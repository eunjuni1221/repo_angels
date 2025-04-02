// Add your custom JS code here

/* 아이디 찾기 체크박스 JS (핸드폰)*/
document.getElementById('certification-phone').addEventListener('change', function() {
    var certificationForm = document.getElementById('certification-form');
    
    // 체크박스가 체크되었을 때
    if (this.checked) {
        certificationForm.style.display = 'block';  // 폼을 보이게 함
    } else {
        certificationForm.style.display = 'none';   // 폼을 숨김
    }
});

/* 아이디 찾기 체크박스 JS (이메일)*/
document.addEventListener("DOMContentLoaded", function () {
    const phoneCheckbox = document.getElementById("certification-phone");
    const emailCheckbox = document.getElementById("certification-email");

    const phoneForm = document.getElementById("certification-form");
    const emailForm = document.getElementById("certification-form-email");

    function toggleForms(selectedCheckbox, showForm, otherCheckbox, hideForm) {
        if (selectedCheckbox.checked) {
            showForm.style.display = "block";
            otherCheckbox.checked = false; // 다른 체크박스 해제
            hideForm.style.display = "none";
        } else {
            showForm.style.display = "none";
        }
    }

    phoneCheckbox.addEventListener("change", function () {
        toggleForms(phoneCheckbox, phoneForm, emailCheckbox, emailForm);
    });

    emailCheckbox.addEventListener("change", function () {
        toggleForms(emailCheckbox, emailForm, phoneCheckbox, phoneForm);
    });
});





