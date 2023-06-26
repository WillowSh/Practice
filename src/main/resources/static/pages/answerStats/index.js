let questionList = [];
const urlParams = new URLSearchParams(window.location.search);
const qNRId = urlParams.get('qNRId');
let ASForStat=[];


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

const fetchTableInfo =(questionId, tbodyId, buttonId) =>{
    let optionParams = {
        questionId: questionId,
    };

    $.ajax({
        url: API_BASE_URL + '/queryOptionList',
        type: 'POST',
        data: JSON.stringify(optionParams),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            let optionList = [];
            console.log("success")
            $(`#${tbodyId}`).html('');
            optionList = res.data
            console.log(optionList);
            res.data.forEach((optionItem, index) => {
                let answerParams={
                    answer:optionItem.optionContent,
                    questionId: questionId,
                };
                $.ajax({
                    url: API_BASE_URL + '/queryAnswerForStat',
                    type: 'POST',
                    data: JSON.stringify(answerParams),
                    dataType: 'json',
                    contentType: 'application/json',
                    success(answerRes){
                        const ratio = (answerRes.data / ASForStat[0]) * 100;
                        $(`#${tbodyId}`).append(`

                          <tr>
                            <td>${optionItem.optionContent}</td>
                            <td>${answerRes.data}</td>
                            <td id="td">
                            <p>
                              <style>
                                    meter {
                                        width: 300px; /* 设置进度条的宽度 */
                                       
                                        height: 20px;
                                    }
                                    meter::-webkit-meter-optimum-value,
                                    meter::-webkit-meter-suboptimum-value,
                                    meter::-webkit-meter-even-less-good-value {
                                        background: #0066ff; /* 设置进度条的优化值、次优化值和更差值的颜色 */
                                    }
                                </style>
                                <meter min="0" max="${ASForStat[0]}" value="${answerRes.data}"></meter>
                                <span> ${ratio} % </span>
                            </p>
                            </td>
                          </tr>
                        `)
                    }
                })

            })
        }
    });
}

const setOriginalInfo = (questionId, tbodyId, buttonId, div1Id, tableId) =>{
    $(`#${buttonId}`).append(`
                         <button onclick="showTable('${div1Id}','${tableId}','${tbodyId}','${buttonId}','${questionId}')">表格</button>
                         <button onclick="showPieChart('${questionId}')">饼状</button>
                         <button onclick="showBarChart('${questionId}')">柱状</button>
                        `)
}

const showTable = (div1Id, tableId, tbodyId, buttonId, id) =>{
    $(`#${div1Id}`).append(`
                          <table class="table table-bordered table-striped" id="${tableId}">
                      <colgroup>
                        <col style="width: 45%;">
                        <col style="width: 10%;">
                        <col style="width: 45%;">
                      </colgroup>
                      <thead>
                       <tr>
                         <th>选项</th>
                         <th>小计</th>
                         <th>比例</th>
                       </tr>
                      </thead>
                      <tbody id="${tbodyId}">
                
                
                      </tbody>
                      <thead>
                       <tr>
                         <th>本题有效填写人次</th>
                         <th>${ASForStat[0]}</th>
                         <th> </th>
                       </tr>
                      </thead>
                    </table>
                        `)

    fetchTableInfo(id, tbodyId, buttonId);
}
