var apiurl={
    registerdata:"/users",
    successregister:"/display.html"
}
$("#enter").click(function (){
    var name = $("#name").val();
    var account = $("#account").val();
    var password = $("#password").val();

    $.post({
        url:apiurl.registerdata,
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify({"name":name,"account":account,"password":password}),
        success:function(res) {
            window.location.href = apiurl.successregister;//正確登入後頁面跳轉
        }
    });
});
