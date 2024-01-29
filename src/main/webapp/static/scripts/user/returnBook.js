layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});

$(document).ready(function () {
    $(".return_btn").click(function () {

        let that = $(this);

        layer.confirm('确认还书?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            let bookId = that.val();

            returnBook(bookId);

            layer.msg("还书成功", {icon: 1, time: 1000});

            setTimeout(function () {
                // 关闭所有 layer选项框
                parent.layer.closeAll();
            }, 1000)

        });

    });
});

function returnBook(bookId) {
    $.ajax({
        async: false,
        type: "post",
        url: "/userReturnBook",
        dataType: "json",
        data: {bookId: bookId},
        success: function (data) {

            if (data.toString() == "true") {
                layer.msg('还书成功!!', {icon: 6, time: 2000});
                $("#bookId").val('');
            } else {
                layer.msg('还书失败!', {icon: 7, time: 2000});
                $("#bookId").val('');
            }
        },
        error: function (data) {
            alert(data.result);
        }
    });
};