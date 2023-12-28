package com.example.demo.entity;

public class MvByCon {
    private int currentPage;
    private int pageSize;
    private int  startIndex;
    private String act;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }
}
