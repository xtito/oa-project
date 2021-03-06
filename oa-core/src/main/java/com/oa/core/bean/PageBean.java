package com.oa.core.bean;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页实体，依赖 Mybatis pageHelper
 *
 * Created by [张渊]
 * 2017/12/6 9:28
 */
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 当前页
    private int pageNum;

    // 每页显示多少条数据
    private int pageSize;

    // 当前页的数量
    private int currentSize;

    // 数据总条数
    private long total;

    // 总页码数
    private int pages;

    // 结果集
    private List<T> list;

    // 排序字段 升降序
    private String orderBy;

    // 数据请求是否成功
    private int code;

    // 请求结果
    private String msg;

    // 查询参数
    private Map<String, Object> params = new HashMap<String, Object>();

    public PageBean() {}

    public PageBean(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageBean(List<T> list) {
        if (list instanceof Page) {
            this.convertPage(list);
        }
    }

    public void put(String key, Object value) {
        this.getParams().put(key, value);
    }

    /**
     * 将Mybatis分页插件pageHelper查询的结果集，转换封装为page对象
     * @param list 结果集
     */
    public void convertPage(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.currentSize = page.size();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", currentSize=" + currentSize +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                ", orderBy='" + orderBy + '\'' +
                ", code=" + code +
                ", msg=" + msg +
                ", params=" + params +
                '}';
    }
}
