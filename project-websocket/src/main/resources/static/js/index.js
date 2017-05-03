//页面杂项
$(function () {
    (function (c) {
        //总布局
        c.chat = $(".chat");

        //左侧节点
        c.tabReader = $(".chat-content-tab-reader");
        c.tabFriends = $(".chat-content-tab-friends");
        c.reader = $(".chat-content-reader");
        c.readerBox = $(".chat-content-reader-maxBox");
        c.friendsBox = $(".chat-content-friends-box");
        c.friends = $(".chat-content-friends");
        c.talk = $(".chat-content-talk");
        c.info = $(".chat-content-info");
        c.more = $(".chat-content-header-more");

        //右侧节点
        c.headName = $(".chat-content-talk-headName");
        c.sendA = $(".chat-content-talk-send > a");
        c.sendJump = $(".chat-content-info-message > a");
        c.text = $(".chat-content-talk-textArea");
        c.content = $(".chat-content-talk-body");
        c.newHeight = $(".chat-content-height");

        //自定义右键
        c.clear = $(".chat-clear");
        c.delete = $(".chat-delete");

        //模态框,绑定模态框对应关系
        c.modal = $(".chat-modal");
        var aim = $(".chat-modal-aim");
        $.each(aim,function (key) {
            var id = $(this).data("modal");
            $(this).on("click",function () {
                $("#"+id).show().find(".chat-modal-box").animate({top: 100,opacity:1
                }, 300);
            });
        });
        c.chatAdd = $("#chatAdd");
        c.chatLaunch = $("#chatLaunch");
        c.chatEdit =$("#chatEdit");
        //统计发起好友个数
        c.chatLaunch.count = 0;

        var chatBox = $(".chat-box");
        var contentBox = $(".chat-content");
        var right = $(".chat-content-right");
        var left = $(".chat-content-left");
        var footer = $("footer");
        
    })(window.chat.index = window.chat.index || {});

    setTimeout("$('.chat-wait').remove()",400);

    $(window).resize(function () {
        chat.scroll.reader.resize();
        chat.scroll.talk.resize();
        chat.scroll.friends.resize();
    });
    
    //插入滚动条
    //加入消息滚动条
    chat.scroll.talk = lib.scroll(chat.index.content,chat.index.talk.find(".chat-content-talk-box"));
    chat.scroll.talk.initialization();
    chat.index.talk.find(".chat-content-talk-box").after(chat.scroll.talk.box.addClass("chat-content-talk-scroll"));
    //加入正在会话好友滚动条
    chat.scroll.reader = lib.scroll(chat.index.readerBox,chat.index.reader);
    chat.scroll.reader.initialization();
    chat.index.readerBox.append(chat.scroll.reader.box.addClass("chat-content-reader-scroll"));
    //加入好友滚动条
    chat.scroll.friends = lib.scroll(chat.index.friendsBox,chat.index.friends);
    chat.scroll.friends.initialization();
    chat.index.friendsBox.append(chat.scroll.friends.box.addClass("chat-content-friends-scroll"));

    //点击切换事件
    chat.index.tabReader.on("click",function () {
        if (chat.index.readerBox.hasClass("chat-content-hide")){
            $(this).addClass("chat-content-tab-active");
            chat.index.tabFriends.removeClass("chat-content-tab-active");
            chat.index.readerBox.removeClass("chat-content-hide");
            chat.index.friendsBox.addClass("chat-content-hide");
            chat.index.talk.removeClass("chat-content-hide");
            chat.index.info.addClass("chat-content-hide");

            //图标变化优化
            $(this).find(".fa").toggleTwoClass("fa-comment-o","fa-comment");
            chat.index.tabFriends.find(".fa").toggleTwoClass("fa-user-o","fa-user");

            chat.scroll.friends.resize();
        }
    });
    chat.index.tabFriends.on("click",function () {
        if (chat.index.friendsBox.hasClass("chat-content-hide")){
            chat.index.friendsBox.removeClass("chat-content-hide");
            $(this).addClass("chat-content-tab-active");
            chat.index.tabReader.removeClass("chat-content-tab-active");
            chat.index.readerBox.addClass("chat-content-hide");
            chat.index.talk.addClass("chat-content-hide");
            chat.index.info.removeClass("chat-content-hide");

            $(this).find(".fa").toggleTwoClass("fa-user-o","fa-user");
            chat.index.tabReader.find(".fa").toggleTwoClass("fa-comment-o","fa-comment");

            chat.scroll.friends.resize();
        }
    });

    //点击正在聊天的好友
    chat.index.reader.on("click",".chat-content-reader-box",function () {
        var talkNone = chat.index.talk.find(".chat-content-talk-none");
        //初始当没有选择的列表时操作优化
        if (!talkNone.hasClass("chat-content-talk-hide")){
            chat.index.talk.find(".chat-content-talk-box,.chat-content-talk-input").removeClass("chat-content-talk-hide");
            talkNone.addClass("chat-content-talk-hide");
        }

        //避免重复点击
        if (!$(this).hasClass("chat-content-reader-active")) {
            chat.other = $(this).data("index");
            var active = $(".chat-content-reader-active");
            if (active.length !== 0){
                chat.index.content.find(".chat-content-talk-box").empty();
                active.removeClass("chat-content-reader-active");
            }
            $(this).addClass("chat-content-reader-active");
            chat.index.headName.text(chat.allFriends[$(this).data("index")].nodeDiv.data("name"));

            //页面输出内容
            var message = chat.allFriends[$(this).data("index")].message;
            var unread = chat.allFriends[$(this).data("index")].nodeDiv.data("unread");
            if (unread !== 0){
                $(".chat-content-reader-count").addClass("chat-hide");
                chat.allFriends[$(this).data("index")].nodeDiv.data("unread",0);
            }
            $.each(message,function(key){
                if (key>20){
                    return false;
                }
                if (this.type == "user") {
                    var text = chat.view.message.clone();
                    text.find("img").attr("src",chat.user.img).end()
                        .find(".pre-plain").html(this.content).end()
                        .addClass("chat-content-talk-self");
                    chat.index.content.find(".chat-content-talk-box").prepend(text);
                } else if (this.type == "time") {
                    var time = chat.view.time.clone();
                    time.find("div").text(this.content);
                    chat.index.content.find(".chat-content-talk-box").prepend(time);
                } else if (this.type == "form") {
                    var text = chat.view.message.clone();
                    text.find("img").attr("src",chat.allFriends[this.userId].nodeDiv.data("img")).end()
                        .find(".pre-plain").html(this.content).end()
                        .addClass("chat-content-talk-other");
                    chat.index.content.find(".chat-content-talk-box").prepend(text);
                }
            });
        }
    });

    //点击好友列表事件
    chat.index.friends.on("click",".chat-content-friends-info",function () {
        var talkNone = chat.index.info.find(".chat-content-info-none");
        //初始当没有选择的列表时操作优化
        if (!talkNone.hasClass("chat-content-info-hide")){
            chat.index.info.find(".chat-content-info-body").removeClass("chat-content-info-hide");
            talkNone.addClass("chat-content-info-hide");
        }
        //避免重复点击
        if (!$(this).hasClass("chat-content-friends-active")) {
            var active = $(".chat-content-friends-active");
            if (active.length !== 0){
                active.removeClass("chat-content-friends-active");
            }
            $(this).addClass("chat-content-friends-active");

            //插入信息
            var info =$(this).data();
            chat.index.info.find(".chat-content-info-icon > img").attr("src",info.img).end()
                .find(".chat-content-info-name>span").text(info.name).end()
                .find(".chat-content-info-tag").text(info.tag).end()
                .find(".chat-content-info-remark>span").text("备注: "+info.remark).end()
                .find(".chat-content-info-address>span").text("地址: "+info.address);

            //当部分信息缺失时显示优化
            if (!info.remark)
                chat.index.info.find(".chat-content-info-remark>span").text("备注: "+info.name);
            if (!info.address)
                chat.index.info.find(".chat-content-info-address>span").text("");
            if (!info.tag)
                chat.index.info.find(".chat-content-info-tag").text("");
            //给发消息按钮一个部分用户信息
            chat.index.sendJump.data("index",info.index);
        }
    });

    //给好友列表的好友发送消息
    chat.index.sendJump.on("click",function () {
        //得到当前好友信息
        var index=$(this).data("index");
        //标识对象是否存在
        var mark = false;
        //判断好友会话是否打开
        var otherIndex = chat.otherId.indexOf(index);
        //循环已存在的对话
        if (chat.otherId.length == 0){
            mark = true;
        }

        if (otherIndex !== -1){
             chat.index.tabReader.trigger("click");
             chat.allFriends[index].point.trigger("click");
             mark = false;
             return false;
        }else{mark = true}

        if (mark){
            var active =  chat.view.readerList.clone();
            var info = chat.allFriends[index].nodeDiv.data();
            active.find("img").attr("src",info.img).end()
                .find(".chat-content-reader-name").text(info.name).end()
                .data({
                    "timeHour":-2,
                    "timeMinute":-6,
                    "lastTime":0,
                    "index":index
                });
            chat.index.reader.append(active);
            chat.otherId.push(index);
            chat.index.tabReader.trigger("click");
            active.trigger("click");
            chat.allFriends[index].point = active;
            //滚动条调整
            chat.scroll.reader.resize();
        }
    });

    //发送消息的组合键操作
    chat.index.text.on({
        keypress:function (e) {
            if(e.ctrlKey && e.which == 13 || e.which == 10) {
                var text =$(this).val();
                $(this).val(text+ "\r\n");
                return false;
            }
            if (e.which == 13 || e.which == 10){
                e.preventDefault();
                chat.index.sendA.trigger("click");
            }
        }
    });

    /*
     *  清屏
     * 清屏操作
     */
    chat.index.content.on("contextmenu",".chat-content-talk-box",function (e) {
       if (chat.index.delete.is(":visible"))
           chat.index.delete.hide();
       e.preventDefault();
       chat.index.clear.show();
       chat.index.clear.css({"top":e.pageY,"left":e.pageX});
       //点击页面任一位置隐藏清屏
       chat.index.chat.on("click.clear",function () {
           chat.index.clear.hide();
           $(this).off("click.clear");
        });
    });
    chat.index.clear.on("click",function () {
        chat.index.content.find(".chat-content-talk-box").empty();
        //重置最后一次发送时间及消息预览
        chat.index.reader.find(".chat-content-reader-active").data({"timeHour":-2,"timeMinute":-6,"lastTime":0})
            .find(".chat-content-reader-time").text("").end()
            .find(".chat-content-reader-message").text("");
        $(this).hide();

        chat.scroll.talk.resize();
    });

     /*
     *  正在会话好友置顶删除
     * 好友置顶操作
     * 好友会话关闭
     */
    chat.index.reader.on("contextmenu",".chat-content-reader-box",function (e) {
        if (chat.index.clear.is(":visible"))
         chat.index.clear.hide();
        e.preventDefault();
        chat.topClose = $(this);
        chat.index.delete.show();
        chat.index.delete.css({"top":e.pageY,"left":e.pageX});
        //点击页面任一位置隐藏
        chat.index.chat.on("click.clear",function () {
            chat.index.delete.hide();
            $(this).off("click.clear");
        });
    });
    chat.index.delete.on("click",".chat-delete-top",function () {
        var obj = chat.topClose.detach();
        chat.index.reader.prepend(obj);
        chat.index.delete.hide();
    });
    chat.index.delete.on("click",".chat-delete-close",function () {

        var id = chat.topClose.data("index");
        var userKey = 0;
        if (chat.topClose.hasClass("chat-content-reader-active")){
            chat.index.talk.find(".chat-content-talk-box,.chat-content-talk-input").addClass("chat-content-talk-hide").end()
                .find(".chat-content-talk-none").removeClass("chat-content-talk-hide");
            chat.index.headName.text("");
            chat.index.talk.find(".chat-content-talk-box").empty();
        }
        $.each(chat.otherId,function (key) {
           if (this.id == id){
               userKey = key;
           }
        });
        chat.otherId.splice(userKey,1);
        chat.topClose.remove();
        chat.index.delete.hide();

        chat.allFriends[id].point=null;
        //滚动条调整
        chat.scroll.reader.resize();
    });

    //优化自定义右键的显示问题
    chat.index.chat.on("contextmenu",function (e) {
        if ($(e.target).parents(".chat-content-reader").length == 0 && $(e.target).parents(".chat-content-talk-body").length == 0){
            chat.index.clear.hide();chat.index.delete.hide();
        }
    });

    //添加好友等操作,显示下拉菜单
    chat.index.more.on("click",".chat-content-header-icon",function () {
        var fn = chat.index.more.find(".chat-content-header-fn");
        fn.show();
        chat.index.chat.on("click.modify",function (e) {
            if (!$(e.target).hasClass("chat-content-header-show")){
                fn.hide();
                $(this).off("click.modify");
            }
        });
    });

    //公用模态框行为事件,关闭模态框
    chat.index.modal.on("click",".chat-modal-close",function () {
       var $this = $(this).parents(".chat-modal");
        $this.find(".chat-modal-box").animate({top: 0,opacity:0
        }, 300,function () {
            $this.hide();
        });
    });
    chat.index.modal.on("click",function (e) {
        var $this = $(this);
        if ($(e.target).hasClass("chat-modal-mask") || $(e.target).hasClass("chat-modal-box")){
            $this.find(".chat-modal-box").animate({top: 0,opacity:0
            }, 300,function () {
                $this.hide();
            });
        }
    });

    //发起聊天操作
    chat.index.chatLaunch.find(".chat-launch-body-box").on("click","div",function () {
       var launch =  chat.index.chatLaunch;
       var button = launch.find(".chat-modal-foot-save");

       $(this).toggleClass("chat-launch-select").find("i").toggleTwoClass("fa-circle-thin","fa-check-circle");
       if ($(this).hasClass("chat-launch-select")){
           launch.count++;
           var img = $(this).find("img").clone();
           launch.find(".chat-launch-body-head").append(img);
           $(this).data("imgActive",img);

       } else {
           launch.count--;
           $(this).data("imgActive").remove();
       }
        button.find("span").text("("+launch.count+")");
        //选中好友个数统计
        if (launch.count == 0){
            button.removeClass("chat-modal-foot-active");
            button.find("span").text("");
        }else{
            button.addClass("chat-modal-foot-active");
        }
    });

});