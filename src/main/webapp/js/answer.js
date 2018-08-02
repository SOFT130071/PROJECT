var uid = sessionStorage.getItem("uid");

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

$.post(
    "Servlet",
    {
        type: "answer",
        hid: hid,
    },
    function (data) {
        console.log(data);
        let ret = JSON.parse(data.replace("/\r\n/g", "<br>"));

        for (let m in ret) {
            console.log(m);
            let li = document.createElement("li");
            li.innerHTML = "<p id=\"d" + ret[m].uid + "\">答案：<br><div>" + ret[m].content + "</div></p><input id=\"i" + ret[m].uid + "\" type=\"number\" placeholder=\"GRADE\">";
            $("ul")[0].append(li);
        }

        let li = document.createElement("li");
        li.innerHTML = "<div class=\"btn btn-primary\" onclick=\"sub()\">提交</div>";
        $("ul")[0].append(li);
    }
);

function sub() {
    for (let i = 0; i < $("ul li").length - 1; i++) {
        let uid = $("ul li input")[i].id.substr(1);
        let value = $("ul li input")[i].value;

        $.post("Servlet", {
            type: "answer",
            uid: uid,
            hid: hid,
            grade: value
        }, function (data) {
            history.go(-1);
        });
    }
}
