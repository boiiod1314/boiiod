/**
 * Created by boiiod on 16/10/28.
 */

var ConstAjaxUrl = {
    Note: {
        edit: '/note/edit/${id}.htm',
        view: '/note/view/${id}.html',
        
        add: ['/note/add.login', 'POST', 'JSON'],
        updateById: ['/note/updateById.login', 'POST', 'JSON'],
        deleteById: ['/note/deleteById.login', 'POST', 'JSON'],
        getListOrPageByCondition: ['/note/getListOrPageByCondition.json', 'GET', 'JSON']
    },

    Root: {
        logout: '/logout.login',
    },

    User: {
        register: ['/user/register.json', 'POST', 'JSON'],
        loginByPhone: ['/user/loginByPhone.json', 'GET', 'JSON'],
    },
}