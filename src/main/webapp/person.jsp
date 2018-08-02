<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/person.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="nav.jsp" %>
<script>
    if (sessionStorage.getItem("username") === null) {
        $(location).attr("href", "http://localhost:8080/index.jsp");
    }
</script>
<h1 id="title">比利</h1>

<figure id="figure" cid="-1"><input type="password" class="form-control"
                                    placeholder="Input your password to confirm this operation." id="info">
    <div class="btn btn-group">
        <div class="btn btn-primary" id="aaaaaa" onclick="tuike()">CONFIRM</div>
        <div class="btn btn-primary" onclick="closepopup()">CANCEL</div>
    </div>
</figure>

<div class="btn-group" role="group" id="tabs">
    <button type="button" mark="xuanke" class="btn btn-default active">我选的课</button>
    <button type="button" mark="kaike" class="btn btn-default">我开的课</button>
</div>

<div class="row">
    <div class="col-lg-4">
        <section id="kaike">
            <ul id="kaikelist">


            </ul>
        </section>

        <section id="xuanke">
            <ul id="xuankelist">
            </ul>
        </section>
    </div>
    <div class="col-lg-6" id="oooo">
        <h4 id="kechengming">asfdasdfas</h4>
        <table id="table">
            <tr>
                <th>No.</th>
                <th>姓名</th>
                <th>页面完成数</th>
            </tr>

        </table>
    </div>
</div>
<script src="js/person.js"></script>
</body>
</html>