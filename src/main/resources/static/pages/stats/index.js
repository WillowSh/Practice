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
            fetchAnswerSheetList(); // 在成功获取QNR列表后调用获取AnswerSheet列表的函数
        }
    });
};

const fetchAnswerSheetList = () => {
    let params = {
        qNRId: qnrList.map(qnr => qnr.id)[0] // 从QNR列表中提取QNRId构成数组
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
              <button type="button" class="btn btn-link" onclick="seeDetailed('${item.id}')">明细</button>
            </td>
          </tr>
        `);
            });
        }
    });
};

const seeDetailed = (answerSheetId) => {
    // 根据AnswerSheet的id执行您想要的操作
};
