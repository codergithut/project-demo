//登录
$(function () {
    (function (c) {
        c.box = $(".user");
        c.login = c.box.find("#userLogin");

        //尺寸调节
        c.resize = function () {
            if (chat.size.height >800){
                c.box.height(chat.size.height+"px");
            }else {
                c.box.height(800+"px");
            }
        };
    })(window.chat.user = window.chat.user || {});

    chat.user.resize();
    $(window).resize(function () {
        chat.user.resize();
    });

    //提交登录信息
    // chat.user.login.on("click",".user-login-submit",function (e) {
    //     e.preventDefault();
    //     var data = chat.user.login.serializeArray();
    //
    //     $.ajax({
    //         url: "login",
    //         method:"post",
    //         data:data,
    //     }).done(function(data) {
    //         //完成后的操作data为返回的数据
    //     });
    // });
});