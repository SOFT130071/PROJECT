$.post("Servlet", {
        type: "tophot"
    },
    function (data) {
        let list = JSON.parse(data)
        console.log(data);
        $.each(list, function (name, value) {
            let str =
                "                <img style=\"background:url(" + value.pic_url + ") no-repeat\" >" +
                "                <div class=\"carousel-caption\">\n" +
                "                    <a href=\"course.jsp?course=" + value.title + "&cid=" + value.cid + "\"><h3>" + value.title + "</h3></a>\n" +
                "                    <p>" + value.content + "</p>\n" +
                "                </div>";

            let div = document.createElement("div");
            if (name === '0') div.setAttribute("class", 'item active');
            else div.setAttribute("class", 'item');
            div.setAttribute("id", "top" + name);
            div.innerHTML = str;
            $(".carousel-inner")[0].appendChild(div);
        });
    });

$.post("Servlet", {
        type: "latestcourse"
    },
    function (data) {
        let list = JSON.parse(data)
        console.log(data);
        $.each(list, function (name, value) {
            let str =
                "<a href=\"course.jsp?course=" + value.title + "&cid=" + value.cid + "\"><img src=\"" + value.pic_url + "\"></a>\n" +
                "        <a href=\"course.jsp?course=" + value.title + "&cid=" + value.cid + "\"><h5>" + value.title + "</h5></a>\n" +
                "        <p>" + value.content + "</p>";

            let div = document.createElement("div");
            div.setAttribute("class", 'col-lg-3');
            div.setAttribute("id", "latest" + name);
            div.innerHTML = str;
            $(".row")[0].appendChild(div);
        });
    });

function logout() {
    sessionStorage.removeItem("uid");
    sessionStorage.removeItem("username");

}