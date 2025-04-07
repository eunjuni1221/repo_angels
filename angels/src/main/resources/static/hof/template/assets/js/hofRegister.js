	let isIdChecked = false;
	let isNicknameChecked = false;

	// 아이디 유효성 + 중복 체크
	document.getElementById("urID").addEventListener("blur", function () {
		checkFieldAndSendRequest(
			"urID",
			"urIDError",
			"urIDSuce",
			"아이디를 입력해주세요.",
			"아이디는 5~20자의 영문 소문자, 숫자, 특수기호(_),(-)만 사용 가능합니다.",
			/^[a-z0-9_-]{5,20}$/,
			"/login/idNmCheckXdmProc",
			"idExists"
		);
	});

	const nicknameInput = document.getElementById("urNickname");
	const nicknameInfo = document.getElementById("urNicknameInfo");

	// 닉네임 입력 시 안내문구 숨기기
	nicknameInput.addEventListener("input", function () {
		if (nicknameInfo) {
			nicknameInfo.style.display = "none";
		}
	});

	// 닉네임 유효성 + 중복 체크
	document.getElementById("urNickname").addEventListener("blur", function () {
		checkFieldAndSendRequest(
			"urNickname",
			"urNicknameError",
			"urNicknameSuce",
			"닉네임을 입력해주세요.",
			"유효하지 않은 닉네임입니다. (길이 2 ~ 15자 한글, 영문, 숫자, _ 만 가능)",
			/^[가-힣a-zA-Z0-9_]{2,15}$/,
			"/login/idNmCheckXdmProc",
			"nicknameExists"
		);
	});

	// 비밀번호 유효성 검사
	document.getElementById("urPassword").addEventListener("blur", function () {
		const password = this.value.trim();
		const errorField = document.getElementById("urPasswordError");
		const validRegex = /^[a-zA-Z0-9!@#$%^&*()_+]+$/;

		if (password.length < 8 || password.length > 20) {
			showValidationMessage(errorField, "비밀번호는 8~20자리로 입력해주세요.", "error");
			this.classList.add("is-invalid");
			this.classList.remove("is-valid");
			return;
		}

		if (!validRegex.test(password)) {
			showValidationMessage(errorField, "영어(대, 소문자), 숫자, 특수문자(!@#$%^&*()_+)만 가능합니다.", "error");
			this.classList.add("is-invalid");
			this.classList.remove("is-valid");
			return;
		}

		errorField.style.display = "none";
		errorField.textContent = "";
		this.classList.remove("is-invalid");
		this.classList.add("is-valid");
	});

	// 비밀번호 확인 검사
	document.getElementById("urPasswordCheck").addEventListener("blur", function () {
		const password = document.getElementById("urPassword").value.trim();
		const passwordCheck = this.value.trim();
		const errorField = document.getElementById("urPasswordCheckError");

		if (!passwordCheck) {
			showValidationMessage(errorField, "비밀번호 확인을 입력해주세요.", "error");
			this.classList.add("is-invalid");
			this.classList.remove("is-valid");
			return;
		}

		if (password !== passwordCheck) {
			showValidationMessage(errorField, "비밀번호가 일치하지 않습니다.", "error");
			this.classList.add("is-invalid");
			this.classList.remove("is-valid");
		} else {
			showValidationMessage(errorField, "비밀번호가 일치합니다.", "success");
			this.classList.add("is-valid");
			this.classList.remove("is-invalid");
		}
	});

	// 중복 체크 AJAX + 밸리데이션
	function checkFieldAndSendRequest(fieldId, errorId, successId, errorMessageEmpty, errorMessageInvalid, regex, url, responseKey) {
		const field = document.getElementById(fieldId);
		const errorField = document.getElementById(errorId);
		const successField = document.getElementById(successId);
		const fieldValue = field.value.trim();

		if (!fieldValue) {
			showValidationMessage(errorField, errorMessageEmpty, "error");
			hideValidationMessage(successField);
			field.classList.add("is-invalid");
			field.classList.remove("is-valid");
			if (fieldId === "urID") isIdChecked = false;
			if (fieldId === "urNickname") isNicknameChecked = false;
			return;
		}

		if (!regex.test(fieldValue)) {
			showValidationMessage(errorField, errorMessageInvalid, "error");
			hideValidationMessage(successField);
			field.classList.add("is-invalid");
			field.classList.remove("is-valid");
			if (fieldId === "urID") isIdChecked = false;
			if (fieldId === "urNickname") isNicknameChecked = false;
			return;
		}

		$.ajax({
			type: "POST",
			url: url,
			data: {
				urID: fieldId === "urID" ? fieldValue : "",
				urNickname: fieldId === "urNickname" ? fieldValue : ""
			},
			success: function (response) {
				if (response[responseKey] === true) {
					showValidationMessage(errorField, "이미 사용 중입니다.", "error");
					hideValidationMessage(successField);
					field.classList.add("is-invalid");
					field.classList.remove("is-valid");
					if (fieldId === "urID") isIdChecked = false;
					if (fieldId === "urNickname") isNicknameChecked = false;
				} else {
					const label = fieldId === "urID" ? "아이디" : "닉네임";
					showValidationMessage(successField, `사용 가능한 ${label}입니다.`, "success");
					hideValidationMessage(errorField);
					field.classList.add("is-valid");
					field.classList.remove("is-invalid");
					if (fieldId === "urID") isIdChecked = true;
					if (fieldId === "urNickname") isNicknameChecked = true;
				}
			}
		});
	}

	// 메시지 출력 유틸
	function showValidationMessage(element, message, type) {
		element.textContent = message;
		element.style.display = "block";
		element.className = type === "error" ? "text-danger" : "text-success";
	}
	function hideValidationMessage(element) {
		element.style.display = "none";
	}

	// 회원가입 버튼 클릭 시 전체 유효성 검사
	document.getElementById("btnSave").onclick = function (event) {
		event.preventDefault();

		function validation() {
			let isValid = true;

			// ✅ 닉네임 안내문구 무조건 숨기기
			if (nicknameInfo) {
				nicknameInfo.style.display = "none";
			}

			// 필드 유효성 검사
			function checkField(fieldId, errorId, errorMessageEmpty, errorMessageInvalid, regex = null) {
				const field = document.getElementById(fieldId);
				const errorField = document.getElementById(errorId);
				const fieldValue = field.value.trim();

				if (!fieldValue) {
					errorField.textContent = errorMessageEmpty;
					errorField.style.display = "block";
					field.classList.add("is-invalid");
					isValid = false;
				} else if (regex && !regex.test(fieldValue)) {
					errorField.textContent = errorMessageInvalid;
					errorField.style.display = "block";
					field.classList.add("is-invalid");
					isValid = false;
				} else {
					errorField.textContent = "";
					errorField.style.display = "none";
					field.classList.remove("is-invalid");
				}
			}

			checkField("urName", "urNameError", "이름을 입력해주세요.", "유효하지 않은 이름입니다.", /^[가-힣]+$/);
			checkField("urBirth", "urBirthError", "생년월일을 입력해주세요.", "올바른 형식(YYYY-MM-DD)으로 입력해주세요.", /^\d{4}-\d{2}-\d{2}$/);
			checkField("urNickname", "urNicknameError", "닉네임을 입력해주세요.", "유효하지 않은 닉네임입니다.", /^[가-힣a-zA-Z0-9_]{2,15}$/);
			checkField("urID", "urIDError", "아이디를 입력해주세요.", "유효하지 않은 아이디입니다.", /^[a-z0-9_-]{5,20}$/);
			checkField("urPassword", "urPasswordError", "비밀번호를 입력해주세요.", "비밀번호는 8~20자리로 입력해주세요.", /^[a-zA-Z0-9!@#$%^&*()_+]{8,20}$/);
			checkField("urPasswordCheck", "urPasswordCheckError", "비밀번호 확인을 입력해주세요.", "비밀번호가 일치하지 않습니다.");

			const password = document.getElementById("urPassword").value.trim();
			const passwordCheck = document.getElementById("urPasswordCheck").value.trim();
			if (passwordCheck && password !== passwordCheck) {
				const passwordCheckError = document.getElementById("urPasswordCheckError");
				passwordCheckError.textContent = "비밀번호가 일치하지 않습니다.";
				passwordCheckError.style.display = "block";
				document.getElementById("urPasswordCheck").classList.add("is-invalid");
				isValid = false;
			}

			const genderError = document.getElementById("urGenderError");
			if (!document.querySelector('input[name="urGender"]:checked')) {
				genderError.textContent = "성별을 선택해주세요.";
				genderError.style.display = "block";
				isValid = false;
			} else {
				genderError.textContent = "";
				genderError.style.display = "none";
			}

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
			checkField("phoneNumber", "phoneNumberError", "휴대전화를 입력해주세요.", "올바른 형식(11자리 숫자)으로 입력해주세요.", /^\d{11}$/);
			checkField("emailID", "emailIDError", "이메일 ID를 입력해주세요.", "유효하지 않은 이메일 ID입니다.", /^[a-zA-Z0-9._%+-]+$/);

			if (!isIdChecked) {
				document.getElementById("urIDError").textContent = "아이디 중복 확인을 해주세요.";
				document.getElementById("urIDError").style.display = "block";
				isValid = false;
			}

			if (!isNicknameChecked) {
				document.getElementById("urNicknameError").textContent = "닉네임 중복 확인을 해주세요.";
				document.getElementById("urNicknameError").style.display = "block";
				isValid = false;
			}

			return isValid;
		}

		// 모든 유효성 검사 통과 시 회원가입 성공 처리
		if (validation()) {
			$("#signupSuccessModal").modal("show");

			let redirectTimeout = setTimeout(function () {
				window.location.href = "/hof/hofLogin";
			}, 5000);

			document.getElementById("signupSuccessConfirmBtn").onclick = function () {
				clearTimeout(redirectTimeout);
				window.location.href = "/hof/hofLogin";
			};

			setTimeout(function () {
				document.getElementById("form").submit();
			}, 1000);
		}
	};
