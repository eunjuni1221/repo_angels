document.getElementById("btnIdCheck").onclick = function (event) {
    event.preventDefault();
    checkFieldAndSendRequest(
        "urID", 
        "urIDError", 
        "urIDSuce",
        "아이디를 입력해주세요.", 
        "유효하지 않은 아이디입니다. (영문, 숫자, _ 만 가능)", 
        /^[a-zA-Z0-9_]+$/,
        "/login/idNmCheckXdmProc",
        "idExists"
    );
};

document.getElementById("btnNickCheck").onclick = function (event) {
    event.preventDefault();
    checkFieldAndSendRequest(
        "urNickname", 
        "urNicknameError", 
        "urNicknameSuce",
        "닉네임을 입력해주세요.", 
        "유효하지 않은 닉네임입니다. (한글, 영문, 숫자, _ 만 가능)", 
        /^[가-힣a-zA-Z0-9_]{1,15}$/,
        "/login/idNmCheckXdmProc",
        "nicknameExists"
    );
};

function checkFieldAndSendRequest(fieldId, errorId, successId, errorMessageEmpty, errorMessageInvalid, regex, url, responseKey) {
    const field = document.getElementById(fieldId);
    const errorField = document.getElementById(errorId);
    const successField = document.getElementById(successId);
    const fieldValue = field.value.trim();

    // 입력값 확인
    if (!fieldValue) {
        showValidationMessage(errorField, errorMessageEmpty, "error");
        hideValidationMessage(successField);
        field.classList.add("is-invalid");
        field.classList.remove("is-valid");
        return;
    }

    // 정규식 검증
    if (!regex.test(fieldValue)) {
        showValidationMessage(errorField, errorMessageInvalid, "error");
        hideValidationMessage(successField);
        field.classList.add("is-invalid");
        field.classList.remove("is-valid");
        return;
    }

    // AJAX 요청
	$.ajax({
	    type: "POST",
	    url: url,
	    data: fieldId === "urID" ? { urID: fieldValue } : { urNickname: fieldValue },
	    success: function (response) {
	        console.log("서버 응답:", response); // 응답 데이터 확인
	        
	        if (typeof response !== "object") {
	            try {
	                response = JSON.parse(response); // 문자열이면 객체로 변환
	            } catch (e) {
	                console.error("JSON 파싱 오류:", e);
	                showValidationMessage(errorField, "서버 응답 오류", "error");
	                return;
	            }
	        }

	        console.log("파싱된 응답:", response); // 변환된 응답 확인

	        if (response && response[responseKey] === true) {
	            showValidationMessage(errorField, "이미 사용 중입니다.", "error");
	            hideValidationMessage(successField);
	            field.classList.add("is-invalid");
	            field.classList.remove("is-valid");
	        } else {
	            showValidationMessage(successField, "사용 가능한 값입니다.", "success");
	            hideValidationMessage(errorField);
	            field.classList.add("is-valid");
	            field.classList.remove("is-invalid");
	        }
	    },
	    error: function () {
	        showValidationMessage(errorField, "서버 오류가 발생했습니다.", "error");
	        hideValidationMessage(successField);
	        field.classList.add("is-invalid");
	        field.classList.remove("is-valid");
	    }
	});
}

// 메시지 표시 함수
function showValidationMessage(element, message, type) {
    element.textContent = message;
    element.style.display = "block";
    element.className = type === "error" ? "text-danger" : "text-success";
}

// 메시지 숨김 함수
function hideValidationMessage(element) {
    element.style.display = "none";
}
