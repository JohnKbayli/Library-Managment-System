package com.example.library.model;

public class Magazine extends LibraryItem {
    public Magazine(int id, String title, boolean available) {
        super(id, title, available);
    }

    @Override
    public int getLoanDays() {
        return 7;
    }

    @Override
    public String getType() {
        return "MAGAZINE";
    }
}
