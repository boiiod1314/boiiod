/**
 * Created by boiiod on 16/10/28.
 */

var boiiodAjax = function (ajaxInfo, ajaxParams, ajaxContext, successFun, failFun) {
    $.ajax({
        url: ajaxInfo[0],
        type: ajaxInfo[1],
        dataType: ajaxInfo[2],
        data: ajaxParams,
        success: function (data) {
            operateMyAjaxData(data, ajaxContext, successFun, failFun);
        }
    })
}

var operateMyAjaxData = function (data, ajaxContext, successFun, failFun) {
    var code = data['code'];
    if (code == ConstStatusCode.CODE_200[0]) {
        if (successFun) successFun(data['data'], ajaxContext);
    } else {
        if (code == ConstStatusCode.CODE_201[0]) {
            boiiodMsgAlertAutoClose(ConstStatusCode.CODE_201[1], 3000);
            return;
        } else if (code == ConstStatusCode.CODE_400[0]) {
            boiiodMsgAlertAutoClose(ConstStatusCode.CODE_400[1], 3000);
            return;
        } else if (code == ConstStatusCode.CODE_405[0]) {
            boiiodMsgAlertAutoClose(ConstStatusCode.CODE_405[1], 3000);
            return;
        } else if (code == ConstStatusCode.CODE_500[0]) {
            boiiodMsgAlertAutoClose(ConstStatusCode.CODE_501[1], 3000);
            return;
        } else if (code == ConstStatusCode.CODE_501[0]) {
            boiiodMsgAlertAutoClose(ConstStatusCode.CODE_501[1], 3000);
            return;
        }
        if (failFun) failFun(data, ajaxContext);
    }
}