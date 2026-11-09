package Homework8;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

  @Test
  void testNewListEmptiness() {
    CustomArrayList<Integer> list = new CustomArrayList<>(12);
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
  }

  @Test
  void testNegativeCapacity() {
    assertThrows(IllegalArgumentException.class, () -> new CustomArrayList<>(-1));
  }

  @Test
  void testKeepingElementsInOrder() {
    CustomArrayList<Integer> list = new CustomArrayList<>();
    for (int i = 0; i < 3; i++) {
      list.add(i);
    }

    for (int i = 0; i < 3; i++) {
      assertEquals(i, list.get(i));
    }
  }

  @Test
  void testResizingAfterAdding10Elements() {
    CustomArrayList<Integer> list = new CustomArrayList<>();
    for (int i = 0; i < 20; i++) {
      list.add(i);
    }

    assertEquals(20, list.size());
    for (int i = 0; i < 20; i++) {
      assertEquals(i, list.get(i));
    }
  }

  @Test
  void testGetAndRemoveIndexChecking() {
    CustomArrayList<Integer> list = new CustomArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    int[] tests = {-3, list.size()};
    for (int test : tests) {
      assertThrows(IndexOutOfBoundsException.class, () -> list.get(test));
      assertThrows(IndexOutOfBoundsException.class, () -> list.remove(test));
    }
  }

  @Test
  void testRemoveExtremeElements() {
    CustomArrayList<Integer> list = new CustomArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    int[] indexes = {0, 8, 4};
    int[] expectedValues = {0, 9, 5};

    for (int i = 0; i < indexes.length; i++) {
      assertEquals(expectedValues[i], list.remove(indexes[i]));
    }
  }

  @Test
  void testAddNull() {
    CustomArrayList<Integer> list = new CustomArrayList<>();
    assertThrows(NullPointerException.class, () -> list.add(null));
  }

  @Test
  void testIterator() {
    CustomArrayList<Integer> list = new CustomArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    Iterator<Integer> iterator = list.iterator();
    assertTrue(iterator.hasNext());
    for (int i = 0; i < 10; i++) {
      assertEquals(i, iterator.next());
    }
    assertThrows(NoSuchElementException.class, iterator::next);
    assertFalse(iterator.hasNext());
  }

}
