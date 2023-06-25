const urlParams = new URLSearchParams(window.location.search);
const projectId = urlParams.get('projectId');

onload = () => {
    handleHeaderLoad();
    fetchQNRList(); // 添加获取QNR列表的函数调用
}

let qnrList = [];
let answerSheetList = [];
let qnrName=[];


const fetchQNRList = () => {
    let qnrParams = {
        projectId: projectId
    };

    $.ajax({
        url: API_BASE_URL + '/queryQNRList',
        type: 'POST',
        data: JSON.stringify(qnrParams),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            qnrList = res.data;
            qnrName=qnrList.map(qnr => qnr.qNRName)[0]

            fetchAnswerSheetList(qnrList.map(qnr => qnr.id)[0]); // 在成功获取QNR列表后调用获取AnswerSheet列表的函数
        }
    });
};

const fetchAnswerSheetList = (qnrid) => {
    let params = {
        qNRId: qnrid
    };
    console.log("!!!"+JSON.stringify(params));

    $.ajax({
        url: API_BASE_URL + '/queryAnswerSheetList',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            $('#table #tbody').html('');
            answerSheetList = res.data;
            res.data.forEach((item, index) => {
                $('#table #tbody').append(`
          <tr>
            <td>${index + 1}</td>
            <td>${qnrName}</td>
            <td>${item.respondent}</td>
            <td>${item.answerDate}</td>
            <td>
              <button type="button" class="btn btn-link" onclick="seeDetailed('${item.id}','${item.qNRId}')">明细</button>
            </td>
          </tr>
        `);
            });
        }
    });
};

const seeDetailed = (answerSheetId,qNRId) => {
    // 根据AnswerSheet的id执行您想要的操作
    location.href = '/pages/answer/index.html?asId='+answerSheetId+'&qNRId='+qNRId
};
