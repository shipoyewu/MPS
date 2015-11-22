/**
 * 
 */
$(document).ready(function(){
  var num = 1;
	$("input#add").click(function(){
		num++;
	  var name="选项"+ num;
    $("<input name=\"option\" class=\"voteoption\" type=\"text\" placeholder=\""+name+"\"><br>").appendTo("div#waitforadd");
    
  });
});


