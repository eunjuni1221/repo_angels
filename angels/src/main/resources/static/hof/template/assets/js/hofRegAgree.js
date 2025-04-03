document.addEventListener("DOMContentLoaded", function () {
    const checkAll = document.querySelector("#allAgree"); // 전체 동의 체크박스
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:not(#allAgree)'); // 개별 체크박스
    const requiredCheckboxes = document.querySelectorAll('#isAdult, #playAgree, #userInfoAgree'); // 필수 체크박스
    const errorMessage = document.querySelector("#passwordError"); // 오류 메시지
    const nextButton = document.querySelector(".btn-primary-inverse"); // 다음 버튼

    // "전체 동의하기" 클릭 시 모든 체크박스 상태 변경
    checkAll.addEventListener("change", function () {
        checkboxes.forEach(checkbox => {
            checkbox.checked = checkAll.checked;
        });
    });

    // 개별 체크박스 변경 시 전체 동의 상태 업데이트
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener("change", function () {
            checkAll.checked = [...checkboxes].every(chk => chk.checked);
        });
    });

    // "다음" 버튼 클릭 시 필수 체크 여부 확인
    nextButton.addEventListener("click", function () {
        const allRequiredChecked = [...requiredCheckboxes].every(chk => chk.checked);

        if (!allRequiredChecked) {
            errorMessage.style.display = "block";  // 오류 메시지 표시
            errorMessage.textContent = "필수 항목을 모두 체크해야 합니다."; // 오류 메시지 변경
        } else {
            errorMessage.style.display = "none";   // 오류 메시지 숨기기
            window.location.href = "/hof/hofRegister"; // 페이지 이동
        }
    });
});

