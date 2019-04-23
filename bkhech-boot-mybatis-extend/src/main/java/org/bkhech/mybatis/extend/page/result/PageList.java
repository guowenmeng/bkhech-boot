package org.bkhech.mybatis.extend.page.result;


import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果集，此容器的目的是在Mapper返回数据中加入分页信息，目的最终结果为PageResult
 *
 * Created by guowm on 2017/3/6.
 */
public class PageList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 6452924799509915636L;

    private int pageSize;
    private int pageNum;
    private int totalPage;
    private int totalRows;

    public PageList(List<T> list, int pageNum, int pageSize, int totalRows) {
        this.addAll(list);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalPage() {
        totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

}
