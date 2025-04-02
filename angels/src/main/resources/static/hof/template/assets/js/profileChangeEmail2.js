$(document).ready(function() {
    // 전화번호 인증 링크 클릭 시 모달 열기
    $('#openPhoneVerifyModal').click(function(e) {
        e.preventDefault(); // 링크 클릭 시 페이지 리로드 방지
        $('#phoneVerifyModal').modal('show'); // 전화번호 인증 모달 열기
    });

    // 전화번호 인증 후 확인 버튼 클릭 시
    $('#confirmPhoneVerify').click(function() {
        // 전화번호 인증 모달을 닫고
        $('#phoneVerifyModal').modal('hide');
        
        // 이메일 변경 모달을 열기
        $('#emailChangeModal').modal('show');
    });

    // 이메일 변경 모달에서 이메일 변경 버튼 클릭 시
    $('#submitNewEmail').click(function() {
        var newEmail = $('#new-email').val();
        
        if (newEmail) {
            // 이메일 변경 작업 수행 (예: AJAX 요청)
            alert('새 이메일로 변경 완료: ' + newEmail);
            $('#emailChangeModal').modal('hide');
            
            // 이메일 변경 완료 모달 열기
            $('#passwordChangeModal').modal('show');
            
            // 5초 후 프로필 페이지로 이동
            setTimeout(function() {
                window.location.href = 'baseball-profile.html';
            }, 5000);
        } else {
            alert('새 이메일을 입력해주세요.');
        }
    });

    // 이메일 변경 완료 모달에서 확인 버튼 클릭 시
    $('#modal-confirm-btn').click(function() {
        // 즉시 프로필 페이지로 이동
        window.location.href = 'baseball-profile.html';
    });

    // 이메일 변경 모달에서 취소 버튼 클릭 시
    $('.btn-secondary').click(function() {
        $('#emailChangeModal').modal('hide');
    });
});

