$(document).ready(function(){
	var cc = "page";
	var maxw = 1;
	while(document.getElementById(cc+maxw) != null){
		maxw++;
	}
	var q=1;
	if(maxw <=2 ){
		$("#pageChangeTop").hide();
	}
	$("#pageChangeTop").click(function(){
	      var uk1="#page"+q;
		  q++;
		  var uk2="#page"+q;
		  $(uk1).hide(); 
		  $(uk2).show();  
		  $("#pageChangeButtom").show();
		  if($("#page"+maxw).is(":hidden")){	  
		  }else{
			  $("#pageChangeTop").hide(); 
		  }
		 
	  });
	$("#pageChangeButtom").click(function(){
		 var uk1="#page"+q;
		  q--;
		  var uk2="#page"+q;
		  $(uk1).hide(); 
		  $(uk2).show();  
 		if($("#page1").is(":hidden")){	  
		  }else{
			  $("#pageChangeButtom").hide(); 
		  }
	  }); 
	 $("#button").click(function(){
    	$(this).attr('disabled',"true");
    	//$(this).removeAttr("disabled");
  });
});

	function MenuSwitch(className){		
		this._elements = [];
		this._default = -1;
		this._className = className;
		this._previous = false;
	}
	MenuSwitch.prototype.setDefault = function(id){
		this._default = Number(id);
	}
	MenuSwitch.prototype.setPrevious = function(flag){
		this._previous = Boolean(flag);
	}
	MenuSwitch.prototype.collectElementbyClass = function(){
		this._elements = [];
		var allelements = document.getElementsByTagName("div");
		for(var i=0;i<allelements.length;i++){
			var mItem = allelements[i];
			if (typeof mItem.className == "string" && mItem.className == this._className){
				var h3s = mItem.getElementsByTagName("h3");
				var uls = mItem.getElementsByTagName("ul");
				if(h3s.length == 1 && uls.length == 1){
					h3s[0].style.cursor = "hand";					
					if(this._default == this._elements.length){
						uls[0].style.display = "block";	
					}else{
						uls[0].style.display = "none";	
					}
					this._elements[this._elements.length] = mItem;
				}				
			}
		}
	}
	MenuSwitch.prototype.open = function(mElement){
		var uls = mElement.getElementsByTagName("ul");
		uls[0].style.display = "block";
	}
	MenuSwitch.prototype.close = function(mElement){
		var uls = mElement.getElementsByTagName("ul");
		uls[0].style.display = "none";
	}
	MenuSwitch.prototype.isOpen = function(mElement){
		var uls = mElement.getElementsByTagName("ul");		
		return uls[0].style.display == "block";
	}
	MenuSwitch.prototype.toggledisplay = function(header){
		var mItem;
		if(window.addEventListener){
			mItem = header.parentNode;
		}else{
			mItem = header.parentElement;
		}
		if(this.isOpen(mItem)){
			this.close(mItem);
		}else{
			this.open(mItem);
		}		
		if(!this._previous){
			for(var i=0;i<this._elements.length;i++){
				if(this._elements[i] != mItem){				
					var uls = this._elements[i].getElementsByTagName("ul");
					uls[0].style.display = "none";		
				}
			}
		}
	}	
	MenuSwitch.prototype.init = function(){		
		var instance = this;
		this.collectElementbyClass();
		if(this._elements.length==0){
			return;
		}
		for(var i=0;i<this._elements.length;i++){
			var h3s = this._elements[i].getElementsByTagName("h3");			
			if(window.addEventListener){
				h3s[0].addEventListener("click",function(){instance.toggledisplay(this);},false);
			}else{
				h3s[0].onclick = function(){instance.toggledisplay(this);}
			}
		}
	}