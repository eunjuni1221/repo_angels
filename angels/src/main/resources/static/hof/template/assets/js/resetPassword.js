
// 비밀번호 재설정 한 후 확인 누르면 모달 띄운 후 로그인 창으로 넘어가는 스크립트 코드
document.addEventListener("DOMContentLoaded", function () {
    let confirmBtn = document.getElementById("confirm-btn");
    let modal = $("#confirmationModal");

    if (confirmBtn) {
        confirmBtn.addEventListener("click", function (event) {
            event.preventDefault(); // 기본 동작 방지
            console.log("Confirm button clicked!");

            // 모달을 띄운 후
            modal.modal("show"); 

            // 모달을 닫고 로그인 페이지로 리다이렉트
            $('#modal-confirm-btn').on('click', function () {
                modal.modal("hide");  // 모달 닫기
                window.location.href = "baseball-login.html";  // 로그인 페이지로 리다이렉트
            });
        });
    } else {
        console.error("confirm-btn not found!");
    }
});