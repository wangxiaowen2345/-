<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车型管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/admin/css/styleForm.css">
		<script
			src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>


	</head>

	<body style="align: center; font-family: 'Microsoft YaHei';">

		<button onclick="ShowAddFrom();"
			style="margin-top: 20px; margin-left: 2%; background: #00a5a5; border: 0px; color: #ffffff; font-size: 15px; width: 150px; height: 30px;">
			添加车型
		</button>
		<div id="div1" style="margin-left: 2%; margin-top: 20px; width: 50%">
			<div id="left" style="background: #00a5a5; width: 20%">
				<font color="#ffffff">ID</font>
			</div>
			<div id="middle" style="background: #00a5a5; width: 50%">
				<font color="#ffffff">车型</font>
			</div>
			<div id="right" style="background: #00a5a5; width: 25%">
				<font color="#ffffff">操作</font>
			</div>


			<s:iterator value="AllModels()" var="son">
				<div id="left" style="width: 20%;overflow: hidden; text-overflow:ellipsis;white-space:nowrap;">
					<s:property value="#son.id" />
				</div>
				<div id="middle" style="width: 50%;overflow: hidden; text-overflow:ellipsis;white-space:nowrap;">
					<s:property value="#son.name" />
				</div>
				<div id="right" style="width: 25%">
					<a href='javascript:showUserData(<s:property value='#son.id' />,"<s:property value="#son.name" />");'>修改</a>
				</div>
			</s:iterator>


			<div id="bottom" style="width: 95%;margin-bottom: 50px"></div>
		</div>

		<div id="main">
			<div id="fullbg"></div>
			<div id="dialog" style="height: 150px;">
				<p class="close">
					<a href="javascript:closeBg();">关闭</a>
				</p>
				<div align="center" id="dialogbody" style="height: 288px;">
					<div
						style="width: 35%; float: left; display: inline; text-align: right; line-height: 30px;">
						车型：
						<br />
					</div>
					<div
						style="width: 65%; float: right; display: inline; text-align: left; line-height: 30px;">
						<input type="text" id="warehousename" style="width: 70%">
						</br>
						
			

					</div>
					
					<button onclick="AddPower();" style="margin-top:20px;">提交</button>
				</div>
			</div>

		</div>
	</body>
	<script type="text/javascript">
	var Id = 0;
	var tag = 0;

	function showUserData(sId,warehousename) {
		tag = 1;
		Id = sId;
		
		$("#warehousename").val(warehousename);
		
		showBg();

	}
	
	function ShowAddFrom(){
		tag = 0;
		$("#warehousename").val("");
	
		var name = document.getElementsByName("functionname");
		
		for(var i = 0;i<name.length;i++){
			name[i].checked = false;
		}
	
		showBg();
	
	}
	
	function AddPower(){
	
		var name = $("#warehousename").val();
		var functionid = document.getElementsByName("functionname");
		var a = "";
		
		if(name==""){
			alert("车型不能为空！");
			return;
		}
		
		
		
		if(tag == 0)
		$.post("<%= path%>/admin.action?function=addmodels", 
			{ value1:name},
   			function(data){
    			if(data=="\"Success\""){
    				alert("添加成功！");
    				location.reload();
    			}
    			if(data=="\"Name existence\"")
    				alert("车型重复，请更换名称");
    			if(data=="\"request error\"")
    				alert("请求错误！");
     			
   			}, "text");
   			
   		else
   		
   		$.post("<%= path%>/admin.action?function=updatemodels", 
			{ value1:name,value2:Id },
   			function(data){
    			if(data=="\"Success\""){
    				alert("更新成功！");
    				location.reload();
    			}
    			if(data=="\"Name existence\"")
    				alert("车型重复，请更换名称");
    			if(data=="\"request error\"")
    				alert("请求错误！");
     			
   			}, "text");
   			
   		
		
	}
	
	

	//显示灰色 jQuery 遮罩层 
	function showBg() {
		var bh = $("body").height();
		var bw = $("body").width();
		$("#fullbg").css( {
			height : '100%',
			width : bw,
			display : "block"
		});
		$("#dialog").show();
	}
	//关闭灰色 jQuery 遮罩 
	function closeBg() {
		$("#fullbg,#dialog").hide();
	}
</script>
</html>
