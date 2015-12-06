function messa() {
    var a = $.blinkTitle.show();
    setTimeout(function() {
        $.blinkTitle.clear(a)
    },
    8e3)
}

$(document).ready(function() {
	//a为当前的的index，b为sender头像，c为receiver头像，d为receiver的姓名，w为sender的id,uname为名字,u为receiver的id
	var a = 3,
    b = $("#master").attr("src"),
    c = "",
    d = "",
    w = $("#master").attr("name"),
    uname=$("#user").text(),
    u = null,
    first=true;
	
	function e() {
        function h() { - 1 != g.indexOf("*#emo_") && (g = g.replace("*#", "<img src='img/").replace("#*", ".gif'/>"), h());
        }
        if(c == ""){
        	alert("请选择一名好友进行聊天！");
        	return ;
        }
        var e = new Date,
        f = "";
        f += e.getFullYear() + "-",
        f += e.getMonth() + 1 + "-",
        f += e.getDate() + "  ",
        f += e.getHours() + ":",
        f += e.getMinutes() + ":",
        f += e.getSeconds();
        var g = $("#textarea").val();
        h();
        var i = "<div class='message clearfix'><div class='user-logo'><img src='" +
            b + 
            "'/>" + "</div>" + "<div class='wrap-text'>" + "<h5 class='clearfix'>"+
            uname +
            "</h5>" + "<div>" + 
            g + 
            "</div>" + "</div>" + "<div class='wrap-ri'>" + "<div clsss='clearfix'><span>" + 
            f + 
            "</span></div>" + "</div>" + "<div style='clear:both;'></div>" + "</div>"; 
        null != g && "" != g ? ($(".mes" + a).append(i), $(".chat01_content").scrollTop($(".mes" + a).height()), $("#textarea").val("")) : alert("\u8bf7\u8f93\u5165\u804a\u5929\u5185\u5bb9!");
    }
	
	
    $(".close_btn").click(function() {
    }),
    $(".chat03_content li").mouseover(function() {
        $(this).addClass("hover").siblings().removeClass("hover")
    }).mouseout(function() {
        $(this).removeClass("hover").siblings().removeClass("hover")
    }),
    
    $(".chat03_content li").dblclick(function() {
    	
    	if(first==true){
    		first=false;
    		setInterval(function getMessage(){
    			$.ajax({
    				type: "POST",
    				url: "GetLetter",
    				data: {"sender":u,"recevier":w},
    				success: function(data){
    					var g;
    					var jsonList=eval("("+data+")");
    					
    					function h() { - 1 != g.indexOf("*#emo_") && (g = g.replace("*#", "<img src='img/").replace("#*", ".gif'/>"), h());
				        }
    					if(jsonList.length!=0){
    						messa();
    					}
    					for(var j = 0;j < jsonList.length;j++){
    						var f = jsonList[j].time;
    						g = jsonList[j].content;
    						h();
    						var i = "<div class='message clearfix'>" + "<div class='user-logo'>" + "<img src='" + 
				            c + 
				            "'/>" + "</div>" + "<div class='wrap-text'>" + "<h5 class='clearfix'>" +
				            d + "</h5>" + "<div>" + 
				            g + 
				            "</div>" + "</div>" + "<div class='wrap-ri'>" + "<div clsss='clearfix'><span>" + 
				            f + "</span></div>" + "</div>" + "<div style='clear:both;'></div>";
    						($(".mes" + a).append(i), $(".chat01_content").scrollTop($(".mes" + a).height()));
					        
    					}
				   }
    			});
    		},
    	    50);
    	}
    	var b = this.id;
        u = b;
    	a = $(this).index() + 1,
        c = "userdata/" + b + ".jpg",
        d = $(this).find(".chat03_name").text(),
        $(".chat01_content").scrollTop(0),
        $(this).addClass("choosed").siblings().removeClass("choosed"),
        $(".talkTo a").text($(this).children(".chat03_name").text()),
        $(".mes" + a).show().siblings().hide()
    }),
    
    $(".ctb01").mouseover(function() {
        $(".wl_faces_box").show()
    }).mouseout(function() {
        $(".wl_faces_box").hide()
    }),
    $(".wl_faces_box").mouseover(function() {
        $(".wl_faces_box").show()
    }).mouseout(function() {
        $(".wl_faces_box").hide()
    }),
    $(".wl_faces_close").click(function() {
        $(".wl_faces_box").hide()
    }),
    $(".wl_faces_main img").click(function() {
        var a = $(this).attr("src");
        $("#textarea").val($("#textarea").val() + "*#" + a.substr(a.indexOf("img/") + 4, 6) + "#*"),
        $("#textarea").focusEnd(),
        $(".wl_faces_box").hide()
    }),
    $(".chat02_bar img").click(function() {
    	if(u!=null)
            $.ajax({
    			type: "POST",
    			url: "SendLetter",
    			data: {"sender":w,"recevier":u,"content":$("#textarea").val()},
    			success: function(data){
    			}
    	});
        e();
        
        
    }),
    document.onkeydown = function(a) {
        var b = document.all ? window.event: a;
        return 13 == b.keyCode ? (e(), !1) : void 0
    },
    $.fn.setCursorPosition = function(a) {
        return 0 == this.lengh ? this: $(this).setSelection(a, a)
    },
    $.fn.setSelection = function(a, b) {
        if (0 == this.lengh) return this;
        if (input = this[0], input.createTextRange) {
            var c = input.createTextRange();
            c.collapse(!0),
            c.moveEnd("character", b),
            c.moveStart("character", a),
            c.select()
        } else input.setSelectionRange && (input.focus(), input.setSelectionRange(a, b));
        return this
    },
    $.fn.focusEnd = function() {
        this.setCursorPosition(this.val().length)
    }
}),
function(a) {
	
    a.extend({
        blinkTitle: {
            show: function() {
                var a = 0,
                b = document.title;
                if ( - 1 == document.title.indexOf("\u3010")) var c = setInterval(function() {
                    a++,
                    3 == a && (a = 1),
                    1 == a && (document.title = "\u3010\u3000\u3000\u3000\u3011" + b),
                    2 == a && (document.title = "\u3010\u65b0\u6d88\u606f\u3011" + b)
                },
                500);
                return [c, b]
            },
            clear: function(a) {
                a && (clearInterval(a[0]), document.title = a[1])
            }
        }
    })
} (jQuery);


