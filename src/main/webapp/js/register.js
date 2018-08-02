let username_flag = false;
let password_flag = false;
let confirm_flag = false;
let nickname_flag = false;
let email_flag = false;
let verify_flag = false;

function mail() {
    if (!username_flag) return;
    if (!email_flag) return;
    $.post("Servlet", {
        type: "mail",
        username: $("#username").val(),
        email: $("#email").val()
    }, function (data) {
        console.log("check email" + data);
        $("#send").html("SEND<div class=\"glyphicon glyphicon-ok\"></div>")
    })
}

function checkUsername() {
    let username = $("#username").val();
    if (username === undefined || username === "")
        username_flag = false;
    else if (username.split("\'").length > 1) {
        $("#us").html("invalid");
        username_flag = false;
    }

    flag = false;

    $.post("Servlet", {
        type: "hasUsername",
        username: username
    }, function (data) {
        //console.log("check username" + data);
        if (data === "true") {
            username_flag = false;
            //console.log("exist "+flag);
            $("#us").html("exists.");
        } else {
            username_flag = true;
            //console.log("ok "+flag);
            $("#us").html("");
        }
    });
    //console.log("return "+flag);
}

function checkPassword() {
    if (!(/^(?![0-9]+$)[0-9A-Za-z]{6,}$/.test($("#password").val()))) {
        console.log("invalid");
        $("#ps").html("<div class=\"glyphicon glyphicon-remove\">invalid</div>")
        password_flag = false;
    } else {
        $("#ps").html("");
        password_flag = true;
    }
}

function checkConfirm() {
    let confirm = $("#confirm").val();
    if ($("#password").val() !== confirm) {
        $("#cs").html("<div class=\"glyphicon glyphicon-remove\">invalid</div>")
        confirm_flag = false;
    } else {
        $("#cs").html("");
        confirm_flag = true;
    }
}

function checkNickname() {
    let nickname = $("#nickname").val();
    if (nickname === undefined || nickname === "" || nickname.split("\'").length > 1) {
        $("#ns").html("<div class=\"glyphicon glyphicon-remove\">invalid</div>");
        nickname_flag = false;
    } else {
        $("#ns").html("");
        nickname_flag = true;
    }
}

function checkEmail() {
    const email_pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!email_pattern.test($("#email").val())) {
        $("#es").html("<div class=\"glyphicon glyphicon-remove\">invalid");
        email_flag = false;
    } else {
        $("#es").html("");
        email_flag = true;
    }
}

function checkVerify() {
    let verify = $("#verify").val();
    if (verify === undefined || verify === "" || verify.split("\'").length > 1) {
        $("#vs").html("<div class=\"glyphicon glyphicon-remove\">invalid</div>");
        verify_flag = false;
    } else {
        $("#vs").html("");
        verify_flag = true;
    }
}

function register() {
    if (!(username_flag && password_flag && confirm_flag && nickname_flag && email_flag && verify_flag)) return;
    let username = $("#username").val();
    $.post("Servlet", {
        type: "register",
        username: username,
        password: $("#password").val(),
        nickname: $("#nickname").val(),
        email: $("#email").val(),
        verify: $("#verify").val()
    }, function (data) {
        console.log("register" + data);
        if (data > 60000) {
            $("#vs").html("<div class=\"glyphicon glyphicon-remove\">invalid</div>");
        } else {
            sessionStorage.setItem("username", username);
            sessionStorage.setItem("uid", data);
            $(location).attr("href", "http://localhost:8080/index.jsp");
        }
    });
}

function verify() {
    console.log("asfdasd");
    $("#verify").val($("#verify").val().replace(/ /g, ""));
};
