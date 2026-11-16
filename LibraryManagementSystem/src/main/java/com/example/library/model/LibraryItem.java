package com.example.library.model;

public abstract class LibraryItem {
    private int id;
    private String title;
    private boolean available;

    public LibraryItem(int id, String title, boolean available) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        this.id = id;
        this.title = title;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() throws ItemUnavailableException {
        if (!available) {
            throw new ItemUnavailableException("This item is already borrowed.");
        }
        available = false;
    }

    public void returnItem() {
        available = true;
    }

    public abstract int getLoanDays();

    public abstract String getType();

    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";
        return getType() + " | ID: " + id + " | Title: " + title
                + " | Loan days: " + getLoanDays() + " | " + status;
    }
}
