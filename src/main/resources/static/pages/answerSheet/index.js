
let questionList = []
onload = () => {
  fetchQuestionList()
  /*
      $('#problem').append(`
      <div class="question" id="question1" data-type="1" data-problemIndex="1">
        <div class="top">
          <span class="question-title" id="questionTitle">1.单选题</span>
          <span class="must-answer" id="mustAnswer">必答题</span>
        </div>
        <div class="bottom">
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" name="chooseTerm">选项1
            </label>
          </div>
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" name="chooseTerm">选项2
            </label>
          </div>
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" name="chooseTerm">选项3
            </label>
          </div>
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" name="chooseTerm">选项4
            </label>
          </div>
        </div>
      </div>
    `)
      $('#problem').append(`
      <div class="question" id="question1" data-type="1" data-problemIndex="1">
        <div class="top">
          <span class="question-title" id="questionTitle">2.多选题</span>
          <span class="must-answer" id="mustAnswer">必答题</span>
        </div>
        <div class="bottom">
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" name="chooseTerm">选项1
            </label>
          </div>
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" name="chooseTerm">选项2
            </label>
          </div>
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" name="chooseTerm">选项3
            </label>
          </div>
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" name="chooseTerm">选项4
            </label>
          </div>
        </div>
      </div>
    `)
      $('#problem').append(`
      <div class="question" id="question1" data-type="1" data-problemIndex="1">
        <div class="top">
          <span class="question-title" id="questionTitle">3.填空题</span>
          <span class="must-answer" id="mustAnswer">必答题</span>
        </div>
        <div class="bottom">
          <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
      </div>
    `)
      $('#problem').append(`
      <div class="question" id="question1" data-type="1" data-problemIndex="1">
        <div class="top">
          <span class="question-title" id="questionTitle">4.矩阵题</span>
          <span class="must-answer" id="mustAnswer">必答题</span>
        </div>
        <div class="bottom">
          <table class="table">
            <thead>
              <tr>
                <th></th>
                <th>选项1</th>
                <th>选项2</th>
                <th>选项3</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>标题1</td>
                <td><input type="radio" name="chooseTerm1" /></td>
                <td><input type="radio" name="chooseTerm1" /></td>
                <td><input type="radio" name="chooseTerm1" /></td>
              </tr>
              <tr>
                <td>标题2</td>
                <td><input type="radio" name="chooseTerm2" /></td>
                <td><input type="radio" name="chooseTerm2" /></td>
                <td><input type="radio" name="chooseTerm2" /></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    `)
      $('#problem').append(`
      <div class="question" id="question1" data-type="1" data-problemIndex="1">
        <div class="top">
          <span class="question-title" id="questionTitle">5.量表题</span>
          <span class="must-answer" id="mustAnswer">必答题</span>
        </div>
        <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
          <div>很满意</div>
          <div>
            <label class="radio-inline">
              <input type="radio" name="fraction" />5
            </label>
          </div>
          <div>
            <label class="radio-inline">
              <input type="radio" name="fraction" />4
            </label>
          </div>
          <div>
            <label class="radio-inline">
              <input type="radio" name="fraction" />3
            </label>
          </div>
          <div>
            <label class="radio-inline">
              <input type="radio" name="fraction" />2
            </label>
          </div>
          <div>
            <label class="radio-inline">
              <input type="radio" name="fraction" />1
            </label>
          </div>
          <div>很不满意</div>
        </div>
      </div>
    `)*/
}


const fetchQuestionList = () => {
  let params = {
    /* pageNum,
    pageSize: 10, */
    qNRId: "QNR1687615803759t0tylsxf2"
  }
  $.ajax({
    url: API_BASE_URL + '/queryQuestionList',
    type: 'POST',
    data: JSON.stringify(params),
    dataType: 'json',
    contentType: 'application/json',
    success(res) {
      $('#problem').html(''); // Clear previous questions
      questionList = res.data;
      res.data.map((item, index) => {
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
          $('#problem').append(questionHtml);
        } else {
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
                if (item.questionType === '单选') {
                  questionHtml += `
                  <div style="display: flex; align-items: center; margin-bottom: 3px;">
                    <label class="radio-inline">
                      <input type="radio" name="chooseTerm${index}" value="${optionIndex}">${option.optionContent}
                    </label>
                  </div>
                `;
                } else if (item.questionType === '多选') {
                  questionHtml += `
                  <div style="display: flex; align-items: center; margin-bottom: 3px;">
                    <label class="checkbox-inline">
                      <input type="checkbox" name="chooseTerm${index}" value="${optionIndex}">${option.optionContent}
                    </label>
                  </div>
                `;
                }

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
}

