package Homework8;

public interface CustomList<E> extends Iterable<E> {

  /**
   * Add element to the list
   *
   * @param element - an Object that will be put to the list
   * @throws NullPointerException - if element equals to null
   */
  void add(E element);


  /**
   *
   * @param index - index of element that you want to get
   *
   * @throws IndexOutOfBoundsException - in case index is
   *              < 0 or >= size
   * @return the element by the index
   */
  E get(int index);

  /**
   *
   * @param index - index of element that you want to remove
   * @throws IndexOutOfBoundsException - if index is invalid
   * @return value of the target
   */
  E remove(int index);

  /**
   * Get number of elements in the list
   *
   * @return number of elements
   */
  int size();

  /**
   * Checks emptiness of the list
   *
   * @return true, if the list is empty
   */
  boolean isEmpty();

}
