//视图输出
var websocket = null;
$(function () {
    (function (c) {

        var token = $("#token").val();
        //console.log(window.location);

        //判断当前浏览器是否支持WebSocket
        if('WebSocket' in window){
            websocket = new WebSocket("ws://localhost:8080/websocket?token="+token);
        }
        else{
            console.log('Not support websocket');
        }

        //连接发生错误的回调方法
        websocket.onerror = function(){
            console.log('error');
        };

        //连接成功建立的回调方法
        websocket.onopen = function(event){
            console.log("open");
        };

        //连接关闭的回调方法
        websocket.onclose = function(){
            console.log("close");
        };

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function(){
            websocket.close();
        };

        //关闭连接
        function closeWebSocket(){
            websocket.close();
        }

    })(window.chat.chat = window.chat.chat || {});
});


