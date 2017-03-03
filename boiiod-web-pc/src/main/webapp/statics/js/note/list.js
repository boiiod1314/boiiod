/**
 * Created by boiiod on 16/10/27.
 */

$(function () {
    document.oncontextmenu = function () {
        return false;
    }
    getNoteList(ConstDB.defaultParentId);

    $('#J_btn_addRootFolderOrNote').click(function () {
        addOrUpdateOrDeleteFolder(null, ConstDB.defaultParentId);
    })
});

/**
 * 对目录进行操作, 添加笔记或修改目录标题
 * @param e
 * @param noteId
 * @param title
 * @param parentUlObj
 * @param parentNoteId
 */
function addOrUpdateOrDeleteFolder(e, noteId, title, currUlObj, parentUlObj, parentNoteId) {
    if (e && e.button != 2) return;  // 右击

    var addFlag = 1;
    var updateFlag = 2;
    var deleteFlag = 3;

    var addNode = '';

    if (noteId == ConstDB.defaultParentId) {
        addNode = buildWinDivAddNote();
    } else {
        addNode += '<div>';
        addNode += '<div style="margin-left:10px;">';
        addNode += '<input type="radio" name="addOrUpdateOrDelete" value="' + addFlag + '" checked> 添加笔记 ';
        addNode += '<input type="radio" name="addOrUpdateOrDelete" value="' + updateFlag + '" > 修改标题 ';
        addNode += '<input type="radio" name="addOrUpdateOrDelete" value="' + deleteFlag + '" > 删除目录 ';
        addNode += '<hr style="height:1px;border:none;border-top:1px solid #555555;" />';
        addNode += '<div style="margin-left:20px;">';
        /*添加*/
        addNode += buildWinDivAddNote();
        /*修改当前节点信息*/
        addNode += buildWinDivUpdateNote(title, true);
        addNode += '</div>';
        addNode += '</div>';
    }
    /**
     * 传输数据到后台
     * @returns {boolean}
     */
    var fnOk = function () {
        var checkedVal = $('input:radio[name="addOrUpdateOrDelete"]:checked').val();

        if (noteId == ConstDB.defaultParentId || checkedVal == addFlag) {
            /**
             * 添加笔记/目录
             */
            var uTitle = $('#J_win_addNoteTitle').val();
            if (isValFalse(uTitle)) {
                boiiodMsgAlertAutoClose("标题不能为空!");
                return false;
            }
            // 笔记/目录
            var noteType = $('input[name="nodeType"]:checked').val();

            var params = {
                noteTypeId: ConstDB.NoteType.markdown,
                title: uTitle,
                parentId: noteId,
                type: noteType
            };
            var fnSucc = function (data) {
                boiiodMsgAlertAutoClose("添加成功!");
                if (noteId == ConstDB.defaultParentId) {
                    getNoteList(ConstDB.defaultParentId);  // 添加根
                } else {
                    getNoteList(noteId, null, currUlObj);
                }
                // 添加笔记成功跳转编辑页面
                if (noteType == ConstDB.Note.typeNote && isValTrue(data)) {
                    var editUrl = ConstAjaxUrl.Note.edit[0].replace('${id}', data);
                    debugger;
                    window.open(editUrl);
                }
            };
            var fnFail = function () {
            }
            boiiodAjax(ConstAjaxUrl.Note.add, params, null, fnSucc, fnFail);
            return true;
        } else if (checkedVal == updateFlag) {
            /**
             * 修改目录
             */
            var uTitle = $('#J_win_updateNoteTitle').val();
            if (isValFalse(uTitle)) {
                boiiodMsgAlertAutoClose("标题不能为空!");
                return false;
            }
            updateNoteOrFolderTitle(noteId, uTitle, parentUlObj, parentNoteId);
        } else {
            /**
             * 删除目录
             */
            deleteNoteOrFolder(noteId, parentUlObj, parentNoteId);
        }
        return true;
    };
    var winAttrJson = {title: title, width: 280, height: 140, "onok": fnOk};
    boiiodMsgWindow(addNode, winAttrJson);

    /**
     * 切换添加或修改内容
     */
    $('input:radio[name="addOrUpdateOrDelete"]').change(function () {
        var val = $(this).val();
        if (val == addFlag) {
            $('#J_win_addNote').show();
            $('#J_win_updateNoteTitle').hide();
        } else if (val == updateFlag) {
            $('#J_win_addNote').hide();
            $('#J_win_updateNoteTitle').show();
        } else {
            $('#J_win_addNote').hide();
            $('#J_win_updateNoteTitle').hide();
        }
    })
}

/**
 * 对笔记进行标题修改
 * @param e
 * @param noteId
 * @param title
 * @param ulObj
 * @param parentNoteId
 */
function updateNoteTitle(e, noteId, title, ulObj, parentNoteId) {
    if (e.button != 2) return;

    var updateFlag = 1;
    var deleteFlg = 2;

    var updateNode = '<div>' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<input type="radio" name="updateOrDelete" value="' + updateFlag + '" checked> 修改标题 ' +
        '<input type="radio" name="updateOrDelete" value="' + deleteFlg + '" > 删除笔记 ' +
        '<hr style="height:1px;border:none;border-top:1px solid #555555;" />' +
        buildWinDivUpdateNote(title) +
        '</div>';

    var fnOk = function () {
        var checkedVal = $('input:radio[name="updateOrDelete"]:checked').val();
        console.info(checkedVal);

        if (checkedVal == updateFlag) {
            /**
             * 修改笔记
             * @type {*|jQuery}
             */
            var uTitle = $('#J_win_updateNoteTitle').val();

            if (isValFalse(uTitle)) {
                boiiodMsgAlertAutoClose("标题不能为空!");
                return false;
            }
            updateNoteOrFolderTitle(noteId, uTitle, ulObj, parentNoteId);
        } else if (checkedVal == deleteFlg) {
            /**
             * 删除笔记
             */
            deleteNoteOrFolder(noteId, ulObj, parentNoteId);
        }
        return true;
    };
    var winAttrJson = {title: title, width: 230, height: 80, "onok": fnOk};
    boiiodMsgWindow(updateNode, winAttrJson);
    $('input:radio[name="updateOrDelete"]').change(function () {
        var val = $(this).val();

        if (val == updateFlag) {
            $('#J_win_updateNoteTitle').show();
        } else {
            $('#J_win_updateNoteTitle').hide();
        }
    });
}

/**
 * 更改笔记或目录的标题
 * @param noteId
 * @param uTitle
 */
function updateNoteOrFolderTitle(noteId, uTitle, ulObj, parentNoteId) {
    var params = {
        id: noteId,
        title: uTitle
    }
    var fnSucc = function () {
        boiiodMsgAlertAutoClose("修改成功!");
        getNoteList(parentNoteId, null, ulObj);
    }
    var fnFail = function (data) {
        var code = data['code'];

        if (code == ConstStatusCode.CODE_1002[0]) {
            boiiodMsgAlertAutoClose("请勿操作它的人笔记!");
        }
    }
    boiiodAjax(ConstAjaxUrl.Note.updateById, params, null, fnSucc, fnFail);
}

/**
 * 删除目录或笔记
 * @param noteId
 * @param ulObj
 * @param parentNoteId
 */
function deleteNoteOrFolder(noteId, ulObj, parentNoteId) {
    var params = {
        id: noteId,
    }
    var fnSucc = function () {
        boiiodMsgAlertAutoClose("删除成功!");
        getNoteList(parentNoteId, null, ulObj);
    }
    var fnFail = function (data) {
    }
    boiiodAjax(ConstAjaxUrl.Note.deleteById, params, null, fnSucc, fnFail);
}

/**
 * 右击弹出窗口添加节点div
 */
function buildWinDivAddNote() {
    var node = '<div id="J_win_addNote">' +
        '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<input type="radio" name="nodeType" value="' + ConstDB.Note.typeNote + '" checked> 笔记' +
        '&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<input type="radio" name="nodeType" value="' + ConstDB.Note.typeFolder + '"> 目录' +
        '<br />' +
        '<br />' +
        '标题: <input  id="J_win_addNoteTitle" value="" style="width: 180">' +
        '</div>';
    return node;
}
/**
 * 右击弹出窗口更新节点div
 */
function buildWinDivUpdateNote(title, isHide) {
    var node = '<div>' +
        '&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<input id="J_win_updateNoteTitle" value="' + title + '" style="width:200px;';

    if (isHide) node += 'display:none';

    node += '">' +
        '</div>';
    return node;
}

function getNoteList(parentId, type, ulObj) {
    if (parentId == ConstDB.defaultParentId) {
        $('ul.j_ul_note_list').remove();// 根要全清
    } else {
        $(ulObj).nextAll('ul.j_ul_note_list').remove();
    }
    var succFun = function (data) {
        buildList(data);
    };
    var failFun = function (data) {
        boiiodMsgAlert(data['msg']);
    };
    var userId = $('#j_user_id').html();
    var params = {
        type: type,
        userId: userId,
        parentId: parentId,
        isOrderByTimeDesc: true
    }
    boiiodAjax(ConstAjaxUrl.Note.getListOrPageByCondition, params, null, succFun, failFun);
}

function buildList(data) {
    var lis = '<ul class="j_ul_note_list" style="float: left;">';

    for (var index in data) {
        var note = data[index];
        var noteType = note['type'];
        var parentNoteId = note['parentId'];
        var noteId = note['id'];
        var noteTitle = note['title'];
        var countNote = note['countNote'];
        countNote = isValTrue(countNote) ? countNote : '0';

        if (noteType == ConstDB.Note.typeFolder) {
            lis += '<li >【目录】<a class="J_cls_addNote" href="javascript:;" ' +
                'onclick="getNoteList(\'' + noteId + '\', null, this.parentNode.parentNode)" ' +
                'oncontextmenu="addOrUpdateOrDeleteFolder(event, \'' + noteId + '\', \'' + noteTitle + '\', ' +
                'this.parentNode.parentNode, this.parentNode.parentNode.previousSibling, \'' + parentNoteId + '\')"' +
                '>' + note['title'] + '（' + countNote + '）</a></li>';
        } else if (noteType == ConstDB.Note.typeNote) {
            var url = ConstAjaxUrl.Note.view.replace("${id}", noteId);
            lis += '<li >《笔记》<a href="' + url + '" target="_blank" ' +
                'oncontextmenu="updateNoteTitle(event, \'' + noteId + '\', \'' + noteTitle + '\', this.parentNode.parentNode.previousSibling, \'' + parentNoteId + '\')"' +
                '>' + note['title'] + '</a></li>';
        }
    }
    lis += '</ul>';

    $('#J_div_list').append(lis);

    $('a').click(function () {
        console.info();
        $(this).css('font-weight:none');
        $(this).css('font-weight:700');
    });
}