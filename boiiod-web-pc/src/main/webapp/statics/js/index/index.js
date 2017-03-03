/**
 * Created by boiiod on 2017/2/25.
 */

$(function () {
    // getNoteList(1, 20);
})

function getNoteList(pageNo, pageSize) {
    var params = {
        type: ConstDB.Note.typeNote,
        isOrderByTimeDesc: true,
        pageNo: pageNo,
        pageSize: pageSize
    };
    var fnSucc = function (data) {
        var node = '<ul>';

        var datas = data['data'];
        for (var i in datas) {
            var d = datas[i];
            var noteId = d['id'];
            var noteTitle = d['title'];
            var noteCreateDatetime = d['createDatetime'];

            var url = ConstAjaxUrl.Note.view.replace('${id}', noteId);

            node += '<li>' +
                '<a href="' + url + '" target="_blank">' + noteTitle + '</a>' +
                '</li>';
        }
        node += '</ul>';
        $('#j_note_list').append(node);
    };
    boiiodAjax(ConstAjaxUrl.Note.getListOrPageByCondition, params, null, fnSucc);
}