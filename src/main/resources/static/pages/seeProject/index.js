onload = () => {
  $('#headerDivB').text('项目详情')

  let projectId = $util.getPageParam('seeProject')
  console.log(projectId, 'projectId')
  fetchProjectInfo(projectId)
  seeQuestionnaire(projectId)
}

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
        $('#table #tbody').append(`
          <tr>
            <td>${index + 1}</td>
            <td>${item.qNRName}</td>
            <td>${item.creationDate}</td>
            <td>
              <button type="button" class="btn btn-link" onclick="seePreview('${item.id}')">预览</button>
              <button type="button" class="btn btn-link" onclick="onRelease(${item.id})">发布</button>
              <button type="button" class="btn btn-link btn-red" onclick="onDelete(${item.id})">删除</button>
              <button type="button" class="btn btn-link btn-red" onclick="onCalculate(${item.id})">统计</button>
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

}

const onCalculate = (id) =>{

}