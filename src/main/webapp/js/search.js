var condition = 0;
var res = [];
var page = 1;

function choose(n) {
    condition = n;
}

$(document).ready(function () {
    $("#ttext").attr("value", tttt);
    $("#sousuo").click();
});


function cccc() {
    let hot = "hot";
    if (condition === 2 || condition === 3) hot = "";
    let desc = "desc";
    if (condition === 1 || condition === 3) desc = "";

    $.post("Servlet", {
        type: "courseinfo",
        title: $("#ttext").val(),
        content: $("#ctext").val(),
        name: $("#ntext").val(),
        order: hot,//hot or else
        choose: desc//desc or blank
    }, function (data) {
        console.log(data);
        let ret = JSON.parse(data.replace(/\r\n/g, "<br>"));

        res = [];

        let j = 0;
        for (let i in ret) {
            res[j] = [];
            let k = res[j];
            k        [0] = i;  //number
            k        [1] = ret[i].cid;     //cid
            k        [2] = ret[i].title;     //title
            k        [3] = ret[i].content;     //content

            j++;
        }

        $("#page").html(showPage());

        showContent(1);
    });
}

function showPage() {
    let no = Math.ceil(res.length / 6);
    let str = "";
    for (let i = 1; i <= no; i++)
        str += "<div class=\"btn btn-default\" onclick=\"showContent(" + i + ")\">" + i + "</div>";
    return str;
}

function showContent(i) {
    $("#wtm").html("");
    $("#page .btn-default").each(function () {
        $(this).attr("class", "btn btn-default");
    });
    $("#page .btn-default")[i - 1].setAttribute("class", "btn btn-default active");
    for (let j = (i - 1) * 6; j < i * 6; j++) {
        if (j >= res.length) break;
        oneline(j)
    }
}

function oneline(i) {
    let li = document.createElement("li");
    li.setAttribute("class", "li");
    li.innerHTML = "<a href=\"course.jsp?cid=" + res[i][1] + "\"><h3>" + res[i][2] + "</h3><h5>" + res[i][3] + "</h5></a>";
    $("#wtm").append(li);
}