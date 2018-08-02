var content = [];
var resource = [];
var hmwk = [];
let teacher_id;
let edit = false;  // teacher
let joined = false;

//post course
// let course = "{\"cid\":\"13\",\"title\":\"鬼畜入门\",\"teacher_id\":\"1\",\"pic_url\":\"https://pic1.zhimg.com/80/v2-cbaf4ecb15631cc33b3726fab733e04c_hd.jpg\",\"content\":\"初步认识鬼畜\"}";
// getcourse(course);
$.post("Servlet", {
        type: "courseinfo",
        cid: cid
    }, function (data) {
        getcourse(data);

//post name
// let teacher = "{\"uid\":\"1\",\"username\":\"root\",\"nickname\":\"比利王\",\"email\":\"lvelvelve\",\"authority\":\"1\"}";
// getteacher(teacher);
        $.post("Servlet", {
                type: "userinfo",
                uid: teacher_id
            }, function (data) {
                getteacher(data);
            }
        );
        //post coursepage
// let coursepage = "{\"0\":{\"pid\":\"2346\",\"title\":\"教你做鬼畜，教不会我吃屎！\",\"content\":\"这里是爱装逼的卖萌猫\r\n今儿带来了鬼畜教学\r\n希望对大家有帮助\r\n同时也希望大家点赞！/(ㄒoㄒ)/~~\r\n\",\"number\":\"1\",\"url\":\"https://www.bilibili.com/video/av27888158/\"},\"1\":{\"pid\":\"2347\",\"title\":\"FL Studio 12（水果）扒谱基础教程\",\"content\":\"曲速测试软件：MixMeister BPM Analyzer（下载地址：http://www.pc6.com/softview/SoftView_84975.html）\r\n各位大佬多点点赞呗，新星计划需要点赞数23333\r\n曲速测试软件建议大家自己找纯净版，我上面发的链接有捆绑软件\",\"number\":\"1.1\",\"url\":\"https://www.bilibili.com/video/av27802765/\"},\"2\":{\"pid\":\"2348\",\"title\":\"金坷垃BB素材\",\"content\":\"自制，你的耳机没有坏，这个就是没有声音的，共13个，就不分P了度盘自取：1kVCz4PL，u7cp。不允许商用，有事请私信\",\"number\":\"2\",\"url\":\"https://www.bilibili.com/video/av8591566/\"},\"3\":{\"pid\":\"2349\",\"title\":\"【鬼畜素材合集】比鬼畜更加鬼畜的鬼畜素材（看缘分更新）\",\"content\":\"灵感来源av1731405不过TA已经不更新了\r\n百度链接https://pan.baidu.com/s/1c21yHQK\r\n因为审核原因度盘里的压缩包都是拿数字命名的，但因更新次数太多我也搞不清顺序了，所以尽量下载所有文件，如要下单个视频请搜索 bilibili唧唧下载工具 有电脑版也有手机版，打开后搜索视频的av号就能找到，av号就在视频的网址中，没有通过B站审核的视频请到度盘内下载\r\n\",\"number\":\"3\",\"url\":\"https://www.bilibili.com/video/av10298469\"}}";
// getcontent(coursepage);
        $.post("Servlet", {
                type: "pagelist",
                cid: cid
            }, function (data) {
                getcontent(data);
            }
        );

        //post resource
// let res = "{\"0\":{\"cid\":\"13\",\"number\":\"1\",\"url\":\"https://zh.moegirl.org/%E9%AC%BC%E7%95%9C\"},\"1\":{\"cid\":\"13\",\"number\":\"1.1\",\"url\":\"https://zh.moegirl.org/%E5%93%B2%E2%99%82%E5%AD%A6#\"},\"2\":{\"cid\":\"13\",\"number\":\"2\",\"url\":\"https://www.bilibili.com/video/av106/index_1.html\"}}";
// getres(res);
        $.post("Servlet", {
                type: "resourcelist",
                cid: cid
            }, function (data) {
                getres(data);
            }
        );
        //post homework
// let homework = "{\"0\":{\"hid\":\"1\",\"cid\":\"13\",\"start_time\":\"2018-08-01\",\"title\":\"pump♂it\",\"content\":\"https://www.bilibili.com/video/av1812439/\",\"end_time\":\"2019-08-01\"},\"1\":{\"hid\":\"2\",\"cid\":\"13\",\"start_time\":\"2018-08-02\",\"title\":\"如何让白鼠爱上学习\",\"content\":\"https://wkaksnfd kajsdl ww.bilibili.com/video/av2909020\",\"end_time\":\"2018-08-22\"},\"2\":{\"hid\":\"3\",\"cid\":\"13\",\"start_time\":\"2018-08-02\",\"title\":\"有何不可\",\"content\":\"https://www.bilibili.com/video/av2760505/\",\"end_time\":\"2018-10-03\"}}";
// gethomework(homework);
        $.post("Servlet", {
                type: "homeworklist",
                cid: cid
            }, function (data) {
                gethomework(data);
            }
        );

        if (edit) {
            let a = $("#title").text();
            $("#title").html("<a onclick=\"titleclick()\">" + a + "<span class=\"glyphicon glyphicon-edit\"></span></a>");
        }

        if (sessionStorage.getItem("uid") !== null) {
            $.post("Servlet", {
                type: "hasJoin",
                uid: sessionStorage.getItem("uid"),
                cid: cid
            }, function (data) {
                console.log(data);
                if (data === "true") {
                    console.log("joined");
                    joined = true;
                }
                if (joined === true) {
                    console.log("joined");
                    $("#select").html("退课");
                } else if (edit === true) {
                    $("#select").attr("disabled", "disabled");
                } else {
                    $("#clist").attr("disabled", "disabled");
                    $("#ulist").css("display", "none");
                    $("#rlist").attr("disabled", "disabled");
                    $("#urlist").css("display", "none");
                    $("#hlist").attr("disabled", "disabled");
                    $("#uhlist").css("display", "none");
                }
            });
        } else {
            $("#clist").attr("disabled", "disabled");
            $("#ulist").css("display", "none");
            $("#rlist").attr("disabled", "disabled");
            $("#urlist").css("display", "none");
            $("#hlist").attr("disabled", "disabled");
            $("#uhlist").css("display", "none");
        }
    }
);

function gethomework(data) {
    let ret = JSON.parse(data.replace(/\r\n/g, "<br>"));

    let j = 0;
    for (let i in ret) {
        hmwk[j] = [];
        let k = hmwk[j];
        k    [0] = ret[i].hid;         //hid
        k    [1] = ret[i].cid;         //cid
        k    [2] = ret[i].title;       //title 
        k    [3] = ret[i].content;     //content
        k    [4] = ret[i].start_time;  //starttime
        k    [5] = ret[i].end_time;    //endtime

        j++;
    }
}


function getres(data) {
    let ret = JSON.parse(data.replace(/\r\n/g, "<br>"));

    let j = 0;
    for (let i in ret) {
        resource[j] = [];
        let k = resource[j];
        k        [0] = ret[i].number;  //number
        k        [1] = ret[i].cid;     //cid
        k        [2] = ret[i].url;     //url

        j++;
    }
}

function getteacher(data) {
    let ret = JSON.parse(data.replace(/\r\n/g, "<br>"));
    $("#tname").text(ret.nickname);
}

function getcourse(data) {
    let ret = JSON.parse(data.replace(/\r\n/g, "<br>"));

    $("#title").text(ret.title);
    document.title = ret.title + " | MOOC";
    teacher_id = ret.teacher_id;
    edit = (teacher_id === sessionStorage.getItem("uid"));
    $("#course-info img").attr("src", ret.pic_url);
    $("#course-info figcaption").text(ret.content);
}

function getcontent(data) {
    let ret = JSON.parse(data.replace(/\r\n/g, "<br>"));
    let j = 0;
    for (let i in ret) {
        content[j] = [];
        let k = content[j];
        k       [0] = ret[i].number;  //number
        k       [1] = ret[i].pid;     //cp_id
        k       [2] = ret[i].title;   //title

        j++;
    }
}

function select() {
    if (edit)
        return;
    if (joined) {
        $.post("Servlet", {
            type: "dropoutcourse",
            uid: sessionStorage.getItem("uid"),
            cid: cid
        }, function (data) {
            location.reload();
        });
    } else if (sessionStorage.getItem("uid") !== null) {
        $.post("Servlet", {
            type: "joincourse",
            uid: sessionStorage.getItem("uid"),
            cid: cid
        }, function (data) {
            location.reload();
        });
    } else {
        location.href = "register.jsp";
    }
}

$("#hlist").click(function () {
    if ($("#uhlist").html() === "") {
        for (let m = 0; m < hmwk.length; m++) {
            let li = document.createElement("li");
            let str;
            if (edit)
                str = "<div class=\"l-div\"><p><a href=\"" + "answer.jsp?hid=" + hmwk[m][0] + "\"><h4>" + hmwk[m][2] + "</h4></a>" + hmwk[m][3] + "</p><a onclick=\"ahlistclick(" + m + ")\"><p>From: " + hmwk[m][4] + "<br>To: " + hmwk[m][5] + "</p><span class=\"glyphicon glyphicon-edit\"></span></a></div>";
            else
                str = "<div class=\"l-div\"><a href=\"" + "homework.jsp?hid=" + hmwk[m][0] + "\"><div class=\"l-div2\"><p><h4>" + hmwk[m][2] + "</h4>" + hmwk[m][3] + "</p><p>From: " + hmwk[m][4] + "<br>To: " + hmwk[m][5] + "</p></div></a></div>";
            li.innerHTML = str;

            $("#uhlist").append(li);
        }
        if (edit) {
            let li = document.createElement("li");
            let str = "<div class=\"l-div\"><a onclick=\"ahlistclick(" + hmwk.length + ")\"><div class=\"l-div2\"><span class=\"glyphicon glyphicon-edit\"></span></div></a></div>";
            li.innerHTML = str;
            $("#uhlist").append(li);
        }
    }
});

function ahlistclick(n) {
    $(".l-div")[n].innerHTML = "<input type=\"text\" placeholder=\"title\" name=\"title\"><textarea placeholder=\"content\" name=\"content\"></textarea><p>From: </p><input type=\"date\" placeholder=\"from\" name=\"from\"><p>To: </p><input type=\"date\" placeholder=\"to\" name=\"to\"><button class=\"btn btn-primary submit\" onclick=\"ahlcsub(" + n + ")\">SUBMIT</button><button class=\"btn btn-primary submit\" onclick=\"ahlcdel(" + n + ")\">DELETE</button>";
}

function ahlcsub(n) {

    let name = $(".l-div")[n].getElementsByTagName("input")[0].value;
    let content = $(".l-div")[n].getElementsByTagName("textarea")[0].value.replace(/\n/g, "<br>");
    let from = $(".l-div")[n].getElementsByTagName("input")[1].value;
    let to = $(".l-div")[n].getElementsByTagName("input")[2].value;

    if (n === hmwk.length)
        $.post("Servlet", {
                type: "homework",
                cid: cid,
                title: name,
                content: content,
                start_time: from,
                end_time: to
            }, function (data) {
                console.log("%s", data);
            }
        );
    else
        $.post("Servlet", {
                type: "homework",
                hid: hmwk[n][0],
                cid: cid,
                title: name,
                content: content,
                start_time: from,
                end_time: to
            }, function (data) {
                console.log("%s", data);
            }
        );
    location.reload();
}

function ahlcdel(n) {
    if (n === $("#uhlist li").length - 1) {
        location.reload();
        return;
    }
    let id = hmwk[n][0];
    $.post("Servlet", {
            type: "homework",
            hid: id
        }, function (data) {
        }
    );
    location.reload();
}

$("#rlist").click(function () {
    if ($("#urlist").html() === "")
        for (let m = 0; m < resource.length; m++) {
            let li = document.createElement("li");

            let span = document.createElement("span");
            span.setAttribute("class", "badge list" + resource[m][0].split(".").length);
            span.innerText = resource[m][0];
            li.appendChild(span);

            if (edit) {
                let c = document.createElement("a");
                c.setAttribute("onclick", "arlistclick(" + m + ")");
                let a = document.createElement("span");
                a.innerText = resource[m][2];
                c.appendChild(a);
                let b = document.createElement("span");
                b.setAttribute("class", "glyphicon glyphicon-edit");
                c.appendChild(b);

                li.appendChild(c);
            }
            else {
                let a = document.createElement("a");
                a.setAttribute("href", resource[m][2]);
                a.innerText = resource[m][2];
                li.appendChild(a);
            }
            $("#urlist").append(li);
        }

    if (edit) {
        let li = document.createElement("li");
        let str = "<a onclick=\"arlistclick(" + resource.length + ")\"><span class=\"badge list1\">ADD</span><span class=\"glyphicon glyphicon-edit\"></span></a>";
        li.innerHTML = str;
        $("#urlist").append(li);
    }
});

function arlistclick(n) {
    if (n === resource.length) {
        $("#urlist li")[n].innerHTML = "<input type=\"text\" name=\"number\" placeholder=\"No.\"><input type=\"text\" name=\"content\" placeholder=\"content\"><button class=\"btn btn-primary submit\" onclick=\"arlcsub(" + n + ")\">SUBMIT</button><button class=\"btn btn-primary submit\" onclick=\"arlcdel(" + n + ")\">DELETE</button>";

    }
    else
        $("#urlist li")[n].innerHTML = "<input type=\"text\" name=\"number\" disabled='disabled' value=\"" + resource[n][0] + "\"><input type=\"text\" name=\"content\" placeholder=\"content\"><button class=\"btn btn-primary submit\" onclick=\"arlcsub(" + n + ")\">SUBMIT</button><button class=\"btn btn-primary submit\" onclick=\"arlcdel(" + n + ")\">DELETE</button>";
}

function arlcsub(n) {
    let ab = $("#urlist li")[n].getElementsByTagName("input")[0].value;

    let content = $("#urlist li")[n].getElementsByTagName("input")[1].value;

    if (n === $("#urlist li").length - 1)
        $.post("Servlet", {
                type: "aresource",
                cid: cid,
                number: ab,
                url: content,
            }, function (data) {
            }
        );
    else
        $.post("Servlet", {
                type: "mresource",
                cid: cid,
                number: resource[n][0],
                url: content,
            }, function (data) {
            }
        );
    location.reload();
}

function arlcdel(n) {
    if (n === $("#urlist li").length - 1) {
        location.reload();
        return;
    }
    let number = resource[n][0];
    let cid = resource[n][1];
    $.post("Servlet", {
            type: "dresource",
            cid: cid,
            number: number
        }, function (data) {
        }
    );
    location.reload();
}

$("#clist").click(function () {
    if ($("#ulist").html() === "")
        for (let m = 0; m < content.length; m++) {
            let li = document.createElement("li");

            let span = document.createElement("span");
            span.setAttribute("class", "badge list" + content[m][0].split(".").length);
            span.innerText = content[m][0];
            li.appendChild(span);

            if (edit) {
                let c = document.createElement("a");
                c.setAttribute("onclick", "aclistclick(" + m + ")");
                let a = document.createElement("span");
                a.innerText = content[m][2];
                c.appendChild(a);
                let b = document.createElement("span");
                b.setAttribute("class", "glyphicon glyphicon-edit");
                c.appendChild(b);

                li.appendChild(c);
            }
            else {
                let a = document.createElement("a");
                a.setAttribute("href", "coursepage.jsp?course=" + content[m][2] + "&cid=" + cid + "&pid=" + content[m][1]);
                a.innerText = content[m][2];
                li.appendChild(a);
            }
            $("#ulist").append(li);
        }

    if (edit) {
        let li = document.createElement("li");
        li.innerHTML = "<a onclick=\"aclistclick(" + content.length + ")\"><span class=\"badge list1\">ADD</span><span class=\"glyphicon glyphicon-edit\"></span></a>";
        $("#ulist").append(li);
    }
});

function aclistclick(n) {
    $("#ulist li")[n].innerHTML = "<input type=\"text\" name=\"number\" placeholder=\"No.\"><input type=\"text\" name=\"title\" placeholder=\"title\"><textarea type=\"text\" name=\"content\" placeholder=\"content\"></textarea><input type=\"url\" name=\"url\" placeholder=\"url\"><button class=\"btn btn-primary submit\" onclick=\"aclcsub(" + n + ")\">SUBMIT</button><button class=\"btn btn-primary submit\" onclick=\"aclcdel(" + n + ")\">DELELE</button>";
}

function aclcdel(n) {
    if (n === $("#ulist li").length - 1) {
        location.reload();
        return;
    }
    let id = content[n][1];
    $.post("Servlet", {
            type: "page",
            pid: id
        }, function (data) {
        }
    );
    location.reload();
}

function aclcsub(n) {
    let number = $("#ulist li")[n].getElementsByTagName("input")[0].value;
    let content1 = $("#ulist li")[n].getElementsByTagName("textarea")[0].value.replace(/\n/g, "<br>");
    let title = $("#ulist li")[n].getElementsByTagName("input")[1].value;
    let url = $("#ulist li")[n].getElementsByTagName("input")[2].value;
    let id;
    if (n === content.length) {
        $.post("Servlet", {
                type: "page",
                cid: cid,
                number: number,
                title: title,
                content: content1,
                url: url
            }, function (data) {
            }
        );
    }
    else {
        id = content[n][1];
        $.post("Servlet", {
                type: "page",
                pid: id,
                number: number,
                title: title,
                content: content1,
                url: url
            }, function (data) {
            }
        );
    }
    location.reload();
}

function titleclick() {
    $("#title").html("<input type=\"text\" name=\"title\" id=\"titleclick1\" placeholder=\"Title\"><button class=\"btn btn-primary submit\" onclick=\"titlesub()\">SUBMIT</button>");
    $("#img").html("<input type=\"text\" name=\"title\" id=\"titleclick2\" placeholder=\"Image url\">");
    $("#descrp").html("<input type=\"text\" name=\"title\" id=\"titleclick3\" placeholder=\"Description\">")
}

function titlesub() {
    let title = $("#titleclick1").val();
    let pic_url = $("#titleclick2").val();
    let content = $("#titleclick3").val();

    $.post("Servlet", {
            type: "course",
            cid: cid,
            title: title,
            content: content,
            pic_url: pic_url
        }, function (data) {
        }
    );
    location.reload();
}