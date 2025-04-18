document.getElementById("changePasswordBtn").addEventListener("click", function (e) {
    e.preventDefault();

    const currentPassword = document.getElementById("urPassword");
    const newPassword = document.getElementById("urNewPassword");
    const confirmPassword = document.getElementById("urNewPasswordCheck");

    const currentPasswordError = document.getElementById("currentPasswordError");
    const newPasswordError = document.getElementById("newPasswordError");
    const newPasswordCheckError = document.getElementById("newPasswordCheckError");

    // 초기화: 오류 메시지 숨김 + 테두리 제거
    [currentPassword, newPassword, confirmPassword].forEach(input => {
        input.classList.remove("is-invalid");
    });

    [currentPasswordError, newPasswordError, newPasswordCheckError].forEach(error => {
        error.style.display = "none";
    });

    let isValid = true;
    const pwRegex = /^[a-zA-Z0-9!@#$%^&*()_+]{8,20}$/;

    // 현재 비밀번호
    if (!currentPassword.value.trim()) {
        currentPassword.classList.add("is-invalid");
        currentPasswordError.innerText = "현재 비밀번호를 입력해주세요.";
        currentPasswordError.style.display = "block";
        isValid = false;
    }

    // 새 비밀번호
    if (!newPassword.value.trim()) {
        newPassword.classList.add("is-invalid");
        newPasswordError.innerText = "새 비밀번호를 입력해주세요.";
        newPasswordError.style.display = "block";
        isValid = false;
    } else if (!pwRegex.test(newPassword.value.trim())) {
        newPassword.classList.add("is-invalid");
        newPasswordError.innerText = "비밀번호는 8~20자리, 영어, 숫자, 특수문자만 가능합니다.";
        newPasswordError.style.display = "block";
        isValid = false;
    }

    // 새 비밀번호 확인
    if (newPassword.value.trim() !== confirmPassword.value.trim()) {
        confirmPassword.classList.add("is-invalid");
        newPasswordCheckError.innerText = "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.";
        newPasswordCheckError.style.display = "block";
        isValid = false;
    }

    // 새 비밀번호가 현재 비밀번호와 같은 경우
    if (currentPassword.value.trim() && newPassword.value.trim() && currentPassword.value.trim() === newPassword.value.trim()) {
        newPassword.classList.add("is-invalid");
        newPasswordError.innerText = "새 비밀번호는 현재 비밀번호와 다르게 설정해야 합니다.";
        newPasswordError.style.display = "block";
        isValid = false;
    }

    if (!isValid) return;

    // AJAX 요청
    $.ajax({
        url: '/hof/hofUsrChangePasswordProc',
        type: 'POST',
        data: {
            urPassword: currentPassword.value.trim(),
            urNewPassword: newPassword.value.trim()
        },
        success: function (res) {
            if (res.rt === 'success') {
                $('#urPwChSuccessModal').modal('show');

                // 5초 후 자동 이동
                setTimeout(function () {
                    location.href = '/hof/hofUsrProfile';
                }, 5000);

                // 확인 버튼 클릭 시 즉시 이동
                document.getElementById("urPwChSuccessBtn").addEventListener("click", function () {
                    location.href = '/hof/hofUsrProfile';
                });
            } else if (res.rt === 'wrongCurrentPassword') {
                currentPassword.classList.add("is-invalid");
                currentPasswordError.innerText = "현재 비밀번호가 틀렸습니다.";
                currentPasswordError.style.display = "block";
            } else {
                alert('알 수 없는 오류가 발생했습니다.');
            }
        },
        error: function () {
            alert("서버 통신 오류가 발생했습니다.");
        }
    });
});
