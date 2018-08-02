<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/course.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="nav.jsp" %>
<script>
    let cid = <%=request.getParameter("cid")%>;//一定要保留这个！！
</script>
<h1 id="title">asdfasdfsadfsa</h1>
<h3 id="tname">asdfasdf</h3>
<div class="row">
    <figure class="col-lg-5" id="course-info">
        <div id="img"><img src="http://img5.imgtn.bdimg.com/it/u=1057415789,3350935000&fm=27&gp=0.jpg"></div>
        <figcaption id="descrp">asdfa asdfas sadf asdf asd fasd f ds fa sdf asd f sad fasd f asd f asd fas df asd fas df
            asd f
        </figcaption>
        <div class="btn btn-primary" id="select" onclick="select()">选课</div>
    </figure>

    <section id="content" class="col-lg-3">
        <div class="btn btn-primary" id="clist">课程列表</div>
        <ul id="ulist"></ul>
    </section>

    <section id="resourse" class="col-lg-2">
        <div class="btn btn-primary" id="rlist">资源列表</div>
        <ul id="urlist"></ul>
    </section>
</div>
<hr/>
<div class="row">
    <div class="btn btn-primary" id="hlist">作业列表</div>
    <ul id="uhlist"></ul>
</div>
<script src="js/course.js"></script>
</body>
</html>