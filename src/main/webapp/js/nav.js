var navtop = false;

function navTopClick() {
    if (navtop) {
        navtop = false;
        $('#nav-top-tab').animate({top: '3.61em'}, "slow").text("︿");
        $('#nav-top').animate({top: '0'}, "slow");
    } else {
        navtop = true;
        $('#nav-top-tab').animate({top: '0'}, "slow").text("﹀");
        $('#nav-top').animate({top: '-2.79em'}, "slow");
    }
}