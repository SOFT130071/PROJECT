function check() {
    let username = $("#username").val();
    let password = $("#password").val();
    if (username === undefined || username === "") {
        console.log("???");
        $("#us").html("<div class=\"glyphicon glyphicon-remove\"> invalid</div>");
        return;
    }
    if (password === undefined || password === "") {
        console.log(" ");
        $("#ps").html("<div class=\"glyphicon glyphicon-remove\"> invalid</div>");
        return;
    }
    $.post("Servlet", {
        type: "login",
        username: username,
        password: password
    }, function (data) {
        console.log(data);
        if (data < 60000) {
            console.log("success");
            sessionStorage.setItem("username", username);
            sessionStorage.setItem("uid", data);
            $(location).attr("href", "http://localhost:8080/index.jsp");
        } else {
            $("#us").html("<div class=\"glyphicon glyphicon-remove\"> maybe wrong</div>");
            $("#ps").html("<div class=\"glyphicon glyphicon-remove\"> maybe wrong</div>");
        }
    })
}