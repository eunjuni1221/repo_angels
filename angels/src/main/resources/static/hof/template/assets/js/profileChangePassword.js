/*document.getElementById("changePasswordBtn").addEventListener("click", function (e) {
    e.preventDefault();

    const currentPassword = document.getElementById("urPassword").value.trim();
    const newPassword = document.getElementById("urNewPassword").value.trim();
    const confirmPassword = document.getElementById("urNewPasswordCheck").value.trim();

    if (!currentPassword || !newPassword || !confirmPassword) {
        alert("모든 항목을 입력해주세요.");
        return;
    }

    if (newPassword !== confirmPassword) {
        alert("새 비밀번호가 일치하지 않습니다.");
        return;
    }

    $.ajax({
        url: '/hof/hofUsrChangePasswordProc',
        type: 'POST',
        data: {
            urPassword: currentPassword,     // 기존 비밀번호
            urNewPassword: newPassword       // 새 비밀번호, 서버에서 받을 때도 urNewPassword로 받음
        },
        success: function (res) {
            if (res.rt === 'success') {
                alert('비밀번호가 변경되었습니다.');
                location.href = '/hof/hofUsrProfile';
            } else if (res.rt === 'wrongCurrentPassword') {
                alert('현재 비밀번호가 틀렸습니다.');
            } else {
                alert('에러가 발생했습니다.');
            }
        },
        error: function (xhr, status, err) {
            console.error("에러 발생: ", err);
            alert("서버 통신 중 오류 발생!");
        }
    });
});*/

document.getElementById("changePasswordBtn").addEventListener("click", function (e) {
    e.preventDefault();

    const currentPassword = document.getElementById("urPassword").value.trim();
    const newPassword = document.getElementById("urNewPassword").value.trim();
    const confirmPassword = document.getElementById("urNewPasswordCheck").value.trim();

    const currentPasswordError = document.getElementById("currentPasswordError");
    const newPasswordError = document.getElementById("newPasswordError");
    const newPasswordCheckError = document.getElementById("newPasswordCheckError");

    // 초기 오류 메시지 숨김
    currentPasswordError.style.display = "none";
    newPasswordError.style.display = "none";
    newPasswordCheckError.style.display = "none";

    let isValid = true;

    // 1. 현재 비밀번호가 비어 있는지 확인
    if (!currentPassword) {
        currentPasswordError.innerText = "현재 비밀번호를 입력해주세요.";
        currentPasswordError.style.display = "block";
        isValid = false;
    }

    // 2. 새 비밀번호 유효성 검사 (8~20자리, 영문+숫자)
    const pwRegex = /^[a-zA-Z0-9!@#$%^&*()_+]{8,20}$/;
    if (!newPassword) {
        newPasswordError.innerText = "새 비밀번호를 입력해주세요.";
        newPasswordError.style.display = "block";
        isValid = false;
    } else if (!pwRegex.test(newPassword)) {
        newPasswordError.innerText = "비밀번호는 8~20자리, 영어, 숫자, 특수문자만 가능합니다.";
        newPasswordError.style.display = "block";
        isValid = false;
    }

    // 3. 새 비밀번호와 확인 비밀번호가 일치하는지 확인
    if (newPassword !== confirmPassword) {
        newPasswordCheckError.innerText = "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.";
        newPasswordCheckError.style.display = "block";
        isValid = false;
    }

    // 4. 새 비밀번호가 현재 비밀번호와 동일한지 확인
    if (currentPassword && newPassword && currentPassword === newPassword) {
        newPasswordError.innerText = "새 비밀번호는 현재 비밀번호와 다르게 설정해야 합니다.";
        newPasswordError.style.display = "block";
        isValid = false;
    }

    if (!isValid) return;

    // 비밀번호 변경 요청
    $.ajax({
        url: '/hof/hofUsrChangePasswordProc',
        type: 'POST',
        data: {
            urPassword: currentPassword,     // 기존 비밀번호
            urNewPassword: newPassword       // 새 비밀번호
        },
        success: function (res) {
            if (res.rt === 'success') {
                alert('비밀번호가 변경되었습니다.');
                location.href = '/hof/hofUsrProfile';
            } else if (res.rt === 'wrongCurrentPassword') {
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


