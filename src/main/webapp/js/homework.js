$.post(
    "Servlet",
    {
        type: "homeworklist",
        hid: hid
    },
    function (data) {
        let ret = JSON.parse(data);

        $("#title").text(ret[0].title);
        $("#dscrp").text(ret[0].content);
    }
);

$("#post").click(function () {
    $.post(
        "Servlet",
        {
            type: "answer",
            hid: hid,
            uid: sessionStorage.getItem("uid"),
            content: $("#content").val().replace("/\n/g", "<br>")
        },
        function (data) {
            history.go(-1);
        }
    )
});