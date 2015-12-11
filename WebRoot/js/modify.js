/**
 * 
 */

function showCreate() {
	alert("修改已提交，刷新整个页面可同步结果！");
	return true;
}

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
    		alert("请至少选择一个群组成员！");
    		return false;
    	}
    	else{
    		if (!confirm("你确认要删除吗？")) return false;
            else {
                document.deleteg.submit();
                //return true;
            }
    	}
    }
    return false;
}