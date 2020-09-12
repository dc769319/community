$(function () {
    /**
     * 创建一级评论
     */
    $("#comment-btn").click(function () {
        var parentId = $(this).attr("data-parentId");
        var content = $.trim($("#comment-content").val());
        createComment(parentId, content, 1);
    });

    /**
     * 创建二级评论
     */
    $(".sub-comment-area").on("click", ".sub-submit", function () {
        var parentId = $(this).attr("data-parentId");
        var content = $("#commentContent" + parentId).val();
        createComment(parentId, content, 2);
    });

    /**
     * 点击一级评论的评论按钮，加载二级评论
     */
    $(".sub-comment-coll").click(function () {
        var collapsed = $(this).hasClass("collapsed");
        if (!collapsed) {
            return;
        }
        var commentId = $(this).attr("data-parent");
        $.getJSON("/subComment/" + commentId, function (response) {
            if (response.code !== 0) {
                return;
            }
            //拼接二级评论html
            var commentList = [];
            $.each(response.data, function (key, item) {
                var commentItem = '<div class="row">' +
                    '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
                    '<div class="media">' +
                    '<div class="media-left">' +
                    '<a href="#">' +
                    '<img class="img-list img-circle" src="' + item["user"]["avatarUrl"] + '"/>' +
                    '</a></div><div class="media-body"><div class="row">' +
                    '<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
                    '<span>' + item["user"]["name"] + '</span></div></div>' +
                    '<div class="row"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
                    '<p>' + item["content"] + '</p>' +
                    '<p class="comment-date">' + time2Date(item["gmtCreate"]) + '</p>' +
                    '</div></div></div></div></div></div><hr>';
                commentList.push(commentItem);
            });
            $("#secComment" + commentId).find(".sec-comment-item").html(commentList);
        });
    });

    var testView = editormd.markdownToHTML("editor-markdown-view", {});
});

function createComment(parentId, content, type) {
    if (!content) {
        alert("评论内容不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            "content": content,
            "parentId": parentId,
            "type": type
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            if (response.code === 0) {
                location.reload();
            } else if (response.code === 1002) {
                //未登录
                var doLogin = confirm("添加回复前需要登录，是否要登录？");
                if (doLogin) {
                    window.open($("#callback-url").val());
                    window.localStorage.setItem("closeable", "true");
                }
            } else {
                alert(response.message);
            }
        }
    });
}

/**
 * int型时间转日期
 * @param time
 * @returns {string}
 */
function time2Date(time) {
    var date = new Date(time + 8 * 3600 * 1000); // 增加8小时
    return date.toJSON().substr(0, 19).replace('T', ' ');
}