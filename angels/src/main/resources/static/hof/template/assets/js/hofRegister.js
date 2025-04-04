
document.getElementById("btnSave").onclick = function (event) {
    event.preventDefault(); // 폼 기본 제출 방지

    function validation() {
        let isValid = true;

        function checkField(fieldId, errorId, errorMessage, regex = null) {
            const field = document.getElementById(fieldId);
            const errorField = document.getElementById(errorId);

            if (!field.value.trim() || (regex && !regex.test(field.value.trim()))) {
                errorField.textContent = errorMessage;
                errorField.style.display = "block";
                field.classList.add("is-invalid");
                isValid = false;
            } else {
                errorField.textContent = "";
                errorField.style.display = "none";
                field.classList.remove("is-invalid");
            }
        }

        // **필수 입력값 검사**
        checkField("urName", "urNameError", "이름을 입력해주세요.");
        checkField("urBirth", "urBirthError", "생년월일을 입력해주세요.", /^\d{4}-\d{2}-\d{2}$/);
        checkField("urNickname", "urNicknameError", "닉네임을 입력해주세요.", /^[가-힣a-zA-Z0-9_]{1,15}$/);
        checkField("urID", "urIDError", "아이디를 입력해주세요.", /^[a-zA-Z0-9_]+$/);
        checkField("urPassword", "urPasswordError", "비밀번호는 8~20자리로 입력해주세요.", /^[a-zA-Z0-9!@#$%^&*()_+]{8,20}$/);
        checkField("urPasswordCheck", "urPasswordCheckError", "비밀번호 확인을 입력해주세요.");

        // **비밀번호 확인 검사**
        const password = document.getElementById("urPassword").value.trim();
        const passwordCheck = document.getElementById("urPasswordCheck").value.trim();
        const passwordCheckError = document.getElementById("urPasswordCheckError");

        if (passwordCheck && password !== passwordCheck) {
            passwordCheckError.textContent = "비밀번호가 일치하지 않습니다.";
            passwordCheckError.style.display = "block";
            document.getElementById("urPasswordCheck").classList.add("is-invalid");
            isValid = false;
        }

        // **라디오 버튼 검사 (성별)**
        const genderError = document.getElementById("urGenderError");
        if (!document.querySelector('input[name="urGender"]:checked')) {
            genderError.textContent = "성별을 선택해주세요.";
            genderError.style.display = "block";
            isValid = false;
        } else {
            genderError.textContent = "";
            genderError.style.display = "none";
        }

        // **셀렉트 박스 검사 (통신사, 이메일)**
        function checkSelect(selectId, errorId, errorMessage) {
            const select = document.getElementById(selectId);
            const errorField = document.getElementById(errorId);

            if (!select.value) {
                errorField.textContent = errorMessage;
                errorField.style.display = "block";
                select.classList.add("is-invalid");
                isValid = false;
            } else {
                errorField.textContent = "";
                errorField.style.display = "none";
                select.classList.remove("is-invalid");
            }
        }

        checkSelect("telecom", "telecomError", "통신사를 선택해주세요.");
        checkSelect("email", "emailError", "도메인을 선택해주세요.");

        // **휴대전화 & 이메일 입력 검사**
        checkField("phoneNumber", "phoneNumberError", "휴대전화를 입력해주세요.", /^\d{10,11}$/);
        checkField("emailID", "emailIDError", "이메일 ID를 입력해주세요.", /^[a-zA-Z0-9._%+-]+$/);

        return isValid;
    }

    // 유효성 검사 후 처리
    if (validation()) {
        // 모달 띄우기
        $("#signupSuccessModal").modal("show");

        // 5초 뒤 자동 이동
        let redirectTimeout = setTimeout(function () {
            window.location.href = "/hof/hofLogin";
        }, 5000);

        // 확인 버튼 클릭 시 즉시 이동
        document.getElementById("signupSuccessConfirmBtn").onclick = function () {
            clearTimeout(redirectTimeout); // 5초 타이머 취소
            window.location.href = "/hof/hofLogin";
        };

        // 1초 뒤 폼 제출 (백엔드로 데이터 전송)
        setTimeout(function () {
            document.getElementById("form").submit();
        }, 1000);
    }
};



