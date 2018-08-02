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
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/coursepage.css">
    <link rel="stylesheet" href="css/nav.css">
</head>
<body>
<script>
    var cp_id =<%= request.getParameter("cpid")%>;
    var c_id =<%= request.getParameter("cid")%>;
</script>
<script src="<%=basePath%>js/coursepage.js"></script>
<script src="<%=basePath%>js/nav.js"></script>
<span id="nav-top-tab" onclick="navTopClick()">︿</span><nav id="nav-top">
    <div class="col-lg-3">
        欢迎来到 <span style="color:rgb(134, 187, 255)">MOOC</span>，<span id="user"><a href="login.jsp">登录</a>或<a href="register.jsp">注册</a></span>。 </div>
    <div class="col-lg-4">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for..." id="search-text">
            <span class="input-group-btn">
                             <button class="btn btn-default" type="button"><div class="glyphicon glyphicon-search"></div></button>
                          </span>
        </div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
</nav>

<span id="nav-tab" onclick="navClick()">〉</span><nav id="right"><div id="nav-lower">
    <ul id="coursepage-list">
    </ul>
</div><div id="nav-upper">
    <p id="cp-title"></p><p id="cp-content"></p>
    <ul id="pagecontent-list">
    </ul>
</div></nav>
<iframe id="player" src="" frameborder="0" allowfullscreen></iframe>
</body>
</html>
