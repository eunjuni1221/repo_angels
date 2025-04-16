document.getElementById("buttonSignIn").onclick = function () {
    if (validation()) {
        loginLogic();
    }
};

function validation() {
    let idInput = document.getElementById("urID");
    let passwordInput = document.getElementById("urPassword");
    let idGroup = document.getElementById("idGroup");
    let passwordGroup = document.getElementById("passwordGroup");
    let idError = document.getElementById("idError");
    let passwordError = document.getElementById("passwordError");

    let idValue = idInput.value.trim();
    let passwordValue = passwordInput.value.trim();
    let idPattern = /^[a-zA-Z0-9]+$/;
    let isValid = true;

    // 초기화
    idGroup.classList.remove("has-error", "has-success");
    passwordGroup.classList.remove("has-error", "has-success");
    idError.style.display = "none";
    passwordError.style.display = "none";

    // 아이디 검증
    if (idValue === "") {
        idGroup.classList.add("has-error");
        idError.innerText = "아이디를 입력해주세요.";
        idError.style.display = "block";
        isValid = false;
    } else if (!idPattern.test(idValue)) {
        idGroup.classList.add("has-error");
        idError.innerText = "잘못된 입력 방식입니다.";
        idError.style.display = "block";
        isValid = false;
    } else {
        idGroup.classList.add("has-success");
    }

    // 비밀번호 검증
    if (passwordValue === "") {
        passwordGroup.classList.add("has-error");
        passwordError.innerText = "비밀번호를 입력해주세요.";
        passwordError.style.display = "block";
        isValid = false;
    } else {
        passwordGroup.classList.add("has-success");
    }

    return isValid;
}

function loginLogic() {
    if (validation() == false) return false;

    $.ajax({
        async: true,
        cache: false,
        type: "post",
        url: "/login/signinHofProc",
        data: {
            "urID": $("#urID").val(),
            "urPassword": $("#urPassword").val(),
            "autoLogin": $("#autoLogin").is(":checked")
        },
        success: function (response) {
            if (response.rt == "success") {
                location.href = "/mainHof";
            } else {
                $("#idGroup").addClass("has-error");
                $("#passwordGroup").addClass("has-error");

                document.getElementById("modalAlertTitle").innerText = "경고";
                document.getElementById("modalAlertBody").innerText = "아이디 또는 비밀번호가 일치하지 않습니다 아이디와 비밀번호를 정확히 입력해주세요";
                $("#modalAlert").modal("show");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
    });
}

document.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        event.preventDefault(); // 기본 제출 막기
        document.getElementById("buttonSignIn").click(); // 버튼 클릭처럼 동작
    }
});
