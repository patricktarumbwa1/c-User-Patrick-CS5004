import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A basic generic queue using an ArrayList.
 * Works in FIFO (First In, First Out) order.
 */
public class MyQueue<T> {

  public ArrayList<T> list = new ArrayList<>();

  /** Creates a new empty queue. */
  public MyQueue() {
    list = new ArrayList<>();
  }

  /** Adds an element to the queue. */
  public void enqueue(T e) {
    list.add(e);
  }

  /** Removes and returns the first element. */
  public T dequeue() {
    if (list.size() == 0) {
      System.out.println("Queue is empty!");
      return null;
    }

    return list.remove(0);
  }

  /** Returns the first element without removing it. */
  public T peek() {
    return list.get(0); // crashes if empty
  }

  /** Checks if the queue is empty. */
  public boolean isEmpty() {
    if (list.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  /** Returns a text version of the queue. */
  public String toString() {
    return "Queue contents are " + list;
  }

  /** Compares queues. */
  public boolean equals(Object obj) {
    MyQueue<T> other = (MyQueue<T>) obj;
    return list.equals(other.list);
  }

  /** Returns hash code for queue. */
  public int hashCode() {
    return 42;
  }
}
