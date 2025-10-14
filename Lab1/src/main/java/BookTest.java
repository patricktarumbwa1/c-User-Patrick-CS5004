import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {

  private Book dreams;

  @BeforeEach
  void setUp(){
    this.dreams = new Book("dreams", "Sigmund Freud", "250");
  }

  @Test
  void getTitle() {
    Assertions.assertEquals("dreams", this.dreams.getTitle());
  }

  @Test
  void getAuthor(){
    Assertions.fail("Not yet implemented");

  }
  @Test
  void getPages(){
    Assertions.fail("Not yet implemented");
  }

