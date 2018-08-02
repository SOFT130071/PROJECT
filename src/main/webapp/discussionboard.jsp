<%--
  Created by IntelliJ IDEA.
  User: JaneH
  Date: 2018/8/1
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MOOC discussion</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/discuss.css">

</head>
<body>
<%@ include file="nav.jsp" %>
<div id="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Discussion Board</h3>
        </div>
        <div class="panel-body">

        </div>
        <div class="panel-footer"><textarea cols="140" rows="10" id="input"></textarea>
            <div id="infobox">
                <button class="btn btn-primary" type="button" id="submit" onclick="submit()">提交</button>
                <span id="info">请先<a href='login.jsp'>登陆</a>或<a href='register.jsp'>注册</a></span>
            </div>

        </div>
    </div>
</div>
<script src="js/discuss.js"></script>
</body>
</html>
