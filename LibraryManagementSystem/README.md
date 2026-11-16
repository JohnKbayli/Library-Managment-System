# Simple Library Management System

A beginner-friendly Java console application for managing books and magazines.

## Requirements

- Java 17 or newer
- IntelliJ IDEA
- Maven (IntelliJ can use the Maven wrapper/imported Maven project)

## Run the application

1. Open this folder in IntelliJ IDEA.
2. Wait for Maven to finish importing.
3. Open `src/main/java/com/example/library/Main.java`.
4. Click the green Run icon next to `main`.

You can also run it from a terminal:

```bash
mvn clean test
mvn exec:java -Dexec.mainClass="com.example.library.Main"
```

If the `exec:java` command is not available in your Maven setup, run `Main` directly from IntelliJ.

## Example use

1. Choose `1` to add a book.
2. Enter an ID, for example `1`.
3. Enter a title, for example `Clean Code`.
4. Choose `3` to list the library.
5. Choose `4` and enter `1` to borrow the book.
6. Choose `0` to save and exit.

The program stores data in `data/library.txt`. The file is created automatically when the program saves for the first time.

## Object-oriented requirements

- **Encapsulation:** `LibraryItem` stores its fields as private and exposes methods.
- **Abstraction:** `LibraryItem` is an abstract class.
- **Inheritance:** `Book` and `Magazine` extend `LibraryItem`.
- **Polymorphism:** Each child class overrides `getLoanDays()` and `getType()`.
- **Relationships:** `LibraryService` manages a collection of `LibraryItem` objects.
- **Generics and sorting:** `ArrayList<LibraryItem>` and a `Comparator` sort items by title.
- **Exception handling:** `ItemUnavailableException` prevents borrowing an item that is already borrowed. Input and duplicate IDs are also validated.
- **Persistence:** `LibraryFileRepository` saves and loads data in a text file.

## Design pattern: Factory

`LibraryItemFactory` creates either a `Book` or a `Magazine`. The rest of the application asks the factory for an item instead of deciding how to construct each concrete class. This keeps object creation in one place and makes it easier to add another item type later.

## Main design decisions and limitations

- The program uses a console menu to keep the interface simple.
- Data is stored in a plain text file, not a database.
- The application does not manage library members or due dates.
- IDs are entered by the user and must be unique.
- Titles containing the `|` character are saved with `/` instead.

## Tests

Run:

```bash
mvn test
```

The tests cover borrowing, unavailable items, and duplicate IDs.

## Project structure

- `model` — library item classes and domain exception
- `factory` — object creation
- `service` — library operations
- `repository` — file saving and loading
- `Main.java` — console menu
- `docs` — UML diagram and user stories

## Contributor

Add your name before submitting.
