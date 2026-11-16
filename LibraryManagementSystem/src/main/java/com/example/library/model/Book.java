package com.example.library.model;

public class Book extends LibraryItem {
    public Book(int id, String title, boolean available) {
        super(id, title, available);
    }

    @Override
    public int getLoanDays() {
        return 21;
    }

    @Override
    public String getType() {
        return "BOOK";
    }
}
