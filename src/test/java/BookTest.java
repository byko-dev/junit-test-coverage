import org.example.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    void shouldCreateBookWithValidData() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884");
        assertAll(
                () -> assertEquals("Clean Code", book.getTitle()),
                () -> assertEquals("Robert C. Martin", book.getAuthor()),
                () -> assertTrue(book.isAvailable())
        );
    }

    @Test
    void shouldThrowExceptionWhenCreatingWithInvalidISBN() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book("Test", "Author", "123")
        );
    }

    @Test
    void shouldChangeAvailabilityStatus() {
        Book book = new Book("Effective Java", "Joshua Bloch", "9780321356680");
        book.borrowBook();
        assertFalse(book.isAvailable());

        book.returnBook();
        assertTrue(book.isAvailable());
    }


    @Test
    void shouldThrowExceptionConstructorParamMissing()
    {
        //ISBN
        assertThrows(IllegalArgumentException.class, () -> new Book("Effective Java", "Joshua Bloch", ""));
        assertThrows(IllegalArgumentException.class, () -> new Book("Effective Java", "Joshua Bloch", null));

        //author
        assertThrows(IllegalArgumentException.class, () -> new Book("Effective Java", "", "9780321356680"));
        assertThrows(IllegalArgumentException.class, () -> new Book("Effective Java", null, "9780321356680"));

        //title
        assertThrows(IllegalArgumentException.class, () -> new Book("", "Joshua Bloch", "9780321356680"));
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "Joshua Bloch", "9780321356680"));
    }

    @Test
    void shouldReturnTrueWhenBookEquals()
    {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884");
        Book book1 = new Book("Clean Code 2005", "Martin Robert C.", "9780132350884");

        assertTrue(book.equals(book1));
    }


    @Test
    void shouldReturnFalseWhenISBNChanges()
    {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884");
        Book book1 = new Book("Clean Code", "Robert C. Martin", "9780132350883");

        assertFalse(book.equals(book1));
    }




}
