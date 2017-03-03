package com.boiiod.consts;

/**
 * Created by boiiod on 16/10/22.
 */
public class ConstDB {
    public static int ISDEL_FALSE = 0;
    public static int ISDEL_TRUE = 1;

    public static long DEFAULT_PARENT_ID = -1L;   // 默认父节点ID

    public static final class User {
        public static final int SEX_DEFAULT = 0;    // 性别, 默认
        public static final int SEX_MALE = 1;       // 性别, 男
        public static final int SEX_FEMALE = 2;     // 性别, 女
    }

    public static final class Note {
        public static final int TYPE_FOLDER = 1;  // 类型, 笔记目录
        public static final int TYPE_NOTE = 2;    // 类型, 笔记

        public static final int STATUS_AUDIT = -1;  // 状态, 待审核
        public static final int STATUS_NO_PASS = 0; // 状态, 不通过
        public static final int STATUS_PASS_NO_OPEN = 1; // 状态, 通过, 不公开
        public static final int STATUS_PASS_OPEN = 2;    // 状态, 通过, 公开
    }

    public static final class NoteContent {
        public static final int TYPE_TXT = 1;      // 笔记内容类型, 文字
    }
}
