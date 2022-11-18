package com.spring.app.payload;

public class FilterRequest {
    private Integer size = 0;
    private Integer page = 5;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
