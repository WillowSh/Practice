/*import {getItem} from "../../utils/storage";*/

let questionList = [];
let answerList=[];
let answer=[];
const urlParams = new URLSearchParams(window.location.search);
let asId = urlParams.get('asId');
const qNRId = urlParams.get('qNRId');
let optionContent = [];
onload = () => {
  let qNRName = '';
  let qNRContent='';
  let respondent='';
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
     /* qNRName=res.data[0].qNRName;
      qNRContent=res.data[0].qNRContent;
      document.getElementById('qnrTitle').innerHTML = qNRName;
      document.getElementById('qnrContent').innerHTML = qNRContent;*/
    }
  })
  let resParams = {
    id:asId,
  };

  $.ajax({
    url: API_BASE_URL + '/queryAnswerSheet',
    type: "POST",
    data: JSON.stringify(resParams),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      respondent=res.data[0].respondent;
      document.getElementById('respondent').innerHTML = respondent;
    }
  })

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
        let content = '';
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
            <textarea class="form-control"  placeholder="请输入" id="textarea-${index}" rows="4" style="width: 70%;"></textarea>
          `;
          $(document).ready(() => {
            $('#problem').append(questionHtml); // 在这里编写你的代码
          });

          // Compare option with answer
         let answerParams = {
            questionId: item.id,
           asId: 1,
          };

          $.ajax({
            url: API_BASE_URL + '/queryAnswerList2',
            type: 'POST',
            data: JSON.stringify(answerParams),
            dataType: 'json',
            contentType: 'application/json',
            success(answerRes) {
              if (answerRes.code === '666') {
                const textarea = document.querySelector(`#textarea-${index}`);
                answerList=answerRes.data;
                answer=answerList.map(asl => asl.answer)[0];
                textarea.value = answer;
              }
            }
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

              console.log(optionRes.length + "长度");
              if (item.questionType === "矩阵") {
                matrixAnswer(questionHtml, item, optionRes.data, optionRes.length)
              } else {


              optionRes.data.forEach((option, optionIndex) => {
                let optionHtml = '';

                if (item.questionType === '单选') {
                  console.log("单选" + index);
                  optionHtml += `
                    <div style="display: flex; align-items: center; margin-bottom: 3px;">
                      <label class="radio-inline">
                        <input type="radio" name="${option.optionContent}" id="option-${index}" value="${optionIndex}">${option.optionContent}
                      </label>
                    </div>
                  `;
                } else if (item.questionType === '多选') {
                  console.log("多选" + index);
                  optionHtml += `
                    <div style="display: flex; align-items: center; margin-bottom: 3px;">
                      <label class="checkbox-inline">
                        <input type="checkbox" name="${option.optionContent}" id="option-${index}" value="${optionIndex}">${option.optionContent}
                      </label>
                    </div>
                  `;
                } else {
                  if (optionIndex === 0) {
                    let first = option.optionContent.substring(0, option.optionContent.indexOf("//left:"));
                    optionHtml_1 = `<div>${first}</div>\n`
                  }

                  let fraction = option.optionContent.substring(option.optionContent.indexOf(':') + 1);
                  optionHtml_2 += ` <div>
                                      <label className="radio-inline">
                                        <input type="radio" name="${option.optionContent}" id="option-${index}" value="${optionIndex}"/>${fraction}
                                      </label>
                                    </div>`;
                  if (optionIndex === optionRes.data.length - 1) {
                    let last = option.optionContent.substring(0, option.optionContent.indexOf("//left:"));
                    optionHtml = `<div className="bottom" style="display: flex; align-items: center; justify-content: space-between;">\n`
                        + optionHtml_1
                        + optionHtml_2
                        + `<div>${last}</div>`
                    ;

                  }

                }
                // Compare option with answer

                let answerParams = {
                  questionId: item.id,
                  asId: 1,
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

                      /* const chooseTerm = document.querySelector(`[#option-${index}]`);*/
                      let chooseTerm = '';

                      if (item.questionType !== "矩阵") {
                        chooseTerm = document.querySelector(`#option-${index}[value="${optionIndex}"]`);
                      } else {
                        chooseTerm = document.querySelector(`[value=${option.optionContent}]`);
                      }
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
              }
           });

        }

      });
    }
  });
};


const matrixAnswer = (questionHtml,item,optionRes,optionIndex) => {

  /*optionHtml_1 += `<th>${option.optionContent}</th>\n`;*/

  /* optionHtml_2 += `<td><input type="radio" class="${option.optionContent}"/></td>\n`;*/
  /*optionContent[optionIndex] = option.optionContent;*/

    let leftContent = item.questionContent.substring(item.questionContent.indexOf(':') + 1);
    const matrixArray = leftContent.split(',');
    let optionHtml =``;
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
        optionHtml_2 += `<td><input type="radio" value="${value}"/></td>\n`;

        let answerParams = {
          questionId: item.id,
          asId: 1,
          answer:value
        };

        $.ajax({
          url: API_BASE_URL + '/queryAnswerList',
          type: 'POST',
          data: JSON.stringify(answerParams),
          dataType: 'json',
          contentType: 'application/json',
          success(answerRes) {
            if (answerRes.code === '666') {
              const  chooseTerm = document.querySelector(`[value="${value}"]`);

              chooseTerm.checked = true;
            }
          }
        });
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