$(function () {
    "use strict";

    var contextPath = $("#context-path").prop("content");
    
    $(".link").addClass("active").siblings().removeClass("active");
    
    /**
     * 保存资源链接
     */
    $(".j-save").click(function(){
        $.ajax({
            type: "POST",
            url: contextPath + "links",
            data: {
                _method: "PUT",
                id: $("#linkId").val(),
                name: $("#name").val(),
                link: $("#link").val(),
                code: $("#code").val(),
                resInfo: $("#res-info").val()
            },
            success: function(data){
                window.location.href = contextPath;
            }
        })
    })
    
});