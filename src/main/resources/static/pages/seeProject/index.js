onload = () => {
  $('#headerDivB').text('项目详情')

  let projectId = $util.getPageParam('seeProject')
  console.log(projectId, 'projectId')
  fetchProjectInfo(projectId)
  seeQuestionnaire(projectId)
}

let qNRId=''
let questionnaireList = [];
const fetchProjectInfo = (id) => {
  let params = {
    id:id
  }
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data[0]
      console.log(info, 'res')
      $('#projectName').text(info.projectName)
      $('#createTime').text(info.creationDate)
      $('#projectDescription').text(info.projectContent)
    }
  })
}

const seeQuestionnaire = (id) =>{
  let params = {
    projectId:id
  }
  $.ajax({
    url: API_BASE_URL + '/queryQNRList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",

    success(res) {
      console.log("success")
      $('#table #tbody').html('')
      questionnaireList = res.data
      res.data.map((item, index) => {
        qNRId = item.id
        $('#table #tbody').append(`
          <tr>
            <td>${index + 1}</td>
            <td>${item.qNRName}</td>
            <td>${item.creationDate}</td>
            <td>
              <button type="button" class="btn btn-link" onclick="seePreview('${item.id}')">预览</button>
              <button type="button" class="btn btn-link" onclick="onRelease('${item.id}')">发布</button>
              <button type="button" class="btn btn-link btn-red" onclick="onDelete('${item.id}')">删除</button>
              <button type="button" class="btn btn-link btn-red" onclick="onCalculate('${item.id}','${item.qNRName}')">统计</button>
            </td>
          </tr>
        `)
      })

    }
  })

}

const seePreview = (id) =>{
  location.href = '/pages/answerSheet/index.html?qNRId='+id
}
const onRelease = (id) =>{

}

const onDelete = (id) =>{
  console.log("success");
  const currentDate = new Date();
  const year = currentDate.getFullYear();
  const month = currentDate.getMonth(); // 0 表示一月，11 表示十二月
  const day = currentDate.getDate();
  const hours = currentDate.getHours();
  const minutes = currentDate.getMinutes();
  const seconds = currentDate.getSeconds();
  let nowTime = new Date(year, month, day, hours, minutes, seconds);
  /*let nowTime = new Date().toISOString();*/
  let params = {
    id: id,
    endDate:nowTime
  }
  $.ajax({
    url: API_BASE_URL + '/deleteQNRById',
    type: 'POST',
    data: JSON.stringify(params), //JSON.stringify(params),
    dataType: 'json',
    contentType: 'application/json',
    success(res) {
      if(res.code==="0"){
        alert("正在进行中，无法删除")
      }else{
        alert("删除成功")
      }

    }

  })
}
const onCalculate = (id,name) =>{
  location.href = '/pages/answerStats/index.html?qNRId='+id+'&qNRName='+name
}