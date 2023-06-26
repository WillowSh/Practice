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
                                            <input type="radio" name="${option.optionContent}" value="${optionIndex}" data-option-content="${option.optionContent}">${option.optionContent}
                                          </label>
                                        </div>
                                      `;
                                } else if (item.questionType === '多选') {
                                    optionHtml += `
                                        <div style="display: flex; align-items: center; margin-bottom: 3px;">
                                          <label class="checkbox-inline">
                                            <input type="checkbox" name="${option.optionContent}" value="${optionIndex}" data-option-content="${option.optionContent}">${option.optionContent}
                                          </label>
                                        </div>
                                      `;
                                }
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
function generateUniqueID(prefix) {
    const timestamp = Date.now().toString();
    const random = Math.random().toString(36).substr(2, 9);
    return prefix + timestamp + random;
}
const handleSubmit = () => {

    asId=generateUniqueID('as');
    //problem[problemIndex].problemId = generateUniqueID('ques')


    console.log(questionList);
    questionList.forEach((item, index) => {

        const problemIndex = index + 1;
        const questionType = item.questionType;
        let answer = null;

        if (questionType === '填空') {
            answer = $(`#question${problemIndex} textarea`).val();
            const params = {
                asId: asId, // 关联答卷ID
                questionId: item.id,
                answer: answer,
            };

            $.ajax({
                url: API_BASE_URL + '/addAnswerInfo',
                type: 'POST',
                data: JSON.stringify(params),
                dataType: 'json',
                contentType: 'application/json',
                success(res) {
                    // 回答成功处理
                }
            });
        }
        else {
            $(`#question${problemIndex} input[type=radio]:checked, #question${problemIndex} input[type=checkbox]:checked`).each((_, element) => {
                const optionContent = $(element).data('option-content');

                const params = {
                    asId: asId, // 关联答卷ID
                    questionId: item.id,
                    answer: optionContent,
                };

                $.ajax({
                    url: API_BASE_URL + '/addAnswerInfo',
                    type: 'POST',
                    data: JSON.stringify(params),
                    dataType: 'json',
                    contentType: 'application/json',
                    success(res) {
                        // 回答成功处理
                    }
                });
            });
        }
    });
    let params = {
        id: asId,
        qNRId:qNRId,
        respondent: $('#respondentName').val(),
    }
    $.ajax({
        url: API_BASE_URL + '/addAnswerSheetInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            alert('成功！')
        }
    })





}
