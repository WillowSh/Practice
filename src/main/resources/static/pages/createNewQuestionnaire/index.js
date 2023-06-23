onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd hh:mm:ss', // 显示格式
    // minView: "month", // 设置只显示到月份
    minView: 0,  //0表示可以选择小时、分钟   1只可以选择小时
    minuteStep: 1,//分钟间隔1分钟
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd hh:mm:ss', // 显示格式
    // minView: "month", // 设置只显示到月份
    minView: 0,  //0表示可以选择小时、分钟   1只可以选择小时
    minuteStep: 1,//分钟间隔1分钟
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
}
const urlParams = new URLSearchParams(window.location.search);
const projectId = urlParams.get('projectId');
const qNRType = urlParams.get('qNRType');


const handleCreateQNR = () => {
  let params = {
    qNRName: $('#surveyName').val(),
    qNRContent: $('#surveyDescription').val(),
    startDate : $('#startDate').val() && new Date($('#startDate').val()).getTime(),
    endDate : $('#endDate').val() && new Date($('#endDate').val()).getTime(),
    createdBy: $util.getItem('userInfo').username,
    userId:$util.getItem('userInfo').id,
    projectId:projectId,
    qNRType: qNRType // 添加qNRType参数到params对象中
  }
  console.log("^_^");
  if (!params.qNRName) return alert('问卷名称不能为空！')
  if (!params.qNRContent) return alert('问卷描述不能为空！')
  if (!($('#startDate').val() && new Date($('#startDate').val()).getTime())) return alert('开始时间不能为空！')
  if (!($('#endDate').val() && new Date($('#endDate').val()).getTime())) return alert('结束时间不能为空！')

  $.ajax({
    url: API_BASE_URL + '/addQNRInfo',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success() {
      alert('创建成功！')
      location.href = `/pages/designQuestionnaire/index.html`;
    }
  })
  console.log("^_^");
}

