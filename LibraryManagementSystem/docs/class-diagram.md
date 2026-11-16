# UML Class Diagram

```mermaid
classDiagram
    class LibraryItem {
        -int id
        -String title
        -boolean available
        +getId() int
        +getTitle() String
        +isAvailable() boolean
        +borrow() void
        +returnItem() void
        +getLoanDays()* int
        +getType()* String
    }

    class Book {
        +getLoanDays() int
        +getType() String
    }

    class Magazine {
        +getLoanDays() int
        +getType() String
    }

    class ItemUnavailableException

    class LibraryItemFactory {
        +createItem(type, id, title, available) LibraryItem
    }

    class LibraryService {
        -List~LibraryItem~ items
        -LibraryFileRepository repository
        +addItem(item) void
        +getItemsSortedByTitle() List~LibraryItem~
        +borrowItem(id) void
        +returnItem(id) void
        +saveItems() void
        +loadItems() void
    }

    class LibraryFileRepository {
        -Path filePath
        -LibraryItemFactory factory
        +save(items) void
        +load() List~LibraryItem~
    }

    LibraryItem <|-- Book
    LibraryItem <|-- Magazine
    LibraryItem ..> ItemUnavailableException
    LibraryItemFactory ..> LibraryItem : creates
    LibraryService "1" o-- "0..*" LibraryItem : manages
    LibraryService --> LibraryFileRepository
    LibraryFileRepository --> LibraryItemFactory
```

## Pattern explanation

The project uses the **Factory** pattern. `LibraryItemFactory` is responsible for creating the correct concrete item (`Book` or `Magazine`). This avoids spreading object-creation decisions throughout the application.
