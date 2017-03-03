/**
 * Created by boiiod on 16/10/27.
 */

var boiiodEditor;
var height = $(window).height() - 15;
$(function () {
    boiiodEditor = editormd("boiiod-editormd-view-note", {
        width: "100%",
        height: height,
        syncScrolling: "single",
        path: "/statics/markdown/lib/",
        toolbarIcons: [],
        onload: function () {
            this.previewing();
            $('.fa-close').hide();
        }
    });
});