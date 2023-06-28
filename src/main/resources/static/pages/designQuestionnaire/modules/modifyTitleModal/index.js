/*const urlParams = new URLSearchParams(window.location.search);
const qNRId = urlParams.get('qNRId');*/
const handleConfirmModify = () => {
  $('#modifyTitleModal').modal('hide')
  $('.questionnaire-title > span').text(questionnaireTitle)
  $('.questionnaire-description > span').text(questionnaireDescription)

  modifyQuestionnaire();
}

const onQuestionnaireTitleInput = (e) => {
  questionnaireTitle = e.value
}

const onQuestionnaireDescriptionInput = (e) => {
  questionnaireDescription = e.value
}

const modifyQuestionnaire = () =>{
  let qnrParams = {
    id:qNRId,
    qNRName: questionnaireTitle,
    qNRContent: questionnaireDescription
  };

  $.ajax({
    url: API_BASE_URL + '/modifyQNRInfo',
    type: "POST",
    data: JSON.stringify(qnrParams),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      alert("修改成功！")
    }
  })
}