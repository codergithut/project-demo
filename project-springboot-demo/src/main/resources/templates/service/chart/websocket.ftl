<!DOCTYPE HTML>
<html>
<head>
    <title>My WebSocket</title>
</head>

<script type="text/javascript" src="../js/sockjs.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/stomp.js"></script>

<body>
Welcome<br/>
<div>
    <div>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
    </div>
    <div id="conversationDiv">
        <p>
            <label>notice content?</label>
        </p>
        <p>
            <textarea id="name" rows="5"></textarea>
        </p>
        <button id="sendName" onclick="sendName();">Send</button>
        <p id="response"></p>
</div>
</body>

<script>

    var s = new SockJS('http:localhost:8080/socket');
    var stomp = Stomp.over(s);

    stomp.connect('guest', 'guest', function(frame) {
        console.log('*****  Connected  *****');
        stomp.subscribe("/topic/spittlefeed", handleSpittle);
        stomp.subscribe("/user/queue/notifications", handleNotification);
    });


    var stompClient = null;
    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }
    // 开启socket连接
    var sock = new SockJS("http://localhost:8080/socket");
    var stomp = Stomp.over(sock);

    stomp.connect('guest', 'guest', function(frame) {
        stomp.subscribe("/user/queue/notifications", function (data) {
            alert(data.body);
        });
    });

    // 断开socket连接
    function disconnect() {
        if (stomp != null) {
            stomp.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }
    // 向‘/app/change-notice’服务端发送消息
    function sendName() {
        var value = document.getElementById('name').value;
        stomp.send("/app/change-notice", {}, value);
    }
</script>
</html>