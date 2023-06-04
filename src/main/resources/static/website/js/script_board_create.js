const subject = document.getElementById("subject")
const content = document.getElementById("content");
const btn_submit = document.getElementById("btn_submit");


btn_submit.addEventListener("click", () => {
    const data =new FormData(document.getElementById("dataForm"));
    btn_submit.disabled=true;
    btn_submit.innerHTML='<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...';
    $.ajax({
        type: "POST",
        url: "",
        data: data,
        enctype: "multipart/form-data", //form data 설정
        processData: false, //프로세스 데이터 설정 : false 값을 해야 form data로 인식
        contentType: false, //헤더의 Content-Type을 설정 : false 값을 해야 form data로 인식
        success: function(data) {
            alert(data.message);
            location.href=data.redirect_url;
        },
        error: function(error) {
            alert(error.status + error.responseJSON.message);
            btn_submit.disabled = false;
            btn_submit.innerHTML='저장하기';
        },
    });
})