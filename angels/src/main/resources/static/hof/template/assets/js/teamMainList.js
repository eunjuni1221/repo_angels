	const form = document.getElementById("formList");
	const seq = form.querySelector("input[name=tmSeq]");

	// goForm을 명시적으로 전역 함수로 선언
	function goForm(keyValue) {
	    seq.value = keyValue;
	    form.action = "/team/TeamHofMain";
	    form.submit();
	}
	
