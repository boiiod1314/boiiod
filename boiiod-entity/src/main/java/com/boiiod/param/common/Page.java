package com.boiiod.param.common;

import com.boiiod.utils.EntityUtil;

import java.util.ArrayList;
import java.util.List;

public class Page<Entity> {

    //数据库总行数
    private long rowCount;

    //一页多少行
    private int pageSize = 10;

    //第几页
    private int num;

    //查询起始行
    private int startRow;

    //下一页
    private int next;

    //上一页
    private int prev;

    //一共多少页
    private int pageCount;

    //显示导航起始
    private int begin;

    //显示的导航结束
    private int end;

    //首页
    private int first = 1;

    //尾页
    private int last;

    //一共显示多少导航
    private int navNum = 10;

    //分页查询之后的数据
    private List<Entity> data = new ArrayList<Entity>(0);


    public Page() {
        super();
    }

    public Page(int pageNo, int pageSize, long rowCount) {
        this(pageNo, pageSize, rowCount, 10);
    }

    public Page(int pageNo, int pageSize, long rowCount, int navNum) {
        this.navNum = navNum;
        this.rowCount = rowCount;
        this.pageSize = pageSize;
        this.pageCount = (int) Math.ceil(this.rowCount * 1.0 / this.pageSize);
        this.last = this.pageCount;
        this.num = Math.max(this.first, pageNo);
        this.num = Math.min(this.last, this.num);

        //this.startRow = Math.max(0, (this.num - 1) * this.pageSize);
        this.startRow = Math.max(0, (pageNo - 1) * this.pageSize);

        this.next = Math.min(this.last, this.num + 1);
        this.prev = Math.max(this.first, this.num - 1);

        this.begin = Math.max(this.first, this.num - (this.navNum / 2));
        this.end = Math.min(this.last, this.begin + this.navNum - 1);

        if ((this.end - this.begin) < (this.navNum - 1)) {
            this.begin = Math.max(this.first, this.last - this.navNum + 1);
        }
    }

    public static <T> void copyData(Page<T> receivePage, Page<Object> expendPage) {
        if (receivePage == null || expendPage == null) return;
        receivePage.setRowCount(expendPage.getRowCount());
        receivePage.setPageSize(expendPage.getPageSize());
        receivePage.setNum(expendPage.getNum());
        receivePage.setStartRow(expendPage.getStartRow());
        receivePage.setNext(expendPage.getNext());
        receivePage.setPrev(expendPage.getPrev());
        receivePage.setPageCount(expendPage.getPageCount());
        receivePage.setBegin(expendPage.getBegin());
        receivePage.setEnd(expendPage.getEnd());
        receivePage.setFirst(expendPage.getFirst());
        receivePage.setLast(expendPage.getLast());
        receivePage.setNavNum(expendPage.getNavNum());
    }

    /**
     * 给xxExample注入Offset和rows
     *
     * @param exampleObj
     * @param pageNo
     * @param pageSize
     * @param isMust     是不是必须, 若是且pageNo或pageSize不满足情况会自动生成默认值
     * @param <T>
     *
     * @return 成功返回pageNo和pageSize, 失败返回null
     */
    public static <T> int[] setExampleOffsetAndRows(T exampleObj, Integer pageNo, Integer pageSize, boolean isMust) {
        if (exampleObj == null) return null;
        int[] arr = getPageNoAndPageSizeAndOffset(pageNo, pageSize, isMust);
        if (arr == null) return null;
        EntityUtil.setValueToObj(exampleObj, "offset", arr[2]);
        EntityUtil.setValueToObj(exampleObj, "rows", arr[1]);
        return new int[]{arr[0], arr[1]};
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param hasDefault
     *
     * @return null 或 一个含两元素的一维数组
     */
    public static int[] getPageNoAndPageSizeAndOffset(Integer pageNo, Integer pageSize, boolean hasDefault) {
        if (pageNo == null || pageNo < 1) {
            if (!hasDefault) return null;
            pageNo = 1;
        }
        if (pageSize == null || pageSize < 1) {
            if (!hasDefault) return null;
            pageSize = 10;
        }
        return new int[]{pageNo, pageSize, (pageNo - 1) * pageSize};
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getNavNum() {
        return navNum;
    }

    public void setNavNum(int navNum) {
        this.navNum = navNum;
    }

    public List<Entity> getData() {
        return data;
    }

    public void setData(List<Entity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page [rowCount=" + rowCount + ", pageSize=" + pageSize + ", num=" + num + ", startRow=" + startRow
                + ", next=" + next + ", prev=" + prev + ", pageCount=" + pageCount + ", begin=" + begin + ", end=" + end
                + ", first=" + first + ", last=" + last + ", navNum=" + navNum + ", data=" + data + "]";
    }
}
