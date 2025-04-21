document.getElementById("changeAddressBtn").addEventListener("click", function (e) {
    e.preventDefault();

    const postNumber = document.getElementById("urPostNumber");
    const address = document.getElementById("urAddress");

    const postNumberError = document.getElementById("urPostNumberError");
    const addressError = document.getElementById("urAddressError");

    // 초기화
    [postNumber, address].forEach(input => {
        input.classList.remove("is-invalid");
    });
    [postNumberError, addressError].forEach(error => {
        error.style.display = "none";
    });

    let isValid = true;

    // 우편번호와 주소의 유효성만 검사
    if (!postNumber.value.trim()) {
        postNumber.classList.add("is-invalid");
        postNumberError.style.display = "block";
        isValid = false;
    }

    if (!address.value.trim()) {
        address.classList.add("is-invalid");
        addressError.style.display = "block";
        isValid = false;
    }

    if (!isValid) return;

    // AJAX 전송
    $.ajax({
        url: '/hof/hofUsrChangeAddressProc',
        type: 'POST',
        data: {
            urPostNumber: postNumber.value.trim(),
            urAddress: address.value.trim()
            // 상세주소는 전송하지 않음
        },
        success: function (res) {
            if (res.rt === 'success') {
                // 모달을 보여준다.
                $('#urAddChSuccessModal').modal('show');

                // 확인 버튼 클릭 시 즉시 프로필 페이지로 이동
                document.getElementById("urAddChSuccessBtn").addEventListener("click", function () {
                    location.href = '/hof/hofUsrProfile';  // 페이지 즉시 이동
                });

                // 5초 후 자동 이동
                setTimeout(function () {
                    location.href = '/hof/hofUsrProfile';  // 페이지 자동 이동
                }, 5000);
            } else {
                alert('알 수 없는 오류가 발생했습니다.');
            }
        },
        error: function () {
            alert('서버와 통신 중 오류가 발생했습니다.');
        }
    });
});
