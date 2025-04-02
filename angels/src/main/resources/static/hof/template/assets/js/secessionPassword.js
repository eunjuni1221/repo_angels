document.addEventListener("DOMContentLoaded", function () {
    // 탈퇴 버튼 클릭 시 모달 띄우기
    const secessionBtn = document.getElementById('secessionBtn');
    const modal = $('#secessionModal');

    if (secessionBtn) {
      secessionBtn.addEventListener('click', function () {
        modal.modal('show'); // 모달 표시
      });
    } else {
      console.error('secessionBtn not found!');
    }
  });