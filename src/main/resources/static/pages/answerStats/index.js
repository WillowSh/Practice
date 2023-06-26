let questionList = [];
const urlParams = new URLSearchParams(window.location.search);
const qNRId = urlParams.get('qNRId');
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
        }
    })

    fetchQuestionList();
};

const fetchQuestionList = () => {
    let params = {
        qNRId: qNRId
    };

    $.ajax({
        url: API_BASE_URL + '/queryQuestionList',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            $('#problem').html(''); // Clear previous questions
            questionList = res.data;

            res.data.forEach((item, index) => {
                $(`#problem`).append(`
                   <div id="optionTitle">第${index+1}题:${item.questionContent}</div>
                   <div>
                    <table class="tablesorter" id="ques-"${index}>
                      <thead>
                       <tr>
                         <th>选项</th>
                         <th>小计</th>
                         <th>比例</th>
                       </tr>
                      </thead>
                      <tbody>
                
                      </tbody>
                    </table>
                    </div>
                  `)
                  setOriginalInfo(item,index);

            });
        }
    });
}

const setOriginalInfo = (item,index) =>{
    /*let params = {
        projectId: projectId,
        questionId: item.id
    };

    $.ajax({
        url: API_BASE_URL + '/queryOptionList',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            let optionList = res.data;
                res.date.map((item, index) => {
                    $(`#problem tbody`).append(`
                    <tr class="tr${index}">
                      <td>${item}</td>
                     </tr>
                     `)});

        }
    });*/
    for(let i = 0; i < 3; i++){
        $(`#problem tbody `).append(`
                      <tr>选项${i+1}</tr>
                     `);
    }

}