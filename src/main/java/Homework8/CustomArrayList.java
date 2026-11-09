package Homework8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Customised array list
 *
 * @param <E> - class of objects, that will be stored in the list
 */
public class CustomArrayList<E> implements CustomList<E> {
  private Object[] list;
  private int size;

  /**
   * A list constructor, basically capacity of list equals 10
   */
  public CustomArrayList() {
    this(10);
  }

  /**
   * A list constructor
   *
   * @param capacity - initial positive capacity of the list
   * @throws IllegalArgumentException - if capacity is <= 0
   */
  public CustomArrayList(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    list = new Object[capacity];
  }

  @Override
  public void add(E element) {
    if (element == null) {
      throw new NullPointerException();
    }
    if (size == list.length) {
      extendCapacity();
    }
    list[size] = element;
    size++;
  }

  private void extendCapacity() {
    list = Arrays.copyOf(list, (int) Math.ceil(list.length * 1.5));
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return (E) list[index];
  }

  @Override
  public E remove(int index) {
    E element = get(index);
    System.arraycopy(list, index + 1, list, index, size - index - 1);
    size--;
    list[size] = null;

    return element;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * An implementation of the Iterable interface method 'iterator'
   *
   * @return an Iterator<E> that let you iterate over the list
   */
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int cursor = 0;

      @Override
      public boolean hasNext() {
        return cursor < size;
      }

      @Override
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return (E) list[cursor++];
      }
    };
  }
}
