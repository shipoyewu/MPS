/**
 * 
 */

function validate_required(field, alerttxt) {
    with(field) {
        if (value == "" || isNaN(value)) {
            alert(alerttxt);
            return false;
        } else {
            return true;
        }
    }
}

function validate_form(expandgroup) {
    with(expandgroup) {
        if (validate_required(groupdownid, "\u7fa4\u7ec4\u0049\u0044\u5fc5\u987b\u4e3a\u6570\u5b57\u4e32\uff01") == false) {
            groupupid.focus();
            return false;
        }
    }
}

function validate_required(field, alerttxt) {
    with(field) {
        if (value == "" || isNaN(value)) {
            alert(alerttxt);
            return false;
        } else {
            return true;
        }
    }
}

function validate_form(applygroup) {
    with(applygroup) {
        if (validate_required(groupupid, "\u7fa4\u7ec4\u0049\u0044\u5fc5\u987b\u4e3a\u6570\u5b57\u4e32\uff01") == false) {
            groupupid.focus();
            return false;
        }
    }
}
function CheckAll(groupid, do_check) {
    if (typeof(groupid) == 'undefined') return;
    var cnt = (typeof(groupid.length) != 'undefined') ? groupid.length: 0;
    if (cnt) {
        for (var i = 0; i < cnt; i++) groupid[i].checked = do_check;
    } else groupid.checked = do_check;
}

function deleteYN() {
    //if(YN(this.form.elements['delBox'], this.checked)){if(!confirm('È·ÊµÒªÉ¾³ýÂð?')) return false;}else return false;
    document.deletegroup.action = "DeleteGroup";

    if (typeof(deletegroup.elements['groupid']) == 'undefined') {
        return false;
    } else {
        if (deletegroup.groupid.checked == true) {
            if (!confirm('\u786e\u5b9e\u8981\u5220\u9664\u5417\u003f')) return false;
            else {
                document.deletegroup.submit();
                //return true;
            }
        } else {
        	
            for (var i = 0; i < deletegroup.groupid.length && !flag; i++) {
                if (deletegroup.groupid[i].checked == true) {
                    if (!confirm('\u786e\u5b9e\u8981\u5220\u9664\u5417\u003f')){ 
                    	return false;
                    }
                    else {
                    	flag = true;
                        document.deletegroup.submit();
                        //return true;
                    }
                    
                }
            }
        }
        return false;
    }
    return false;
}

function Modify() {
    //alert("asdadsasd");
    document.deletegroup.action = "jsp/ModifyGroup.jsp";
    document.deletegroup.submit();
}