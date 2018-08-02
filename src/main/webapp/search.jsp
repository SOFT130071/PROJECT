<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/search.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="nav.jsp" %>
<script>
    var tttt = "<%=request.getParameter("title")%>";
</script>
<main>

    <div class="input-group">
        <input type="text" class="form-control input-group-addon" id="ttext" placeholder="Title">
        <input type="text" class="form-control input-group-addon" id="ctext" placeholder="Content">
        <input type="text" class="form-control input-group-addon" id="ntext" placeholder="Teacher Name">
        <div class="input-group-btn" id="ddf">
            <button type="button" class="btn btn-default" id="sousuo" onclick="cccc()">搜索</button>
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu dropdown-menu-right" id="list">
                <li><a onclick=choose(0)>按热度降序</a></li>
                <li><a onclick=choose(1)>按热度升序</a></li>
                <li role="separator" class="divider"></li>
                <li><a onclick=choose(2)>按时间降序</a></li>
                <li><a onclick=choose(3)>按时间升序</a></li>
            </ul>
        </div>
    </div><!-- /.input-group -->

    <ul id="wtm">

        <li class="li">

        </li>
    </ul>

    <center>
        <div class="btn-group" role="group" aria-label="..." id="page">

        </div>
    </center>
</main>
<script src="js/search.js"></script>
</body>
</html>