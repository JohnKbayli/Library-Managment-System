package com.example.library.service;

import com.example.library.model.LibraryItem;
import com.example.library.model.ItemUnavailableException;
import com.example.library.repository.LibraryFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LibraryService {
    private final List<LibraryItem> items;
    private final LibraryFileRepository repository;

    public LibraryService(LibraryFileRepository repository) {
        this.repository = repository;
        this.items = new ArrayList<>();
    }

    public void loadItems() throws IOException {
        items.clear();
        items.addAll(repository.load());
    }

    public void saveItems() throws IOException {
        repository.save(items);
    }

    public void addItem(LibraryItem item) {
        if (findItem(item.getId()) != null) {
            throw new IllegalArgumentException("An item with this ID already exists.");
        }
        items.add(item);
    }

    public List<LibraryItem> getItemsSortedByTitle() {
        List<LibraryItem> sortedItems = new ArrayList<>(items);
        sortedItems.sort(Comparator.comparing(LibraryItem::getTitle,
                String.CASE_INSENSITIVE_ORDER));
        return sortedItems;
    }

    public LibraryItem findItem(int id) {
        for (LibraryItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void borrowItem(int id) throws ItemUnavailableException {
        LibraryItem item = findItem(id);
        if (item == null) {
            throw new IllegalArgumentException("No item found with this ID.");
        }
        item.borrow();
    }

    public void returnItem(int id) {
        LibraryItem item = findItem(id);
        if (item == null) {
            throw new IllegalArgumentException("No item found with this ID.");
        }
        item.returnItem();
    }
}
