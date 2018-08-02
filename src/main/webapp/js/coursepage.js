var nav = false;
var content = [];
record(sessionStorage.getItem("uid"), c_id, cp_id);

$.post("Servlet", {
        type: "pagelist",
        cid: c_id
    },
    function (data) {
        let ret = JSON.parse(data);
        let j = 0;
        for (let i in ret) {
            content[j] = [];
            let k = content[j];
            k[0] = ret[i].number; //number
            k[1] = ret[i].pid; //cp_id
            k[2] = ret[i].title; //title
            k[3] = ret[i].content;
            k[4] = [];
            k[4] = ret[i].url.split(" ");

            for (let q = 0; q < k[4].length; q++) {
                let o = k[4][q];
                if (o.substr(o.length - 4) === ".ppt" || o.substr(o.length - 5) === ".pptx" || o.substr(o.length - 5) === ".ppt/" || o.substr(o.length - 6) === ".pptx/") {
                    o = "//view.officeapps.live.com/op/view.aspx?src=" + encodeURIComponent(o);
                    k[4][q] = o;
                }
                else {
                    let p = o.split("/");
                    if (p[2] === "www.bilibili.com") {
                        o = "//player.bilibili.com/player.html?aid=" + p[4].substr(2);
                        k[4][q] = o;
                    }
                    if (p[2] === "www.youtube.com") {
                        o = "//www.youtube.com/embed/" + p[3].substr(8);
                        k[4][q] = o;
                    }
                }
            }
            j++;
        }

        for (let m = 0; m < content.length; m++) {
            let li = document.createElement("li");
            li.setAttribute("id", "cp" + content[m][1]);
            li.setAttribute("class", "cp-list");
            li.onclick = function () {
                showCoursePageResourceList(content[m][1])
            };
            li.innerText = content[m][0] + " " + content[m][2];
            $("#coursepage-list").append(li);
        }

        showCoursePageResourceList(cp_id);
    });

function showCoursePageResourceList(id) {
    record(sessionStorage.getItem("uid"), c_id, id);
    let s;
    let u;
    for (let r = 0; r < content.length; r++) {
        if (content[r][1].toString() === id.toString()) {
            s = content[r][4];
            u = r;
            break;
        }
    }

    $("#cp-title").text(content[u][2]);
    $("#cp-content").text(content[u][3]);

    $("#pagecontent-list").text("");

    for (let t = 0; t < s.length; t++) {
        let li = document.createElement("li");
        li.setAttribute("class", "cp-resource");
        li.onclick = function () {
            $("#player").attr("src", "https:" + s[t]);
        };

        if (s[t].substr(s[t].length - 4) === ".ppt" || s[t].substr(s[t].length - 5) === ".pptx" || s[t].substr(s[t].length - 5) === ".ppt/" || s[t].substr(s[t].length - 6) === ".pptx/")
            li.innerText = "PPTX";
        else li.innerText = "VIDEO";

        $("#pagecontent-list").append(li);
    }

}

function navClick() {
    if (nav) {
        nav = false;
        $('#right').animate({right: '0'}, "slow");
        $('#nav-tab').animate({right: '30em'}, "slow").text("〉");
    } else {
        nav = true;
        $('#right').animate({right: '-30em'}, "slow");
        $('#nav-tab').animate({right: '0'}, "slow").text("〈");
    }
}

function record(uid, cid, pid) {
    $.post("Servlet", {
        type: "add_record",
        cid: cid,
        uid: uid,
        pid: pid,
    });
}