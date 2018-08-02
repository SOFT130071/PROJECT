<%--
  Created by IntelliJ IDEA.
  User: Veronica
  Date: 7/26/18
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>

<html>
<head>
    <title><%=request.getParameter("course")%> | MOOC</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/coursepage.css">
    <link rel="stylesheet" href="css/nav.css">
</head>
<body>
<script>
    if (sessionStorage.getItem("username") === null) {
        $(location).attr("href", "http://localhost:8080/index.jsp");
    }
    var cp_id =<%= request.getParameter("pid")%>;
    var c_id =<%= request.getParameter("cid")%>;
</script>
<script src="<%=basePath%>js/coursepage.js"></script>
<script src="<%=basePath%>js/nav.js"></script>
<span id="nav-top-tab" onclick="navTopClick()">︿</span>
<nav id="nav-top">
    <script>
        function search() {
            $(location).attr("href", "http://localhost:8080/search.jsp?title=" + $("#nav-jsp-search-text").val());
        }

        function board() {
            $(location).attr("href", "http://localhost:8080/discussionboard.jsp");
        }

        function logout() {
            sessionStorage.removeItem("uid");
            sessionStorage.removeItem("username");
            console.log("to index");
            location.reload();
        }
    </script>
    <div class="col-lg-3">
        欢迎来到 <a href="index.jsp" style="color:rgb(134, 187, 255)">MOOC</a>，<span id="nav-jsp-user"></span>。
        <script>
            console.log("6516" + sessionStorage.getItem("username"));
            if (sessionStorage.getItem("username") === "" || sessionStorage.getItem("username") === null)
                $("#nav-jsp-user").html("<a style=\"color: lightcyan\" href=\"login.jsp\">登录</a>或<a style=\"color: lightcyan\" href=\"register.jsp\">注册</a>");
            else
                $("#nav-jsp-user").html("<a style=\"color: lightcyan\" href=\"\">" + sessionStorage.getItem("username") + "</a>，<a style=\"color: lightcyan\" href=\"\" onclick=\"logout()\">登出</a>");
        </script>
    </div>
    <div class="col-lg-4">

        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for..." id="nav-jsp-search-text">
            <span class="input-group-btn">
                             <button class="btn btn-default" type="button" id="nav-jsp-search-button"
                                     onclick="search()"><span
                                     class="glyphicon glyphicon-search"></span></button>
                          </span>
        </div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
    <div class="col-lg-3">

        <div class="input-group">
            <span class="input-group-btn">
                             <button class="btn btn-primary" type="button" onclick="board()">讨论板</button>
                          </span>
        </div><!-- /input-group -->
    </div>
</nav>

<span id="nav-tab" onclick="navClick()">〉</span>
<nav id="right">
    <div id="nav-lower">
        <ul id="coursepage-list">
        </ul>
    </div>
    <div id="nav-upper">
        <p id="cp-title"></p>
        <p id="cp-content"></p>
        <ul id="pagecontent-list">
        </ul>
    </div>
</nav>
<iframe id="player" src="" frameborder="0" allowfullscreen></iframe>
</body>
</html>
