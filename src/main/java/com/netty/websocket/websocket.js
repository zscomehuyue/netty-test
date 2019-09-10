<script type="text/javascript">
var socket;
if(window.WebSocket){
    socket = new WebSocket("ws://localhost:8899/ws");
    socket.onmessage = function(event){
        alert(event.data);
    }
    socket.onopen = function(event){
        alert("连接开启");
    }
    socket.onclose = function(event){
        alert("连接关闭");
    }
}else{
    alert("浏览器不支持 WebSocket");
}

function send(message){
    if(!window.WebSocket){
        return;
    }
    if(socket.readyState == WebSocket.OPEN){
        socket.send(message);
    }
}
</script>
