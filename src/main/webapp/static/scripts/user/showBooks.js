layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});

$(document).ready(function () {

    //检查能否再点击上一页，下一页
    let lab1 = $("#lab1").html().trim();//获取当前页码
    let lab2 = $("#lab2").html().trim();//获取总页码

    $("#prePage").click(function () {
        if (lab1 == 1) {
            layer.msg("已经是第一页了!", {icon: 7});
            return false;
        }
        return true;
    });
    $("#nextPage").click(function () {
        if (lab1 == lab2) {
            layer.msg("已经是最后一页了!", {icon: 7});
            return false;
        }
        return true;
    });

    //点击按钮后借书
    $(".del_btn").click(function () {

        let that = $(this);

        layer.confirm('确认借书?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            let bookId = that.val();

            borrowingBook(bookId)

            layer.msg("借书成功", {icon: 1, time: 1000});

            setTimeout(function () {
                // 关闭所有 layer选项框
                parent.layer.closeAll();
            }, 1000)

        });

    });
});

//借书
function borrowingBook(bookId) {
    $.ajax({
        async: false,
        type: "post",
        url: "/userBorrowingBook",
        dataType: "json",
        data: {bookId: bookId},
        success: function (data) {
            console.log(data.toString());
            if (data.toString() == "true") {
                layer.msg('借 书 成 功!', {icon: 6, time: 2000});
            } else {
                layer.msg('借 书 失 败!', {icon: 7, time: 2000});
            }
        },
        error: function (data) {
            layer.alert(data.result);
        }
    });
};