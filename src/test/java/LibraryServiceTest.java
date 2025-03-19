import org.example.Book;
import org.example.Library;
import org.example.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    private Library library;
    private LibraryService libraryService;


    @BeforeEach
    void setUp()
    {
        library = new Library();
        libraryService = new LibraryService(library);

        library.addBook(new Book("Clean Code", "Robert C. Martin", "9780132350884"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "9780321356680"));
    }

    @Test
    void shouldBorrowBookSuccessfully()
    {
        String confirmation = libraryService.borrowBookWithConfirmation("9780132350884");

        assertAll(
                () -> assertEquals("Wypożyczono: Clean Code | Autor: Robert C. Martin", confirmation),
                () -> assertFalse(library.findBookByIsbn("9780132350884").get().isAvailable())
        );
    }

    @Test
    void shouldHandleExceptionWhenBookAlreadyBorrowed()
    {
        library.borrowBook("9780132350884");
        assertThrows(IllegalStateException.class, () -> library.borrowBook("9780132350884"));

        String result = libraryService.borrowBookWithConfirmation("9780132350884");

        assertEquals("Błąd podczas wypożyczania: Książka już wypożyczona", result);
    }

    @Test
    void shouldNotifyAboutOverdueIfBookExists() {
        Library library = new Library();
        Book book = new Book("Test Driven Development", "Kent Beck", "9780321146533");
        library.addBook(book);
        LibraryService libraryService = new LibraryService(library);

        assertDoesNotThrow(() -> libraryService.notifyAboutOverdue("9780321146533"));
    }

    @Test
    void shouldNotNotifyWhenBookDoesNotExist() {
        Library library = new Library();
        LibraryService libraryService = new LibraryService(library);

        assertDoesNotThrow(() -> libraryService.notifyAboutOverdue("0000000000000"));
    }

    @Test
    void shouldHandleBorrowingWithInvalidISBN() {
        Library library = new Library();
        LibraryService libraryService = new LibraryService(library);

        String result = libraryService.borrowBookWithConfirmation("123");

        assertEquals("Błąd podczas wypożyczania: Książka nie istnieje", result);
    }
}
