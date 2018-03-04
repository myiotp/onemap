package com.onemap.utl.pages;

import java.util.ArrayList;
@SuppressWarnings("serial")
public final class PaginatedArrayList<V> extends ArrayList<V> implements PaginatedList<V> {
    /**
     * 当前页数
     */
    private int pageIndex = 1;

    /**
     * 每页显示记录数，当其值小于0时。应该是返回所有记录
     */
    private int pageSize = 20;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总纪录数
     */
    private int totalRec;

    /**
     * 开始记录数
     */
    private int startPos;

    /**
     * 结束记录数
     */
    private int endPos;

    /**
     * 显示页的第一页
     */
    private int begin;
    /**
     * 显示页的最后一页
     */
    private int end;

    public PaginatedArrayList() {

    }

    /**
     * 通过总记录数、每页数目、页大小来构造实例，同时，还计算其它的值。<br>
     * ------------------------------------------------------------------------
     * 
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @param totalRec 总纪录数
     */
    public PaginatedArrayList(int totalRec, int pageIndex, int pageSize) {
        super(pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
        calculate(totalRec, pageIndex, pageSize, DEFAULT_DISPALY_PAGE_COUNT);
    }

    /**
     * @param pageIndex 当前页
     * @param pageSize 页大小
     * @param totalRec 总纪录数
     * @roseuid 40B2F47B0219
     * @param displayPageSize 共显示多少页
     */
    public PaginatedArrayList(int totalRec, int pageIndex, int pageSize, int displayPageSize) {
        super(pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
        if (displayPageSize < 0) {
            displayPageSize = DEFAULT_DISPALY_PAGE_COUNT;
        }
        calculate(totalRec, pageIndex, pageSize, displayPageSize);
    }

    public void setPageInfo(int totalRec, int pageIndex, int pageSize) {
        calculate(totalRec, pageIndex, pageSize, DEFAULT_DISPALY_PAGE_COUNT);
    }

    /**
     * 通过总记录数、当前页、每页记录数，计算其它属性<br>
     * ------------------------------------------------------------------------
     * 
     * @param recNum 总纪录数
     * @param pageIndex 当前页
     * @param pageSize 页大小
     */
    private void calculate(int recNum, int pageIndex, int pageSize, int displayPageSize) {
        // ---------------------------------
        // 总记录数小于1没有任何任何计算意义。
        // ---------------------------------
        if (recNum < 1) {
            this.begin = 1;
            this.end = 1;
            return;
        }

        // ---------------------------------
        // 页大小为0的时候，无意义
        // ---------------------------------
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        // ---------------------------------
        // 当前页小于1的时候。默认为第1页
        // ---------------------------------
        if (pageIndex < 1)
            pageIndex = 1;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalRec = recNum;

        if (pageSize > 0) {
            // ---------------------------------
            // 总页数=总记录/页大小，不能整除就要进一位
            // ---------------------------------
            if (recNum % pageSize > 0)
                totalPage = recNum / pageSize + 1;
            else
                totalPage = recNum / pageSize;

            // ---------------------------------
            // 当前页不能大于总页数。
            // ---------------------------------
//            if (pageIndex > totalPage)
//                this.pageIndex = totalPage;
            
            if (this.pageIndex < 1)
                this.pageIndex = 1;
            // --------------------------------------
            // 计算该显示的第一页最后一页的值
            // --------------------------------------
            boolean flag = displayPageSize % 2 == 0;
            int mid = flag ? displayPageSize / 2 : displayPageSize / 2 + 1;
            if (this.pageIndex <= mid) {
                this.begin = 1;
                this.end = displayPageSize >= this.totalPage ? this.totalPage : displayPageSize;
                this.end = this.end <= this.totalPage ? this.end : this.totalPage;
            } else if (this.pageIndex >= this.totalPage - (mid - 1)) {
                this.end = this.totalPage;
                this.begin = 1 > this.totalPage - (mid - 1) ? 1 : this.totalPage - (displayPageSize - 1);
                this.begin = this.begin > 0 ? this.begin : 1;
            } else {
                if (flag) {
                    this.begin = this.pageIndex - (mid - 1);
                    this.end = this.pageIndex + mid;
                } else {
                    this.begin = this.pageIndex - (mid - 1);
                    this.end = this.pageIndex + (mid - 1);
                }
            }

            // ---------------------------------
            // 计算数据库记录的开始处和结束处。
            // ---------------------------------
            startPos = (this.pageIndex - 1) * pageSize;
            endPos = this.pageIndex * pageSize;
        } else {
            // ---------------------------------
            // 页大小小于0的时候，应该是所有记录数
            // ---------------------------------
            startPos = 0;
            endPos = recNum;
            this.begin = 1;
            this.end = 1;
        }

    }

    /**
     * 取得每页的记录数
     * 
     * @return 第页记录数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 取得共有多少页
     * 
     * @return 总页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 取得总记录数
     * 
     * @return 总记录数
     */
    public int getTotalRec() {
        return totalRec;
    }

    /**
     * 取得当前页
     * 
     * @return 当前页
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 判断当前页是不是第一页
     * 
     * @return 结果
     */
    public boolean isFirstPage() {
        return pageIndex == 1;
    }

    /**
     * 判断当前页是不是最后一页
     * 
     * @return 结果
     */
    public boolean isLastPage() {
        return pageIndex == totalPage;
    }

    /**
     * 符合记录范围的开始位置
     * 
     * @return 位置
     */
    public int getStartPos() {
        return startPos;
    }

    public int getBegin() {
        return this.begin;
    }

    public int getEnd() {
        return this.end;
    }

    /**
     * 符合记录范围的结束位置
     * 
     * @return 结束位置
     */
    public int getEndPos() {
        return endPos;
    }

}
