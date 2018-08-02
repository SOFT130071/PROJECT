<%--
  Created by IntelliJ IDEA.
  User: JaneH
  Date: 2018/7/31
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register to MOOC</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login.css">
    <script src="js/register.js"></script>
</head>
<body>
<script>
    if (sessionStorage.getItem("username") !== null) {
        $(location).attr("href", "http://localhost:8080/index.jsp");
    }
</script>
<div class="card">
    <h1><span class="label label-primary">Register</span></h1>
    <div class="container card-body">

        <div class="input-group input-group-lg">
            <span class="input-group-addon">USERNAME </span>
            <input type="text" class="form-control" id="username" onblur="checkUsername()">
            <span class="input-group-addon alert" id="us"></span>
        </div>

        <div class="input-group input-group-lg">
            <span class="input-group-addon">PASSWORD </span>
            <input type="password" class="form-control" id="password" placeholder="大于6位 非纯数字" onblur="checkPassword()">
            <span class="input-group-addon alert" id="ps"></span>
        </div>

        <div class="input-group input-group-lg">
            <span class="input-group-addon">CONFIRM </span>
            <input type="password" class="form-control" id="confirm" onblur="checkConfirm()">
            <span class="input-group-addon alert" id="cs"></span>
        </div>

        <div class="input-group input-group-lg">
            <span class="input-group-addon">NICKNAME </span>
            <input type="text" class="form-control" id="nickname" onblur="checkNickname()">
            <span class="input-group-addon alert" id="ns"></span>
        </div>

        <div class="input-group input-group-lg">
            <span class="input-group-addon">   EMAIL  </span>
            <input type="text" class="form-control" id="email" onblur="checkEmail()">
            <span class="input-group-addon alert" id="es"></span>
        </div>

        <div class="input-group input-group-lg">
            <span class="input-group-addon"> CAPTCHA </span>
            <input type="text" class="form-control" id="verify" onchange="verify()" onblur="checkVerify()">
            <span class="input-group-addon btn btn-primary" onclick="mail()" id="send">SEND</span>
            <span class="input-group-addon alert" id="vs"></span>
        </div>

        <button type="button" class="btn btn-primary btn-lg" onclick="register()">submit</button>

    </div>
</div>

</body>
</html>
