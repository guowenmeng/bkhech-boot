package org.bkhech.mybatis.extend.page.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guowm on 2017/3/6.
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 6452924799509915636L;

    private int pageSize;
    private int pageNum;
    private int totalPage;
    private int totalRows;
    private List<T> data;

    public PageResult(List<T> list) {
        if (list instanceof PageList) {
            PageList pageList = (PageList) list;
            this.pageNum = pageList.getPageNum();
            this.pageSize = pageList.getPageSize();
            this.totalRows = pageList.getTotalRows();
            this.totalPage = pageList.getTotalPage();
            this.data = pageList;
        } else {
            throw new RuntimeException("construct PageResult failed, input parameter type error!");
        }
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", totalPage=" + totalPage +
                ", totalRows=" + totalRows +
                ", data=" + data +
                '}';
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
