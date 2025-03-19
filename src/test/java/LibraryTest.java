import org.example.Book;
import org.example.Library;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraryTest {


    @Test
    void shouldThrowExceptionWhenAddDuplicateOfBook()
    {
        Book book1 = new Book("Effective Java", "Joshua Bloch", "9780321356680");

        Library lib = new Library();
        lib.addBook(book1);

        assertThrows(IllegalArgumentException.class, () -> lib.addBook( book1));
    }

    @Test
    void shouldThrowsExceptionWhenTryBorrowUsedBook()
    {
        Book book = new Book("Effective Java", "Joshua Bloch", "9780321356680");

        Library service = new Library();
        service.addBook(book);
        service.borrowBook(book.getIsbn());

        assertThrows(IllegalStateException.class, () -> service.borrowBook(book.getIsbn()));
    }

    @Test
    void shouldThrowsExceptionWhenBookDoesNotExists()
    {
        Library service = new Library();

        assertThrows(IllegalArgumentException.class, () -> service.borrowBook("9780321356680"));
    }
}
