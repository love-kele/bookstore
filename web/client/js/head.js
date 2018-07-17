$(function () {
   $("#myaccount").click(function () {

      var $logined=$("#logined");

      if($logined.val()==null||$logined.val()==""){


      }else {
          $("#myaccount").attr("href","../client/myAccount.jsp");
      }

   });

    $("#cart").click(function () {

        var $logined=$("#logined");

        if($logined.val()==null||$logined.val()==""){
            alert("你还没有登录，请先登录");
           $("#cart").attr("href","../client/login.jsp");
        }

    });
})