package com.example.library.repository;

import com.example.library.factory.LibraryItemFactory;
import com.example.library.model.LibraryItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LibraryFileRepository {
    private final Path filePath;
    private final LibraryItemFactory factory;

    public LibraryFileRepository(String fileName, LibraryItemFactory factory) {
        this.filePath = Path.of(fileName);
        this.factory = factory;
    }

    public void save(List<LibraryItem> items) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> lines = new ArrayList<>();
        for (LibraryItem item : items) {
            String safeTitle = item.getTitle().replace("|", "/");
            lines.add(item.getType() + "|" + item.getId() + "|" + safeTitle
                    + "|" + item.isAvailable());
        }

        Files.write(filePath, lines);
    }

    public List<LibraryItem> load() throws IOException {
        List<LibraryItem> items = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return items;
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] parts = line.split("\\|", 4);
            if (parts.length != 4) {
                continue;
            }

            try {
                int id = Integer.parseInt(parts[1]);
                boolean available = Boolean.parseBoolean(parts[3]);
                items.add(factory.createItem(parts[0], id, parts[2], available));
            } catch (IllegalArgumentException exception) {
                System.out.println("Skipped invalid data line: " + line);
            }
        }

        return items;
    }
}
