let questionList = [];
const urlParams = new URLSearchParams(window.location.search);
const qNRId = urlParams.get('qNRId');
onload = () => {
  let qNRName = '';
  let qNRContent='';
  let qnrParams = {
    id:qNRId,
  };

  $.ajax({
    url: API_BASE_URL + '/queryQNR',
    type: "POST",
    data: JSON.stringify(qnrParams),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      qNRName=res.data[0].qNRName;
      qNRContent=res.data[0].qNRContent;
      document.getElementById('qnrTitle').innerHTML = qNRName;
      document.getElementById('qnrContent').innerHTML = qNRContent;
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
        let content ='';
        if(item.questionType === '矩阵'){
          content = item.questionContent.substring(0,item.questionContent.indexOf("//leftTitle:"));
        }else{
          content = item.questionContent;
        }

        let questionHtml = `
          <div class="question" id="question${index + 1}" data-type="${item.questionType}" data-problemIndex="${index + 1}">
            <div class="top">
              <span class="question-title" id="questionTitle">${index + 1}.${item.questionType}</span>
              <span class="must-answer" id="mustAnswer">必答题</span>
            </div>
            <div class="bottom">
              <p class="question-content">${content}</p>
        `;


        if (item.questionType === '填空') {
          questionHtml += `
            <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
          `;
          $(document).ready(() => {
            $('#problem').append(questionHtml); // 在这里编写你的代码
          });


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
              if (item.questionType === "矩阵") {
                matrixShow(questionHtml, item, optionRes.data)
              } else {
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
                  } else {

                    if (optionIndex === 0) {
                      let first = option.optionContent.substring(0, option.optionContent.indexOf("//left:"));
                      optionHtml_1 = `<div>${first}</div>`
                    }

                    let fraction = option.optionContent.substring(option.optionContent.indexOf(':') + 1);
                    console.log(option.optionContent)
                    optionHtml_2 += ` <div>
                                      <label className="radio-inline">
                                        <input type="radio" name="${option.optionContent}"/>${fraction}
                                      </label>
                                    </div>`;
                    if (optionIndex === optionRes.data.length - 1) {
                      let last = option.optionContent.substring(0, option.optionContent.indexOf("//left:"));
                      optionHtml = `<div className="bottom" style="display: flex; align-items: center; justify-content: space-between;">`
                          + optionHtml_1
                          + optionHtml_2
                          + `<div>${last}</div>`
                      ;
                    }
                  }
                  questionHtml += optionHtml;
                });

                questionHtml += `
                  </div>
                </div>
                `;
                $('#problem').append(questionHtml);
              }
            }
          });
        }

      });
    }
  });
};



const matrixShow = (questionHtml,item,optionRes) => {
  let leftContent = item.questionContent.substring(item.questionContent.indexOf(':') + 1);
  const matrixArray = leftContent.split(',');
  let optionHtml = ``;
  let optionHtml_1 = ``;
  let optionHtml_3 = '';
  for (let i = 0; i < matrixArray.length; i++) {
    console.log(matrixArray[i]);
    let optionHtml_2 = ``;

    for (let j = 0; j < optionRes.length; j++) {
      if ( i=== 0) {
        optionHtml_1 += `<th>${optionRes[j].optionContent}</th>\n`;
      }

      let value = optionRes[j].optionContent + "//left:" + matrixArray[i];
      console.log(value);
      optionHtml_2 += `<td><input type="radio" value="${value}" data-option-content="${value}"/></td>\n`;
    }
    optionHtml_3 += `<tr>
                       <td>${matrixArray[i]}</td>\n`
        + optionHtml_2
        + `</tr>`;

  }


  optionHtml = `
                        <div class="bottom">
                          <table class="table">
                            <thead>
                              <tr>
                                <th></th>
                            ` + optionHtml_1 + `
                               </tr>
                            </thead>
                            <tbody>
                            ` + optionHtml_3 +
      ` 
                            </tbody>
                          </table>
                        </div>
                           `;

  questionHtml += optionHtml;
  questionHtml += `
                          </div>
                        </div>
                        `;

  console.log(questionHtml)
  $('#problem').append(questionHtml);

}