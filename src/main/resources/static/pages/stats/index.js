onload = () => {
    $('#headerRespondentname').text($util.getItem('respondentInfo').respondentname)
    handleHeaderLoad()
    fetchRespondentList()
}

let respondentList = []

const fetchRespondentList = () =>{
    let params = {
        respondent: $('#respondentName').val()
    }
    $.ajax({
        url: API_BASE_URL + '/admin/queryRespondentList',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            $('#table #tbody').html('')
            respondentList = res.data
            res.data.map((item, index) => {
                $('#table #tbody').append(`
          <tr>
            <td>${index + 1}</td>
            <td>${item.respondent}</td>
            <td>${item.qNRId}</td>
            <td>${item.answerDate}</td>
            <td>
             <button type="button" class="btn btn-link" onclick="seeDetailed('${item.id}')">编辑</button>
            </td>
          </tr>
        `)
            })
        }
    })

}

const seeDetailed = () =>{

}