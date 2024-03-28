package com.example.demo.enity;

import lombok.Data;

@Data
public class SearchId {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SearchId{" +
                "id=" + id +
                '}';
    }
}
