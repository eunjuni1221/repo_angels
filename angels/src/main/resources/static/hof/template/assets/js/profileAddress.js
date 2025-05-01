window.onload = function () {
	        var mapContainer = document.getElementById('map');
	        var mapOption = {
	            center: new kakao.maps.LatLng(37.566826, 126.9786567), // 초기 위치: 서울시청
	            level: 3
	        };

	        var map = new kakao.maps.Map(mapContainer, mapOption);
	        var geocoder = new kakao.maps.services.Geocoder();

	        // 사용자 주소 문자열 합치기
			var userAddress = /*[[${'"' + list.urAddress + '' + list.urDetailAddress + '"'}]]*/'';

	        if (userAddress && userAddress.trim() !== "") {
	            geocoder.addressSearch(userAddress, function(result, status) {
	                if (status === kakao.maps.services.Status.OK) {
	                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	                    // 지도 중심 이동
	                    map.setCenter(coords);

	                    // 마커 생성
	                    var marker = new kakao.maps.Marker({
	                        map: map,
	                        position: coords
	                    });
	                } else {
	                    console.warn("주소 검색 실패:", status);
	                }
	            });
	        } else {
	            console.warn("주소 없음");
	        }
	    };