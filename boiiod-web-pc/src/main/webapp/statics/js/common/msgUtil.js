/**
 * Created by boiiod on 16/10/28.
 */

function boiiodMsgAlert(msg, title) {
    if (!title) title = "消息";
    var d = dialog({
        title: title,
        content: msg
    });
    d.show();
}

function boiiodMsgConfirm(msg) {
    return confirm(msg);
}

function boiiodMsgBubble(msg) {
    var d = dialog({
        content: msg,
        quickClose: true// 点击空白处快速关闭
    });
    d.show(document.getElementById('quickref-bubble'));
}

function boiiodMsgAlertAutoClose(msg, time) {
    if (!time) time = 1000;
    var d = dialog({
        content: msg,
        quickClose: true
    });
    d.show();
    setTimeout(function () {
        d.close().remove();
    }, time);
}

function boiiodMsgWindow(content, winAttrJson) {
    var title = "提示";
    var width = 200;
    var height = 50;
    var onok = function () {
        this.title = "提交中...";
        return true;
    };
    var onclose = function () {
    };
    if (winAttrJson) {
        if (winAttrJson['title']) title = winAttrJson['title'];
        if (winAttrJson['width']) width = winAttrJson['width'];
        if (winAttrJson['height']) height = winAttrJson['height'];
        if (winAttrJson['onok']) onok = winAttrJson['onok'];
        if (winAttrJson['onclose']) onclose = winAttrJson['onclose'];
    }
    var d = dialog({
        title: title,
        content: content,
        okValue: '确定',
        ok: onok,
        onclose: onclose,
        cancelValue: '取消',
        cancel: function () {
        }
    });
    d.width(width).height(height).show();
}