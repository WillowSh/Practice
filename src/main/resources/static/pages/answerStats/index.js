let questionList = [];
const urlParams = new URLSearchParams(window.location.search);
const qNRId = urlParams.get('qNRId');
let ASForStat=[];
let optionContentList=[];
let answerForStat=[];
let ratioList=[];

onload = () => {
    let qNRName = '';
    let qNRContent = '';

    let qnrParams = {
        id: qNRId,
    };

    $.ajax({
        url: API_BASE_URL + '/queryQNR',
        type: "POST",
        data: JSON.stringify(qnrParams),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            qNRName = res.data[0].qNRName;
            qNRContent = res.data[0].qNRContent;
            document.getElementById('qnrTitle').innerHTML = qNRName;
            document.getElementById('qnrContent').innerHTML = qNRContent;
        }
    })

    fetchQuestionListStat();
    fetchQuestionList();
};

const fetchQuestionListStat = () =>{

    let params = {
        qNRId: qNRId
    };

    $.ajax({
        url: API_BASE_URL + '/queryASForStat',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            ASForStat[0]=res.data;
        }
    })
}


const fetchQuestionList = () => {
    let params = {
        qNRId: qNRId
    };

    $.ajax({
        url: API_BASE_URL + '/queryQuestionListForStat',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            $('#problem').html(''); // Clear previous questions
            questionList = res.data;

            res.data.forEach((item, index) => {
                const questionId = item.id;
                const tableId = `questionTable-${questionId}`;
                const tbodyId = `tbody-${questionId}`;
                const buttonId = `button-${questionId}`;
                const div1Id = `div1-${questionId}`;

                $(`#problem`).append(`
                   <div id="optionTitle">第${index+1}题:${item.questionContent}</div>
                   <div id="${div1Id}">
                    
                    <div class="buttons" id="${buttonId}">
                      </div>
                    </div>
                  `)
                setOriginalInfo(questionId, tbodyId, buttonId, div1Id, tableId);
            });
        }
    });
}
const fetchAnswerStat = async (questionId) => {
    let optionParams = {
        questionId: questionId,
    };

    const optionListRes = await $.ajax({
        url: API_BASE_URL + '/queryOptionList',
        type: 'POST',
        data: JSON.stringify(optionParams),
        dataType: 'json',
        contentType: 'application/json',
    });

    const optionList = optionListRes.data;

    for (let index = 0; index < optionList.length; index++) {
        const optionItem = optionList[index];
        let answerParams = {
            answer: optionItem.optionContent,
            questionId: questionId,
        };

        const answerRes = await $.ajax({
            url: API_BASE_URL + '/queryAnswerForStat',
            type: 'POST',
            data: JSON.stringify(answerParams),
            dataType: 'json',
            contentType: 'application/json',
        });

        const ratio = (answerRes.data / ASForStat[0]) * 100;
        optionContentList[index] = optionItem.optionContent;
        answerForStat[index] = answerRes.data;
        ratioList.push(ratio);
    }
};


const setOriginalInfo = (questionId, tbodyId, buttonId, div1Id, tableId) =>{
    $(`#${buttonId}`).append(`
                         <button onclick="showTable('${div1Id}','${tableId}','${tbodyId}','${buttonId}','${questionId}')">表格</button>
                         <button onclick="showPieChart('${questionId}')">饼状</button>
                         <button onclick="showBarChart('${div1Id}','${buttonId}','${questionId}')">柱状</button>
                        `)
}
const showBarChart = (div1Id, buttonId, id) =>{
    fetchAnswerStat(id);
    $(`#${div1Id}`).append(`
        <div id="${id}" style="width: 500px;height:400px;"></div>
    `);

    // echarts initialization and set options
    const myChart = echarts.init(document.getElementById(id));
    const option = {
        xAxis: {
            type: 'category',
            data: optionContentList
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: answerForStat,
                type: 'bar'
            }
        ]
    };

    myChart.setOption(option);
};

