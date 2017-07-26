$(function () {
	"use strict";

	var contextPath = $("#context-path").prop("content");
	
	$(".link").addClass("active").siblings().removeClass("active");
	
    /**
     * 查询资源链接列表
     */
	var pageNum = 1;
	var size = 10;
	var query = $("#search-input").val();
	
	listLinks();
	function listLinks(){
		$.ajax({
			type: "GET",
			url: contextPath + "links",
			data: {
				pageNum: pageNum,
				size: size,
				query: query
			},
			success: function(pageList){
				$("#linkContent").empty();
				$("#linkTmpl").tmpl({links: pageList.data}).appendTo("#linkContent");
				pagination(pageList.totalPages);
			}
		});
	}
	
	/**
     * 点击搜索
     */
    $("#search-btn").click(function(){
    	query = $("#search-input").val();
		pageNum = 1;
		listLinks();
    });
    
    /**
     * 回车搜索
     */
    $("#search-input").keydown(function(event){
    	var e = event || window.event || arguments.callee.caller.arguments[0];
    	if(e && e.keyCode == 13){
    		e.preventDefault();
    		query = $(this).val();
    		pageNum = 1;
    		listLinks();
    	}
    });
	
	/**
     * 分页事件
     */
    function pagination(totalPages){
    	if(totalPages > 1) {
    		$(".pagination").show();
    		$(".pagination").bootstrapPaginator({
        		bootstrapMajorVersion: 3.0,
        		currentPage: pageNum,
        		totalPages: totalPages,
        		numberOfPages: 10,
        		itemTexts: function (type, page, currentpage) {
                    switch (type) {
        	            case "first": return "首页";
        	            case "prev" : return "上一页";
        	            case "next" : return "下一页";
        	            case "last" : return "尾页";
        	            case "page" : return page;
                    }
                },
                onPageClicked: function(event, originalEvent, type, current){
        			if(pageNum == current){
        				return false;
        			}
        			pageNum = current;
        			listLinks();
        		}
        	});
    	}else{
    		$(".pagination").hide();
    	}
    	
    	$(".pagination").find("li a").prop("title", "");
    };
    
    /**
     * 删除资源
     */
    $("#linkContent").on("click", ".j-delete", function(){
    	var id = $(this).prop("id");
    	layer.alert("确定删除?", {title: "删除资源链接"}, function(index){
    		layer.close(index);
    		$.ajax({
        		type: "POST",
        		url: contextPath + "links",
        		data: {
        			_method: "DELETE",
        			linkId: id
        		},
        		success: function(data){
        			pageNum = 1;
        			listLinks();
        		}
        	})
    	})
    })
    
    /**
     * 添加资源链接
     */
    $("#link-add").click(function(){
    	window.location.href = contextPath + "link-add";
    })
    
    /**
     * 修改资源链接
     */
    $("#linkContent").on("click", ".j-update", function(){
    	window.location.href = contextPath + "link-update?linkId=" + $(this).prop("id");
    })
});