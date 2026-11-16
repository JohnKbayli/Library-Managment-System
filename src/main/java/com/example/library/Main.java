package com.example.library;

import com.example.library.factory.LibraryItemFactory;
import com.example.library.model.ItemUnavailableException;
import com.example.library.model.LibraryItem;
import com.example.library.repository.LibraryFileRepository;
import com.example.library.service.LibraryService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryItemFactory factory = new LibraryItemFactory();
        LibraryFileRepository repository =
                new LibraryFileRepository("data/library.txt", factory);
        LibraryService library = new LibraryService(repository);

        try {
            library.loadItems();
            System.out.println("Library data loaded.");
        } catch (IOException exception) {
            System.out.println("Could not load saved data: " + exception.getMessage());
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1 -> addItem(library, factory, "BOOK");
                    case 2 -> addItem(library, factory, "MAGAZINE");
                    case 3 -> showItems(library.getItemsSortedByTitle());
                    case 4 -> borrowItem(library);
                    case 5 -> returnItem(library);
                    case 6 -> {
                        library.saveItems();
                        System.out.println("Data saved.");
                    }
                    case 0 -> {
                        library.saveItems();
                        System.out.println("Data saved. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Please choose a valid option.");
                }
            } catch (ItemUnavailableException | IOException | IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Add a book");
        System.out.println("2. Add a magazine");
        System.out.println("3. Show all items (sorted by title)");
        System.out.println("4. Borrow an item");
        System.out.println("5. Return an item");
        System.out.println("6. Save data");
        System.out.println("0. Save and exit");
    }

    private static void addItem(LibraryService library,
                                LibraryItemFactory factory, String type) {
        int id = readInt("Enter item ID: ");
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();

        LibraryItem item = factory.createItem(type, id, title, true);
        library.addItem(item);
        System.out.println(type + " added successfully.");
    }

    private static void showItems(List<LibraryItem> items) {
        if (items.isEmpty()) {
            System.out.println("The library is empty.");
            return;
        }

        for (LibraryItem item : items) {
            System.out.println(item);
        }
    }

    private static void borrowItem(LibraryService library)
            throws ItemUnavailableException {
        int id = readInt("Enter the ID to borrow: ");
        library.borrowItem(id);
        System.out.println("Item borrowed successfully.");
    }

    private static void returnItem(LibraryService library) {
        int id = readInt("Enter the ID to return: ");
        library.returnItem(id);
        System.out.println("Item returned successfully.");
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
}
