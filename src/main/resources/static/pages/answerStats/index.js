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
            ASForStat[0]+=res.data;
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
                const button2Id = `button2-${questionId}`;
                const div1Id = `div1-${questionId}`;

                $(`#problem`).append(`
                   <div id="optionContainer">
                      <div id="questionWrapper">
                        <span id="questionNumber">第${index+1}题:</span>
                        <span id="questionContent">${item.questionContent}</span>
                      </div>
                      <div id="buttonWrapper">
                        <button id="${button2Id}" onclick="showSame('${div1Id}','${tableId}','${tbodyId}','${buttonId}','${questionId}')">同类问题统计</button>
                      </div>
                    </div>

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
const fetchAnswerStatForSame = async (questionId) =>{








}

const fetchAnswerStat = async (questionId) => {


    let optionParams = {
        questionId: questionId,
    };

    optionContentList=[];
    answerForStat=[];
    const optionListRes = await $.ajax({
        url: API_BASE_URL + '/queryOptionList',
        type: 'POST',
        data: JSON.stringify(optionParams),
        dataType: 'json',
        contentType: 'application/json',
    });

    const optionList = optionListRes.data;

    const promises = optionList.map(async (optionItem, index) => {

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
        optionContentList[index]=optionItem.optionContent;
        answerForStat[index]=answerRes.data;
        ratioList.push(ratio);
    });

    await Promise.all(promises);
};


const setOriginalInfo = (questionId, tbodyId, buttonId, div1Id, tableId) =>{
    $(`#${buttonId}`).append(`
                         <button onclick="showTable('${div1Id}','${tableId}','${tbodyId}','${buttonId}','${questionId}')">表格</button>
                         <button onclick="showPieChart('${questionId}')">饼状</button>
                         <button onclick="showBarChart('${div1Id}','${buttonId}','${questionId}')">柱状</button>
                        `)
}
const showBarChart = async (div1Id, buttonId, id) => {
    await fetchAnswerStat(id);
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
const showSame = async (div1Id, tableId, tbodyId, buttonId, id) => {

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

    await fetchTableForSame(id, tbodyId, buttonId);


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


const fetchTableForSame = async (questionId, tbodyId, buttonId) => {
    try {
        const questionIdParams = {
            id: questionId,
        };

        const questionListResponse = await $.ajax({
            url: API_BASE_URL + '/queryQuestionList2',
            type: 'POST',
            data: JSON.stringify(questionIdParams),
            dataType: 'json',
            contentType: 'application/json',
        });
        //获取问题的题目内容
        const questionContent = questionListResponse.data[0].questionContent;

        const questionContentParams = {
            questionContent: questionContent,
        };

        const questionContentResponse = await $.ajax({
            url: API_BASE_URL + '/queryQuestionContent',
            type: 'POST',
            data: JSON.stringify(questionContentParams),
            dataType: 'json',
            contentType: 'application/json',
        });
        //获取有相同问题内容的题目
        const questions = questionContentResponse.data;

        const optionCon = [];   //
        const answerSt = [];    //

        let flag = 0;

        //获取选项列表
        const optionParams = {
            questionId: questionId,
        };

        const optionResponse = await $.ajax({
            url: API_BASE_URL + '/queryOptionList',
            type: 'POST',
            data: JSON.stringify(optionParams),
            dataType: 'json',
            contentType: 'application/json',
        });

        const optionList = optionResponse.data;
        console.log(optionList)


        for (let i = 0; i < questions.length; i++) {   //对有相同题目内容的问题循环
            const questionItem = questions[i];
            //获取这个问题的选项列表

            optionList.forEach((optionItem, index) => {  //对每个选项
                const answerParams = {
                    answer: optionItem.optionContent,
                    questionId: questionItem.id,
                };

                $.ajax({
                    url: API_BASE_URL + '/queryAnswerForStat',
                    type: 'POST',
                    data: JSON.stringify(answerParams),
                    dataType: 'json',
                    contentType: 'application/json',
                    success(answerRes) {
                        const optionConIndex = optionCon.indexOf(optionItem.optionContent);
                        if (optionConIndex === -1) {
                            optionCon.push(optionItem.optionContent);
                            answerSt.push(answerRes.data);
                        } else {
                            console.log("???");
                            answerSt[optionConIndex] += answerRes.data;

                        }
                        console.log(answerSt);

                        console.log(optionCon.length+"len")

                        if(i === questions.length-1 && index === optionList.length -1){
                            console.log(i);
                            console.log(optionCon.length+"lenjinru")
                            for (let i = 0; i < answerSt.length; i++) {
                                const optionConItem = optionCon[i];
                                const answerStItem = answerSt[i];

                                console.log("answerStItem:"+answerStItem);
                                /*console.log(answerSt);*/
                                console.log("打印")
                                $(`#${tbodyId}`).append(`
                                <tr>
                                  <td>${optionConItem}</td>
                                  <td>${answerStItem}</td>
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
                                      <meter min="0" max="${ASForStat[0]}" value="${answerStItem}"></meter>
                      <!--                <span> ratio % </span>  -->
                                    </p>
                                  </td>
                                </tr>
                              `);

                            }


                        }

                    },
                });
            });


        }

        $(`#${tbodyId}`).html('');




    } catch (error) {
        console.error(error);
    }
};

