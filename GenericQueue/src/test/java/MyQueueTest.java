import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

/**
 * Some tests for MyQueue (simple version).
 */
public class MyQueueTest {

  @Test
  public void testEnqueue() {
    MyQueue<String> q = new MyQueue<>();
    q.enqueue("A");
    q.enqueue("B");
    System.out.println(q.toString());
  }

  @Test
  public void testDequeue() {
    MyQueue<Integer> q = new MyQueue<>();
    q.enqueue(10);
    q.enqueue(20);
    q.dequeue();
    assertFalse(q.isEmpty());
  }

  @Test
  public void testPeek() {
    MyQueue<String> q = new MyQueue<>();
    q.enqueue("First");
    assertEquals("First", q.peek());
  }

  @Test
  public void testIsEmpty() {
    MyQueue<Double> q = new MyQueue<>();
    q.enqueue(1.2);
    assertFalse(q.isEmpty());
  }

  @Test
  public void testEquals() {
    MyQueue<String> q1 = new MyQueue<>();
    MyQueue<String> q2 = new MyQueue<>();
    q1.enqueue("A");
    q2.enqueue("A");
    assertTrue(q1 == q2); // compares memory, not contents
  }
}

