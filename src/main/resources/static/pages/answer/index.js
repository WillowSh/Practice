let questionList = [];

onload = () => {
  fetchQuestionList();
};

const fetchQuestionList = () => {
  let params = {
    qNRId: "QNR1687673721182all8ozuct"
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
        if(item.questionType === '矩阵'){
            item.questionContent = item.questionContent.substring(0,item.questionContent.indexOf("//leftTitle:"));
        }
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

          // Compare option with answer
         /* let answerParams = {
            questionId: item.id,
          };

          $.ajax({
            url: API_BASE_URL + '/queryAnswerList',
            type: 'POST',
            data: JSON.stringify(answerParams),
            dataType: 'json',
            contentType: 'application/json',
            success(answerRes) {
              if (answerRes.code === '666') {
                const textarea = document.querySelector('.form-control');
                textarea.value = answerRes;
              }
            }
          });*/

        } else{ /*(item.questionType === '单选' || item.questionType === '多选') */
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
              let optionHtml_1 = '';
              let optionHtml_2 = '';
              optionRes.data.forEach((option, optionIndex) => {
                let optionHtml = '';

                if (item.questionType === '单选') {
                  optionHtml += `
                    <div style="display: flex; align-items: center; margin-bottom: 3px;">
                      <label class="radio-inline">
                        <input type="radio" name="${option.optionContent}" value="${optionIndex}">${option.optionContent}
                      </label>
                    </div>
                  `;
                } else if (item.questionType === '多选') {
                  optionHtml += `
                    <div style="display: flex; align-items: center; margin-bottom: 3px;">
                      <label class="checkbox-inline">
                        <input type="checkbox" name="${option.optionContent}" value="${optionIndex}">${option.optionContent}
                      </label>
                    </div>
                  `;
                }else if (item.questionType === '矩阵') {

                  optionHtml_1 += `<th>${option.optionContent}</th>\n`;

                  optionHtml_2 += `<td><input type="radio" name="${option.optionContent}" /></td>\n`;

                  if (optionIndex === optionRes.data.length-1) {
                    let leftContent = item.questionContent.substring(item.questionContent.indexOf(':') + 1);
                    optionHtml =`
                          <div class="bottom">
                            <table class="table">
                              <thead>
                                <tr>
                                  <th></th>
                              `+optionHtml_1+
                               `
                                 </tr>
                              </thead>
                              <tbody>
                                <tr>
                                    <td>${leftContent}</td>
                              `+optionHtml_2+
                                ` 
                                </tr>
                              </tbody>
                            </table>
                          </div>
                             `;
                  }
                }else{

                  if(optionIndex === 0){
                    let first = option.optionContent.substring(0,option.optionContent.indexOf("//left:"));
                    optionHtml_1 = `<div>${first}</div>`
                  }

                  let fraction = option.optionContent.substring(option.optionContent.indexOf(':') + 1);
                  console.log(option.optionContent)
                  optionHtml_2 +=` <div>
                                      <label className="radio-inline">
                                        <input type="radio" name="${option.optionContent}"/>${fraction}
                                      </label>
                                    </div>`;
                  if (optionIndex === optionRes.data.length-1) {
                      let last = option.optionContent.substring(0,option.optionContent.indexOf("//left:"));
                      optionHtml = `<div className="bottom" style="display: flex; align-items: center; justify-content: space-between;">`
                                   +optionHtml_1
                                   +optionHtml_2
                                   +`<div>${last}</div>`
                                   ;
                  }
                }

                console.log("answer:"+option.optionContent)
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
                     /* const elementId = `#question${index + 1} [value="${optionIndex}"]`;
                      $(elementId).prop('checked', true);*/
                      // 假设 optionContent 是选项的内容，例如 "适合"、"有点适合"、"不太适合"
                      const checkboxName = option.optionContent;
                      let choose_type = "";
                        // 获取具有指定名称和值的复选框元素
                      if(item.questionType === '多选'){
                        choose_type = "checkbox"
                      }else{
                        choose_type = "radio"
                      }

                      const chooseTerm = document.querySelector(`input[type=${choose_type}][name="${checkboxName}"]`);
                       // 将复选框设置为选中状态
                      chooseTerm.checked = true;


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
