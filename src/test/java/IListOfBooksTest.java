import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.books.EmptyNode;
import com.example.books.ElementNode;
import com.example.books.Book;
import com.example.books.IListOfBooks;

public class IListOfBooksTest {

  @Test
  void testCount() {
    EmptyNode empty = new EmptyNode();
    assertEquals(0, empty.count());

    Book book1 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, 18.99f);
    ElementNode list = new ElementNode(book1, empty);
    assertEquals(1, list.count());
  }

  @Test
  void testTotalPrice() {
    EmptyNode empty = new EmptyNode();
    assertEquals(0.0f, empty.totalPrice());

    Book book1 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, 18.99f);
    Book book2 = new Book("Educated", "Tara Westover", 2018, 14.50f);
    ElementNode list = new ElementNode(book1, new ElementNode(book2, empty));
    assertEquals(33.49f, list.totalPrice(), 0.001f);
  }

  @Test
  void testAllBefore() {
    EmptyNode empty = new EmptyNode();
    assertEquals(empty.toString(), empty.allBefore(2025).toString());

    Book book1 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, 18.99f);
    Book book2 = new Book("Educated", "Tara Westover", 2018, 14.50f);
    ElementNode list = new ElementNode(book1, new ElementNode(book2, empty));

    IListOfBooks result = list.allBefore(2015);
    assertEquals(1, result.count());
    assertTrue(result.toString().contains("Sapiens"));
  }

  @Test
  void testAddAtEnd() {
    EmptyNode empty = new EmptyNode();
    Book book1 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, 18.99f);
    IListOfBooks list = empty.addAtEnd(book1);
    assertEquals(1, list.count());

    Book book2 = new Book("Educated", "Tara Westover", 2018, 14.50f);
    IListOfBooks extended = list.addAtEnd(book2);
    assertEquals(2, extended.count());
  }

  @Test
  void testToString() {
    EmptyNode empty = new EmptyNode();
    assertEquals("[]", empty.toString());

    Book book1 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011, 18.99f);
    ElementNode list = new ElementNode(book1, empty);
    assertTrue(list.toString().contains("Sapiens"));
    assertTrue(list.toString().startsWith("["));
    assertTrue(list.toString().endsWith("]"));
  }

  @Test
  void testBeforeMethodInBook() {
    Book book = new Book("The Power of Habit", "Charles Duhigg", 2012, 16.00f);
    assertTrue(book.before(2015));
    assertFalse(book.before(2010));
  }

  @Test
  void testCheaperThanMethodInBook() {
    Book book1 = new Book("Atomic Habits", "James Clear", 2018, 12.99f);
    Book book2 = new Book("Educated", "Tara Westover", 2018, 14.50f);
    assertTrue(book1.cheaperThan(book2));
    assertFalse(book2.cheaperThan(book1));
  }

  @Test
  void testElementNodeCountMultiple() {
    EmptyNode empty = new EmptyNode();
    Book book1 = new Book("The Body", "Bill Bryson", 2019, 20.00f);
    Book book2 = new Book("Thinking, Fast and Slow", "Daniel Kahneman", 2011, 15.00f);
    ElementNode list = new ElementNode(book1, new ElementNode(book2, empty));
    assertEquals(2, list.count());
    assertNotEquals(3, list.count());
  }

  @Test
  void testElementNodeTotalPriceMultiple() {
    EmptyNode empty = new EmptyNode();
    Book book1 = new Book("The Body", "Bill Bryson", 2019, 20.00f);
    Book book2 = new Book("Thinking, Fast and Slow", "Daniel Kahneman", 2011, 15.00f);
    ElementNode list = new ElementNode(book1, new ElementNode(book2, empty));
    assertEquals(35.00f, list.totalPrice(), 0.001f);
    assertNotEquals(40.00f, list.totalPrice());
  }

  @Test
  void testAllBeforeEdgeCases() {
    EmptyNode empty = new EmptyNode();
    Book book = new Book("The Wright Brothers", "David McCullough", 2015, 13.50f);
    ElementNode list = new ElementNode(book, empty);

    IListOfBooks result1 = list.allBefore(2010);
    assertEquals(0, result1.count());

    IListOfBooks result2 = list.allBefore(2020);
    assertEquals(1, result2.count());
  }
}
