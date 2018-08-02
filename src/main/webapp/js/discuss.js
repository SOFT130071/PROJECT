if (sessionStorage.getItem("uid") === "" || sessionStorage.getItem("uid") === null) {
    console.log("dis");
    $("#input").attr("disabled", "disabled");
    $("#submit").attr("disabled", "disabled");
} else {
    $("#info").html("");
}

$.post("Servlet", {
    type: "board"
}, function (data) {
    console.log(data);
    let list = JSON.parse(data);
    $.each(list, function (name, value) {
        $(".panel-body").append("<h5>" + value.username + "</h5>\n" +
            "<p>" + value.content + "</p>" +
            "<span>#" + value.did + "</span><span>" + value.time + "</span><hr>")
    })
});

function submit() {
    $.post("Servlet", {
        type: "add_board",
        username: sessionStorage.getItem("username"),
        content: $("#input").val()
    }, function (data) {
        $(location).attr("href", "http://localhost:8080/discussionboard.jsp");
    });
}