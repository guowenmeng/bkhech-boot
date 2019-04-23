package org.bkhech.mybatis.extend.page.param;

import org.apache.ibatis.session.RowBounds;
import org.bkhech.mybatis.extend.page.constant.PageConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page 作为输入
 *
 * Created by guowm on 2017/3/3.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -4882478724270973629L;

    private int page_size;
    private int page_num;
    private List<Map<String, OrderType>> orders = new ArrayList<Map<String, OrderType>>();

    public Page() { }

    public Page(int pageNum) {
        this.page_num = pageNum;
    }

    public Page(int pageSize, int pageNum) {
        this.page_size = pageSize;
        this.page_num = pageNum;
    }

    public RowBounds toRowBounds() {
        int offset = (getPage_num() - 1) * getPage_size();
        int limit = getPage_size();
        return new RowBounds(offset, limit);
    }

    public int getPage_size() {
        return page_size > 0 ? page_size : PageConstant.PAGE_SIZE;
    }

    public void setPage_size(int pageSize) {
        this.page_size = pageSize;
    }

    public int getPage_num() {
        return page_num > 0 ? page_num : 1;
    }

    public void setPage_num(int pageNum) {
        this.page_num = pageNum;
    }

    public List<Map<String, OrderType>> getOrders() {
        return orders;
    }

    public void setOrders(List<Map<String, OrderType>> orders) {
        this.orders = orders;
    }

    public void setAscOrders(List<String> orders) {
        settingOrder(orders, OrderType.ASC);
    }

    public void setDescOrders(List<String> orders) {
        settingOrder(orders, OrderType.DESC);
    }

    private void settingOrder(List<String> orders, OrderType orderType) {
        orders.forEach(i -> {
            Map<String, OrderType> map = new HashMap<String, OrderType>();
            map.put(i, orderType);
            this.orders.add(map);
        });
    }

    public enum OrderType {
        ASC, DESC
    }

}
