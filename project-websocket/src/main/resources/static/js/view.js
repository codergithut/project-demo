//视图输出
$(function () {
    (function (c) {
        //正在聊天好友模板
        var readerBox = $(".chat-content-reader-box");
        c.readerList = readerBox.eq(0).detach();
        c.readerGroup = readerBox.eq(1).detach();

        //好友列表模板
        c.friendSort = $(".chat-content-friends-scope").detach();
        c.friendList = $(".chat-content-friends-info").detach();

        //聊天框模板
        c.time = $(".chat-content-talk-time").detach();
        c.message = $(".chat-content-talk-text").detach();

        //模态框模板
        c.modalSelect = $(".chat-launch-body-box > div").detach();

        //登录用户信息
        c.user = $("#userid").val();

    })(window.chat.view = window.chat.view || {});

    //发送消息
    chat.index.sendA.on("click",function () {
        //插入消息
        var hour = chat.time().hour,minute = chat.time().minute;
        var news = chat.index.text.val();
        var text = chat.view.message.clone();
        var index = chat.other,
            info = chat.allFriends[index].nodeDiv.data(),
            active = chat.allFriends[index].point;

        //时间显示优化,间隔短不显示,这里只做当天的判断,年月日不作判断
        var minusHour = hour-active.data("timeHour"),
            minusMinute = minute - active.data("timeMinute");
        minute < 10 ? minute ="0" + minute : minute;
        if (minusHour > 1 || minusMinute > 5){
            active.data("timeHour",hour);
            active.data("timeMinute",minute);
            var timeText = hour + ":"+minute;
            var time = chat.view.time.clone();
            time.find("div").text(timeText);

            var timeRecord = {
                type:"time",
                content:timeText
            }
            chat.allFriends[chat.other].message.unshift(timeRecord);
        } else {time = {};}

        //将消息输出到页面
        text.find("img").attr("src",chat.user.img).end()
            .find(".pre-plain").html(news).end()
            .addClass("chat-content-talk-self");
        chat.index.content.find(".chat-content-talk-box").append(time,text);

        //发送数据
        var data = JSON.stringify({otherId:info.friend,type:"talk",id:chat.user.id,text:news});
        websocket.send(data);

        var record = {
            type:"user",
            userId:chat.user.id,
            content:news
        }
        chat.allFriends[chat.other].message.unshift(record);

        //在好友处插入时间以及消息预览
        active.find(".chat-content-reader-time").text(hour + ":"+minute);
        if (news.length > 12){news = news.substring(0,12) + "...";}
        active.find(".chat-content-reader-message").text(news);

        //清空发送框栏
        chat.index.text.val("");

        //滚动条调整
        chat.scroll.talk.resize();
    });

    //接收到消息的回调方法
    websocket.onmessage = function(e){
        var data = JSON.parse(e.data);
        var hour = chat.time().hour,minute = chat.time().minute;

        if (data.type == "talk"){
        //得到对话消息
            var content = data.text,
               sender = data.id,
               img = chat.allFriends[sender].nodeDiv.data("img");
            Notification.requestPermission().then(chat.notifyMsg(sender,img,content));

            var point = chat.allFriends[sender].point;

            if (!point) {
                //接受人会话框未打开
                var active =  chat.view.readerList.clone();
                var info = chat.allFriends[sender].nodeDiv.data();
                info.unread++;
                active.find(".chat-content-reader-count").removeClass("chat-hide");
                active.find("img").attr("src",info.img).end()
                    .find(".chat-content-reader-name").text(info.name).end()
                    .find(".chat-content-reader-count").text(info.unread).end()
                    .data({
                        "timeHour":hour,
                        "timeMinute":minute,
                        "lastTime":0,
                        "index":sender
                    });

                 //在好友处插入时间以及消息预览
                 var news="";
                 active.find(".chat-content-reader-time").text(hour + ":"+minute);
                 if (content.length > 12){news = content.substring(0,12) + "...";}else{news = content;}
                 active.find(".chat-content-reader-message").text(news);

                 var timeText = hour + ":"+minute;
                 var time = chat.view.time.clone();
                 time.find("div").text(timeText);

                 var timeRecord = {
                     type:"time",
                     content:timeText
                 }
                 chat.allFriends[sender].message.unshift(timeRecord);

                chat.index.reader.append(active);
                chat.otherId.push(sender);
                chat.allFriends[sender].point = active;
                //滚动条调整
                chat.scroll.reader.resize();
            } else if (point.data("index") == chat.other){
                //接收人会话框处于激活状态
               //时间显示优化,间隔短不显示,这里只做当天的判断,年月日不作判断
                  var minusHour = hour-point.data("timeHour"),
                      minusMinute = minute - point.data("timeMinute");
                  minute < 10 ? minute ="0" + minute : minute;
                 if (minusHour > 1 || minusMinute > 5){
                      point.data("timeHour",hour);
                      point.data("timeMinute",minute);
                      var timeText = hour + ":"+minute;
                      var time = chat.view.time.clone();
                      time.find("div").text(timeText);

                      var timeRecord = {
                          type:"time",
                          content:timeText
                      }
                      chat.allFriends[sender].message.unshift(timeRecord);
                      console.log(chat.allFriends[chat.other].message);
                  } else {time = {};}
                var answer = chat.view.message.clone();
                answer.find("img").attr("src",img).end()
                    .find(".pre-plain").html(content).end()
                    .addClass("chat-content-talk-other");
                chat.index.content.find(".chat-content-talk-box").append(time,answer);



                chat.scroll.talk.resize();
            } else {
                //接受人会话存在但未激活
                var info = chat.allFriends[sender].nodeDiv.data();
                var nodeDiv = chat.allFriends[sender].point;
                info.unread++;
                nodeDiv.find(".chat-content-reader-count").removeClass("chat-hide");
                //在好友处插入时间以及消息预览
                var news="";
                nodeDiv.find(".chat-content-reader-time").text(hour + ":"+minute);
                if (content.length > 12){news = content.substring(0,12) + "...";}else{news = content;}
                nodeDiv.find(".chat-content-reader-message").text(news).end()
                    .find(".chat-content-reader-count").text(info.unread);

                //时间显示优化,间隔短不显示,这里只做当天的判断,年月日不作判断
                var minusHour = hour-nodeDiv.data("timeHour"),
                    minusMinute = minute - nodeDiv.data("timeMinute");
                minute < 10 ? minute ="0" + minute : minute;
                if (minusHour > 1 || minusMinute > 5){
                    nodeDiv.data("timeHour",hour);
                    nodeDiv.data("timeMinute",minute);
                    var timeText = hour + ":"+minute;
                    var time = chat.view.time.clone();
                    time.find("div").text(timeText);

                    var timeRecord = {
                        type:"time",
                        content:timeText
                    }
                    chat.allFriends[sender].message.unshift(timeRecord);
                } else {time = {};}
            }

            var record = {
                type:"form",
                userId:sender,
                content:content
            }
            chat.allFriends[sender].message.unshift(record);

            //滚动条调整
            chat.scroll.talk.resize();

        }else if (data.type == "friendsinfo"){
            //得到好友列表
            chat.index.friends.empty();
            chat.index.chatLaunch.find(".chat-launch-body-box").empty();
            console.log(data);
             $.each(data.Data,function () {
                 var list = chat.view.friendList.clone();
                 var img = "../img/user/" + this.image;
                 list.find(".chat-content-friends-icon > img").attr("src",img).end()
                     .find(".chat-content-friends-name > span").text(this.friendname).end()
                     .data({
                         "id":this.id,
                         "friend":this.friend,
                         "name":this.friendname,
                         "tag":this.tag,
                         "img":img,
                         "remark":this.remark,
                         "address":this.address,
                         "index":this.friend,
                         "unread":0,
                     });
                 chat.index.friends.append(list);

                 //写入模态框
                 var modal = chat.view.modalSelect.clone();
                 modal.find("img").attr("src",img).end()
                     .find("span").text(this.friendname).end()
                     .data({
                         "id":this.id,
                         "img":img,
                         "name":this.friendname,
                         "index":this.id
                     });
                 chat.index.chatLaunch.find(".chat-launch-body-box").append(modal);

                  chat.allFriends[this.friend] = {
                      nodeDiv:list,
                      message:[],
                      point:null
                  }
             });

             //加入好友滚动条
             chat.scroll.friends.resize();
         }else if (data.type == "userinfo") {
         //得到用户信息
              var data = data.Data;
              var img = "../img/user/" +data.image;
              chat.user.id = data.userid;
              chat.user.name = data.name;
              chat.user.img = img;
              chat.user.tag = data.signname;
              chat.user.address = data.address;

              //将信息写入模态框
              chat.index.chatEdit.find("#editName").val(chat.user.name).end()
                  .find("#editTag").val(chat.user.tag).end()
                  .find("#editAddress").val(chat.user.address);

              //写入头部
              $(".chat-content-header-icon").find("img").attr("src",img)
                .end().find("p").html(data.name);
         }else if (data.type == "friendRequest") {
             $("#chatAccept").find(".chat-add-count").text("添加人账号: "+data.user.userid).end()
                .find(".chat-add-name").text("添加人昵称: "+data.user.name);
             $("#chatAccept").find(".chat-modal-box").animate({top: 150,opacity:1
             }, 300,function () {
                 $("#chatAccept").show();
             });
             $("#chatAccept").find(".chat-modal-foot-save").data("account",data.user.userid);
         }
    };

    /*
     * 下拉好友信息操作
     * 修改好友备注
     * 检测到失去焦点后修改数据
     * 删除好友
     */
    chat.index.info.on("click",".chat-content-info-more",function () {
        var modify = chat.index.info.find(".chat-content-info-modify");
        modify.show();
        chat.index.chat.on("click.modify",function (e) {
            if (!$(e.target).hasClass("chat-content-info-more")){
                modify.hide();
                $(this).off("click.modify");
            }
        });
    });
    chat.index.info.on("click",".chat-content-remark-modify",function () {
        chat.index.info.find(".chat-content-info-modify").hide().end()
            .find(".chat-content-info-remark>span").hide().end()
            .find(".chat-content-info-remark>div").show().end()
            .find(".chat-content-info-remark input").focus();
    });
    chat.index.info.on("focusout",".chat-content-info-remark input",function () {
        var remark = $(this).val();
        var active = $(".chat-content-friends-active");
        !!remark ? active.data("remark",remark) : remark = active.data("name");
        chat.index.info.find(".chat-content-info-remark>span").show().text("备注: "+remark).end()
            .find(".chat-content-info-remark>div").hide();
        //此处往后台发送数据
        //
        //
    });
    chat.index.info.on("click",".chat-content-friend-delete",function () {
        chat.index.info.find(".chat-content-info-modify").hide();
        var active = $(".chat-content-friends-active");
        var id = active.data("id");
        //此处往后台发送数据
        //
        //
        active.remove();
        //无数据时将首字母删除
        var headWord = chat.index.friends.find(".chat-content-friends-scope");
        $.each(headWord,function () {
            var next =$(this).next().length == 0 || $(this).next().hasClass("chat-content-friends-scope");
            if (next)
                $(this).hide();
        });
        //将右侧内容隐藏
        chat.index.info.find(".chat-content-info-body").addClass("chat-content-info-hide").end()
            .find(".chat-content-info-none").removeClass("chat-content-info-hide");

        //删除模态框中的好友
        var modal = chat.index.chatLaunch.find(".chat-launch-body-box>div");
        $.each(modal,function () {
           if ($(this).data("id") == id)
               $(this).remove();
        });

        chat.scroll.friends.resize();
    });

    //模态框操作
    //添加好友
    chat.index.chatAdd.on("click",".chat-modal-foot-save",function () {
       var account = chat.index.chatAdd.find("#addAccount").val();
       console.log(chat.allFriends[account]);
       if (!chat.allFriends[account]){
         //将账号传进后台
          var data = JSON.stringify({account:account,type:"addFriend",id:chat.user.id});
          websocket.send(data);

          chat.index.chatAdd.find(".chat-modal-close").trigger("click");
       }else{
          chat.index.chatAdd.find(".add-message").text("好友已存在！");
       }

    });

    //接受好友申请
    $("#chatAccept").on("click",".chat-modal-foot-save",function(){
         var  chatAccept = $("#chatAccept");
         var data = JSON.stringify({account:chatAccept.find(".chat-modal-foot-save").data("account"),type:"agreeFriend",id:chat.user.id,result:"agree"});
         websocket.send(data);

         chatAccept.find(".chat-modal-box").animate({top: 0,opacity:0
         }, 300,function () {
             chatAccept.hide();
         });
    });
    //拒绝好友申请
    $("#chatAccept").on("click",".chat-modal-foot-save",function(){
         var data = JSON.stringify({account:$("#chatAccept").find(".chat-modal-foot-save").data("account"),type:"agreeFriend",id:chat.user.id,result:"refused"});
         websocket.send(data);
    });

    //发起聊天
    chat.index.chatLaunch.on("click",".chat-modal-foot-save",function () {
       var launch =  chat.index.chatLaunch;
       var select =  launch.find(".chat-launch-select");
       //此处对象存有选中的聊天对象

       //在正在聊天栏对应显示出来
        if (select.length == 1){
            var info = select.data();
            //标识对象是否存在
            var mark = false;
            //循环已存在的对话
            $.each(chat.otherId,function () {
                if (info.id == this.id){
                    chat.index.tabReader.trigger("click");
                    this.obj.trigger("click");
                    mark = false;
                    return false;
                }else{mark = true}
            });
            if (mark){
                // var active =  chat.view.readerList.clone();
                // active.find("img").attr("src",info.img).end()
                //     .find(".chat-content-reader-name").text(info.name).end()
                //     .data({"id":info.id,
                //         "name":info.name,
                //         "timeHour":-2,
                //         "timeMinute":-6,
                //         "lastTime":0,
                //         "dialog":{}
                //     });
                // chat.index.reader.append(active);
                //chat.otherId.push({id:"？？？",obj:{}});
            }
        } else if (select.length > 1){
            //群聊
            var group = chat.view.readerGroup.clone();
            $.each(select,function(key){
               if (key>3)
                   return false;
                var img = $(this).find("img").clone();
                group.find(".chat-content-reader-icon").append(img).end()
                    .find(".chat-content-reader-name").text("暂不支持群聊");
                chat.index.reader.append(group);
            });
        }

        select.trigger("click");
        launch.find(".chat-modal-close").trigger("click");

    });
    //修改个人信息
    chat.index.chatEdit.on("click",".chat-modal-foot-save",function () {
        //修改个人信息
        //id即当前用户
        chat.index.chatEdit.find(".chat-modal-close").trigger("click");
    });
    
});