package com.example.books;

/**
 * Represents a node in a linked list of books.
 */
public class ElementNode implements IListOfBooks {
  private Book book;
  private IListOfBooks rest;

  /**
   * Construct a new node with the given book and the rest of the list.
   * @param book the book to store
   * @param rest the rest of the list
   */
  public ElementNode(Book book, IListOfBooks rest) {
    this.book = book;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + rest.count();
  }

  @Override
  public float totalPrice() {
    return book.getPrice() + rest.totalPrice();
  }

  @Override
  public IListOfBooks allBefore(int year) {
    if (book.before(year)) {
      return new ElementNode(book, rest.allBefore(year));
    } else {
      return rest.allBefore(year);
    }
  }

  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(this.book, rest.addAtEnd(book));
  }

  @Override
  public String toString() {
    return book.toString() + "\n" + rest.toString();
  }
}

