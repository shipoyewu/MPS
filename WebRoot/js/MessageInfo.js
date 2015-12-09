/**
 * 
 */

function setflag(messageid,groupid) {
	$.ajax({
		type: "POST",
		url: "ReadMessage",
		data: {"messageid":messageid,"groupid":groupid},
		success: function(data){
			location.reload();
		}
	})
}