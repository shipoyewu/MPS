var setting = {
			check: {
				enable: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view:{
				showLine:false,
				/*showIcon:false*/
			},		
			async: {
                enable: true,
                url:"userSideBar.jsp",
                dataFilter: null
            }
		};
	
		var data;
		var treeNodes;
		/*$(function(){
			$.ajax({
			async : true,
			cache: true,
			type: 'GET',
			dataType : "json",
			url: "/MPS/getUserTree",//请求的action路径
			error: function () {//请求失败处理函数
			alert('请求失败');
			},
			success:function(data){ //请求成功后处理函数。 
			alert(data);
		    $('#show').text(data);
			treeNodes = eval("("+data+")"); //把后台封装好的简单Json格式赋给treeNodes
			
			$.fn.zTree.init($("#treeDemo"), setting, treeNodes);
			}
			});
		});*/
			$.get("http://localhost:8080/MPS/getUserTree",function(data){  
	          
	            $('#result').text(data);//直接展示JSON数据  
	            fuzhi(data);  
	              
	        });  
		   function fuzhi(data){  
	                       
	           zNodes=eval("("+data+")");  
	   
	            $.fn.zTree.init($("#treeDemo"), setting, zNodes); 
		   }
		var code;
		
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
	
		