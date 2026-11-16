package com.example.library;

import com.example.library.factory.LibraryItemFactory;
import com.example.library.model.Book;
import com.example.library.model.ItemUnavailableException;
import com.example.library.repository.LibraryFileRepository;
import com.example.library.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void borrowingAnAvailableBookMakesItUnavailable() throws Exception {
        LibraryItemFactory factory = new LibraryItemFactory();
        LibraryFileRepository repository = new LibraryFileRepository(
                temporaryDirectory.resolve("library.txt").toString(), factory);
        LibraryService service = new LibraryService(repository);
        Book book = new Book(1, "Java Basics", true);

        service.addItem(book);
        service.borrowItem(1);

        assertFalse(book.isAvailable());
    }

    @Test
    void borrowingAnAlreadyBorrowedBookThrowsException() throws Exception {
        Book book = new Book(1, "Java Basics", false);

        assertThrows(ItemUnavailableException.class, book::borrow);
    }

    @Test
    void duplicateIdsAreNotAllowed() {
        LibraryItemFactory factory = new LibraryItemFactory();
        LibraryFileRepository repository = new LibraryFileRepository(
                temporaryDirectory.resolve("library.txt").toString(), factory);
        LibraryService service = new LibraryService(repository);

        service.addItem(new Book(1, "First book", true));

        assertThrows(IllegalArgumentException.class,
                () -> service.addItem(new Book(1, "Second book", true)));
    }
}
