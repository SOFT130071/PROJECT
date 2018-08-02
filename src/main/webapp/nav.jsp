<%--
  Created by IntelliJ IDEA.
  User: JaneH
  Date: 2018/8/1
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<nav id="nav-jsp-nav-top" style="display: block; width: 100%; height: 4em;background-color: dimgray; padding: 0.7em">
    <div class="col-lg-3">
        <h4>
            欢迎来到 <a href="index.jsp" style="color:rgb(134, 187, 255)">MOOC</a>，<span id="nav-jsp-user">
        </span>。</h4>
        <script>
            console.log("6516" + sessionStorage.getItem("username"));
            if (sessionStorage.getItem("username") === "" || sessionStorage.getItem("username") === null)
                $("#nav-jsp-user").html("<a style=\"color: lightcyan\" href=\"login.jsp\">登录</a>或<a style=\"color: lightcyan\" href=\"register.jsp\">注册</a>");
            else
                $("#nav-jsp-user").html("<a style=\"color: lightcyan\" href=\"person.jsp\">" + sessionStorage.getItem("username") + "</a>，<a style=\"color: lightcyan\" href=\"\" onclick=\"logout()\">登出</a>");
        </script>
    </div>
    <div class="col-lg-4">

        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for..." id="nav-jsp-search-text">
            <span class="input-group-btn">
                             <button class="btn btn-default" type="button" style="height: 2.47em;"
                                     id="nav-jsp-search-button"
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
