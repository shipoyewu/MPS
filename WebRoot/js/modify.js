/**
 * 
 */

function CheckAll(groupdownid, do_check) {
    if (typeof(groupdownid) == 'undefined') return;
    var cnt = (typeof(groupdownid.length) != 'undefined') ? groupdownid.length: 0;
    if (cnt) {
        for (var i = 0; i < cnt; i++) groupdownid[i].checked = do_check;
    } else groupdownid.checked = do_check;
}

function deleteYN() {

    document.deleteg.action = "Delete";
    if (typeof(deleteg.elements['groupdownid']) == 'undefined') {
        return false;
    } else {
    	var flag = false;
    	for(var i = 0;i < deleteg.groupdownid.length;i++){
    		if(deleteg.groupdownid[i].checked == true){
    			flag = true;
    		}
    	}
    	if(!flag){
    		alert("请选择一个群组");
    		return false;
    	}
    	else{
    		if (!confirm('确实要删除吗?')) return false;
            else {
                document.deleteg.submit();
                //return true;
            }
    	}
    }
    return false;
}