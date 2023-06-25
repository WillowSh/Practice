let questionList = [];

onload = () => {
  fetchQuestionList();
};

const fetchQuestionList = () => {
  let params = {
    qNRId: "QNR1687615803759t0tylsxf2"
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
        let questionHtml = `
          <div class="question" id="question${index + 1}" data-type="${item.questionType}" data-problemIndex="${index + 1}">
            <div class="top">
              <span class="question-title" id="questionTitle">${index + 1}.${item.questionType}</span>
              <span class="must-answer" id="mustAnswer">必答题</span>
            </div>
            <div class="bottom">
              <p class="question-content">${item.questionContent}</p>
        `;

        if (item.questionType === '填空') {
          questionHtml += `
            <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
          `;
          $(document).ready(() => {
            $('#problem').append(questionHtml); // 在这里编写你的代码
          });

        } else if (item.questionType === '单选' || item.questionType === '多选') {
          let optionParams = {
            questionId: item.id
          };

          $.ajax({
            url: API_BASE_URL + '/queryOptionList',
            type: 'POST',
            data: JSON.stringify(optionParams),
            dataType: 'json',
            contentType: 'application/json',
            success(optionRes) {
              optionRes.data.forEach((option, optionIndex) => {
                let optionHtml = '';

                if (item.questionType === '单选') {
                  optionHtml += `
                    <div style="display: flex; align-items: center; margin-bottom: 3px;">
                      <label class="radio-inline">
                        <input type="radio" name="chooseTerm${index}" value="${optionIndex}">${option.optionContent}
                      </label>
                    </div>
                  `;
                } else if (item.questionType === '多选') {
                  optionHtml += `
                    <div style="display: flex; align-items: center; margin-bottom: 3px;">
                      <label class="checkbox-inline">
                        <input type="checkbox" name="chooseTerm${index}" value="${optionIndex}">${option.optionContent}
                      </label>
                    </div>
                  `;
                }

                // Compare option with answer
                let answerParams = {
                  questionId: item.id,
                  answer: option.optionContent
                };

                $.ajax({
                  url: API_BASE_URL + '/queryAnswerList',
                  type: 'POST',
                  data: JSON.stringify(answerParams),
                  dataType: 'json',
                  contentType: 'application/json',
                  success(answerRes) {
                    if (answerRes.code === '666') {
                      // Set selected state for radio or checkbox
                      const elementId = `#question${index + 1} [value="${optionIndex}"]`;
                      $(elementId).prop('checked', true);
                    }
                  }
                });

                questionHtml += optionHtml;
              });
              questionHtml += `
                </div>
              </div>
              `;
              $('#problem').append(questionHtml);
            }
          });
        }

      });
    }
  });
};
