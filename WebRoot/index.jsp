<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="../css/style.css" />
	
	<script type="text/javascript">
		function getYzm(){
			document.getElementById("veryCode").src="YzmControl";
		}
	
		function getFroms(){
			var isSuccess = false;
			var name = document.getElementsByName("name")[0].value;
			var psw = document.getElementsByName("password")[0].value;
	//		var code = document.getElementsByName("veryCode")[0].value;
			if(name == ""){
				document.getElementById("nameSpan").innerHTML = "用户名不能为空!";
				document.getElementById("nameSpan").className = "error";
				return false;
			}else if(psw == ""){
				document.getElementById("passWordSpan").innerHTML = "密码不能为空!";
				document.getElementById("passWordSpan").className = "error";
				return false;
			}else{
				isSuccess = true;
			}
			/* else if(code == ""){
				document.getElementById("codeSpan").innerHTML = "验证码不能为空!";
				document.getElementById("codeSpan").className = "error";
				return false;
			}else{
				//$.post("servlet路径",{参数},回调函数);
				$.post("CheckServlet",{"veryCode":veryCode},huidiao);
			} */
			if(isSuccess){
				//提交
				document.getElementById("loginForm").submit();
			}
			return false;
		}
	</script>
  </head>
  
  <body>
	<div id="register" class="wrap">
		<div class="shadow">
			<div class="box">
				<h3>注册</h3>
				<form id="loginForm" method="post"
					action="${pageContext.request.contextPath}/dbAction_register.do">
					<table>
						<tr>
							<td class="field">用户名：</td>
							<td><input class="text" type="text" name="name" /><span
								id="nameSpan">${fail}</span>
							</td>
						</tr>
						<tr>
							<td class="field">密码：</td>
							<td><input class="text" type="password" name="password"
								 /><span id="passWordSpan"></span>
							</td>
						</tr>
						<tr>
							<td class="field">验证码：</td>
							<td><input class="text verycode" type="text" name="veryCode" />
								<img id="veryCode" src="YzmControl" onclick="getYzm()" /> <span
								id="codeSpan"></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<label class="ui-green">
									<input type="button" name="button" value="立即注册" onclick="getFroms()" />
								</label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>

</body>
</html>
