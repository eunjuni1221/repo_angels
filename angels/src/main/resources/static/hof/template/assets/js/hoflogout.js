const URL_SIGNIN_FORM_XDM = "/mainHof"
const URL_SIGNOUT_PROC_XDM = "/login/signoutHofProc"
const URL_INDEX_VIEW_XDM = "/v1/infra/index/indexXdmView"

$("#btnSignout1, #btnSignout2").on("click", function(){
	$.ajax({
		async: true,
		cache: false,
		type: "post",
		url: URL_SIGNOUT_PROC_XDM,
		data: {},
		success: function(response) {
			if (response.rt == "success") {
				location.href = URL_SIGNIN_FORM_XDM;
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("ajaxUpdate " + textStatus + " : " + errorThrown);
		}
	});
});