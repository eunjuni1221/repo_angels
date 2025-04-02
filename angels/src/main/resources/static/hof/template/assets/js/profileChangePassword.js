document.addEventListener("DOMContentLoaded", function () {
    let confirmBtn = document.getElementById("openPasswordChangeModal");  // ID 수정
    let modal = $("#passwordChangeModal");  // 모달 ID는 passwordChangeModal로 맞춰야 합니다
    let modalConfirmBtn = document.getElementById("modal-confirm-btn");  // 모달 내 확인 버튼

    if (confirmBtn) {
        confirmBtn.addEventListener("click", function (event) {
            event.preventDefault(); // 기본 동작 방지
            console.log("Confirm button clicked!");

            // 모달 표시
            modal.modal("show");

            // 5초 후에 자동으로 프로필 페이지로 이동
            setTimeout(function () {
                window.location.href = "baseball-profile.html";  // 페이지 이동
            }, 5000); // 5초 후 실행
        });
    } else {
        console.error("openPasswordChangeModal not found!");  // ID 확인
    }

    if (modalConfirmBtn) {
        modalConfirmBtn.addEventListener("click", function () {
            // 모달 확인 버튼 클릭 시 바로 프로필 페이지로 이동
            window.location.href = "baseball-profile.html";  // 페이지 이동
        });
    } else {
        console.error("modal-confirm-btn not found!");  // ID 확인
    }
});
