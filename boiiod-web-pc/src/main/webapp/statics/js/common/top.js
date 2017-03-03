/**
 * Created by boiiod on 2017/2/25.
 */

var loginRegisterWin;
$(function () {
    $('#j_login_register').mouseup(function () {
        var loginFlag = 1;
        var registerFlag = 2;

        var node = '<div>';
        node += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
        node += '<input type="radio" name="loginOrRegister" value="' + loginFlag + '" checked> 登录 ';
        node += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
        node += '<input type="radio" name="loginOrRegister" value="' + registerFlag + '" > 注册 ';
        node += '<hr style="height:1px;border:none;border-top:1px solid #555555;" />';
        node += buildLoginNode();
        node += buildRegisterNode();
        node += '</div>';

        var fnOk = function () {
            var checkedVal = $('input:radio[name="loginOrRegister"]:checked').val();

            if (checkedVal == loginFlag) {
                /**
                 * 登录
                 */
                var phone = $('#j_login_phone').val();
                var password = $('#j_login_password').val();
                if (!isPhone(phone)) {
                    boiiodMsgAlertAutoClose("手机号不正确!");
                    return false;
                }
                if (isValFalse(password)) {
                    boiiodMsgAlertAutoClose("密码不能为空!");
                    return false;
                }
                var params = {
                    phone: phone,
                    password: password
                };
                var fnSucc = function () {
                    boiiodMsgAlertAutoClose("登录成功!");
                    location.reload();
                };
                var fnFail = function (data) {
                };
                boiiodAjax(ConstAjaxUrl.User.loginByPhone, params, null, fnSucc, fnFail);
            } else if (checkedVal == registerFlag) {
                /**
                 * 注册
                 */
                var phone = $('#j_register_phone').val();
                if (!isPhone(phone)) {
                    boiiodMsgAlertAutoClose("手机号不正确!");
                    return false;
                }
                var nickname = $('#j_register_nickname').val();
                if (isValFalse(nickname)) {
                    boiiodMsgAlertAutoClose("昵称不能为空!");
                    return false;
                }
                var password = $('#j_register_password').val();
                var confirmPassword = $('#j_register_password_confirm').val();
                if (isValFalse(password) ||
                    isValFalse(confirmPassword)) {
                    boiiodMsgAlertAutoClose("密码与确认密码不能为空!");
                    return false;
                }
                if (password != confirmPassword) {
                    boiiodMsgAlertAutoClose("两次密码输入不一致!");
                    return false;
                }
                var sex = $('input:radio[name="sex"]:checked').val();

                var params = {
                    phone: phone,
                    password: password,
                    nickname:nickname,
                    sex: sex,
                };
                var fnSucc = function () {
                    boiiodMsgAlertAutoClose("注册成功!");
                    location.reload();
                };
                var fnFail = function (data) {
                    var code = data['code'];
                    
                    if (code == ConstStatusCode.CODE_10012[0]) {
                        boiiodMsgAlertAutoClose(ConstStatusCode.CODE_10012[1]);
                        return;
                    }
                    boiiodMsgAlertAutoClose("注册失败, 别找茬, 自己找原因!")
                };
                boiiodAjax(ConstAjaxUrl.User.register, params, null, fnSucc, fnFail);
                return true;
            }
        };

        var winAttr = {
            title: '登录•注册',
            width: 230,
            height: 210,
            onok: fnOk
        }
        boiiodMsgWindow(node, winAttr);

        /**
         * 登录, 注册切换
         */
        $('#j_register').hide();
        $('input:radio[name="loginOrRegister"]').change(function () {
            var checkedVal = $(this).val();
            if (checkedVal == loginFlag) {
                $('#j_login').show();
                $('#j_register').hide();
            } else if (checkedVal == registerFlag) {
                $('#j_login').hide();
                $('#j_register').show();
            }
        });
    });
});

function buildLoginNode() {
    var node = '<div id="j_login">';
    node += '<br />';
    node += '手机号: <input id="j_login_phone" value=""/><br /><br />';
    node += '密&nbsp;&nbsp;&nbsp;&nbsp;码: <input id="j_login_password" type="password" value=""/>';
    node += '</div>';
    return node;
}

function buildRegisterNode() {
    var node = '<div id="j_register">';
    node += '昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:&nbsp;';
    node += '<input id="j_register_nickname" value=""/><br />';
    node += '手&nbsp; 机&nbsp;号:&nbsp;<input id="j_register_phone" value="" style="margin:5px 0"/><br />';
    node += '密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;';
    node += '<input id="j_register_password" type="password" value=""/><br />';
    node += '确认密码:&nbsp;<input id="j_register_password_confirm" type="password" value="" style="margin:5px 0;"/><br />';
    node += '性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:&nbsp;&nbsp;&nbsp;&nbsp;';
    node += '<input name="sex" type="radio" value="' + ConstDB.User.sexMale + '" checked/>&nbsp;&nbsp;男';
    node += '&nbsp;&nbsp;&nbsp;&nbsp;'
    node += '<input name="sex" type="radio" value="' + ConstDB.User.sexFemale + '"/>&nbsp;&nbsp;女';
    node += '</div>';
    return node;
}