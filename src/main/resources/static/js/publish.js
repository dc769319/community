$(function () {
    $(".com-tag").click(function () {
        var tagVal = $.trim($(this).text());
        var tagObj = $("#tag");
        var curTag = $.trim(tagObj.val());
        var tagList = curTag.split(",");
        if (tagList.includes(tagVal)) {
            //标签已经存在
            return false;
        }
        if (curTag) {
            tagList.push(tagVal);
            tagObj.val(tagList.join(','));
        } else {
            tagObj.val(tagVal);
        }
    });

    var testEditor = editormd("editor-md", {
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/editor/lib/",
        placeholder: "请输入问题描述",
        toolbar: true,
        toolbarAutoFixed: false,
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "webp"],
        imageUploadURL: "/file/upload"
    });
});