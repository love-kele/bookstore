function cheacklogin() {

    var $logined=$("#logined");

    if($logined.val()==null||$logined.val()==""){
        alert("你还没有登录，请先登录");
        $("#addcar").attr("href","../client/login.jsp");
    }
}