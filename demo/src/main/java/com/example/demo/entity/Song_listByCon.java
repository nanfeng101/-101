package com.example.demo.entity;

public class Song_listByCon {
    private int id;
    private int currentPage;
    private int pageSize;
    private String style;
    private int  startIndex;
    private String act;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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
