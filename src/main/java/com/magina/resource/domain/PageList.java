package com.magina.resource.domain;

import java.util.List;

public class PageList<T> {

    private int record;
    private int size;
    private List<T> data;
    private int totalPages;
    
    public PageList() {}

    public PageList(int record, int size, List<T> data) {
        this.record = record;
        this.size = size;
        this.data = data;
        init();
    }

    private void init() {
        this.totalPages = record % size == 0 ? record / size : record / size + 1;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
}
