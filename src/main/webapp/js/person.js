let content = [];
let kaike = [];
let xuanke = [];
let uid = sessionStorage.getItem("uid");

$.post(
    "Servlet",
    {
        type: "courseinfo",
        tid: uid
    },
    function (data) {
        console.log(data);
        let ret = JSON.parse(data);

        let j = 0;
        for (let m in ret) {
            kaike [j] = [];
            kaike [j][0] = ret[m].cid;
            kaike [j][1] = ret[m].title;


            if ($("#kaikelist li").length + "" === m) {
                let tr = document.createElement("li");
                let str = "<a href=\"course.jsp?cid=" + ret[m].cid + "\"><div>" + ret[m].title + "</div></a><div class=\"btn btn-primary btn-sm\" onclick=\"tongji(" + ret[m].cid + ", " + $("#kaikelist li").length + ", 'kaike', true)\">统计</div></li>";

                tr.innerHTML = str;

                $("#kaikelist").append(tr);
            }
            j++;
        }
        let tr = document.createElement("li");
        let str = "<li id=\"newcrs\"><a onclick=\"newCourse()\"><div class=\"glyphicon glyphicon-education\"></div>ADD NEW COURSE</a></li>";
        tr.innerHTML = str;

        $("#kaikelist").append(tr);
    }
);

$.post(
    "Servlet",
    {
        type: "courseinfo",
        uid: uid
    },
    function (data) {
        console.log(data);
        let ret = JSON.parse(data);

        let j = 0;
        for (let m in ret) {
            xuanke [j] = [];
            xuanke [j][0] = ret[m].cid;
            xuanke [j][1] = ret[m].title;


            if ($("#xuankelist li").length + "" === m) {
                let tr = document.createElement("li");
                let str = "<a href=\"course.jsp?cid=" + ret[m].cid + "\"><div>" + ret[m].title + "</div></a><div class=\"btn btn-primary btn-sm\" onclick=\"tongji(" + ret[m].cid + "," + $("#xuankelist li").length + ", 'xuanke', false)\">统计</div><div class=\"btn btn-primary btn-sm\" onclick = \"popup(" + ret[m].cid + ")\">退课</div></li>";

                tr.innerHTML = str;

                $("#xuankelist").append(tr);
            }
            j++;
        }
    }
);

$.post("Servlet", {
    type: "userinfo",
    uid: uid
}, function (data) {
    console.log(data);
    $("#title").html(JSON.parse(data).nickname);
});

$("#tabs button").click(function () {
    $("#tabs button").attr("class", "btn btn-default");
    $(this).attr("class", "btn btn-default active");
    $("section").each(function () {
        $(this).css("display", "none");
    });
    $("#" + $(this).attr("mark")).css("display", "block");
});

function tongji(cid, no, ke, tc) {
    $("#oooo").css("display", "block");
    $.post("Servlet",
        {
            type: "countrecord",
            cid: cid
        }, function (data) {
            console.log(data);
            $("#kechengming").text($("#" + ke + "list li a div")[no].innerText);

            $("#table").html("<tr>\n" +
                "                <th>No.</th><th>姓名</th><th>页面完成数</th>\n" +
                "            </tr>");

            let ret = JSON.parse(data);

            let j = 0;
            for (let m in ret) {
                content[j] = [];
                content[j][0] = m;
                content[j][1] = ret[m].uid;
                content[j][2] = ret[m].nickname;
                content[j][3] = ret[m].count;


                if ($("#table tr").length + "" === m) {
                    let tr = document.createElement("tr");
                    let str;
                    if (tc || ret[m].uid === uid + "")
                        str = "<td>" + m + "</td><td>" + ret[m].nickname + "</td><td>" + ret[m].count + "</td>";
                    else {
                        str = "<td>" + m + "</td><td>***</td><td>" + ret[m].count + "</td>";
                    }

                    tr.innerHTML = str;

                    $("#table").append(tr);
                }
                j++;
            }
        });
}

function popup(cid) {
    $("#figure").css("display", "block");
    $("#aaaaaa").attr("onclick", "tuike(" + cid + ")");
}

function closepopup() {
    $("#figure").css("display", "none");
}

function tuike(cid) {
    //TODO: 先验证密码 $("#figure input")[0].value 成功的话就退课
    $.post("Servlet", {
        type: "login",
        username: sessionStorage.getItem("username"),
        password: $("#figure input")[0].value
    }, function (data) {
        console.log(data);
        if (data < 60000) {
            $.post("Servlet", {
                type: "dropoutcourse",
                uid: uid,
                cid: cid
            }, function (data) {
                location.reload();
            });
        } else {
            $("#info").attr("placeholder", "wrong password");
        }
    })
}

function newCourse() {
    $("#newcrs").html("<input type=\"text\" name=\"title\" placeholder=\"title\"><textarea name=\"content\" placeholder=\"content\"></textarea><input type=\"text\" name=\"pic_url\" placeholder=\"Image\"><div class=\"btn btn-primary btn-sm\" onclick=\"subnc()\">SUBMIT</div>");
}

function subnc() {
    //TODO: 新建课程
    $.post("Servlet", {
        type: "add_course",
        teacher_id: uid,
        title: $("#newcrs input")[0].value,
        pic_url: $("#newcrs input")[1].value,
        content: $("#newcrs textarea")[0].value
    }, function (data) {
        location.reload();
    });
}
