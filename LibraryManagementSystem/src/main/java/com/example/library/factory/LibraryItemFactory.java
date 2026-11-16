package com.example.library.factory;

import com.example.library.model.Book;
import com.example.library.model.LibraryItem;
import com.example.library.model.Magazine;

public class LibraryItemFactory {
    public LibraryItem createItem(String type, int id, String title, boolean available) {
        if (type.equalsIgnoreCase("BOOK")) {
            return new Book(id, title, available);
        }

        if (type.equalsIgnoreCase("MAGAZINE")) {
            return new Magazine(id, title, available);
        }

        throw new IllegalArgumentException("Unknown item type: " + type);
    }
}
