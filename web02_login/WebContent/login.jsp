<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<script>
		//校验用户名
        //单词字符，长度8到20位
        function checkUsername() {
            //1.获取用户名值
            var username = $("#username").val();
            //定义正则，注意，{8,20}中间没空格，加空格错误！！！注意格式
            var reg_username = /^\w{8,20}$/;
            //判断正则，并给出提示信息
            var flag = reg_username.test(username);

            if (flag) {
                //用户名合法
                $("#username").css("border", "");
            } else {
                //用户名非法
                $("#username").css("border", "1px solid red");
            }

            return flag;
        }

        //校验密码
        function checkPassword() {
            //1.获取密码
            var password = $("#password").val();
            //定义正则，注意，{8,20}中间没空格，加空格错误！！！注意格式
            var reg_password = /^\w{8,20}$/;
            //判断正则，并给出提示信息
            var flag = reg_password.test(password);

            if (flag) {
                //密码合法
                $("#password").css("border", "");
            } else {
                //密码非法
                $("#password").css("border", "1px solid red");
            }

            return flag;
        }
	
		$(function () {
            //表单提交时，调用所有校验方法
            $("#registerForm").submit(function () {
                if (checkUsername() && checkPassword() && checkEmail() && checkName()
                    && checkTelephone() && checkBirthday() && checkCheck()) {
                    // 校验通过,发送ajax请求，提交表单的数据
                    alert("123");
                    $.post("user/registUserServlet", $(this).serialize(), function (data) {
                        // 处理服务器响应的数据  data  {flag:true,errorMsg:"注册失败"}
                        if (data.flag) {
                            // 注册成功，跳转到register_ok.html页面
                            location.href = "register_ok.html";
                        } else {
                            // 注册失败
                            $("#errorMsg").html(data.errorMsg);
                        }
                    });
                }

                //2.不让页面跳转
                return false;
                //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
            });
	</script>
</head>
<body>

	<h1>登錄頁面</h1>
	<form id="registerForm">
		<table border="1" width="400">
			<tr>
				<td>用戶名</td>
				<td><input type="text" name="username" id="username"/></td>
			</tr>
			<tr>
				<td>密碼</td>
				<td><input type="password" name="password" id="password"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登入" /></td>
			</tr>
		</table>
	</form>

</body>
</html>
