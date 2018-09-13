package com.magina.resource.domain;

public class RequestParam {
    
    private int start;
    private int size;
    private String query;
    
    public RequestParam() {}
    
    public RequestParam(int start, int size, String query) {
        this.start = start;
        this.size = size;
        this.query = query;
    }

    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    
}