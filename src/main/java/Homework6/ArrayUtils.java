package Homework6;

public class ArrayUtils {
  public static <T> int findFirst(T[] array, T element) {
    if (array == null) {
      return -1;
    }
    for (int i = 0; i < array.length; i++) {
      T current = array[i];
      if (current == null) {
        if (element == null) {
          return i;
        }
        continue;
      }
      if (current.equals(element)) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    final String[] names = {"John", null, "Jane"};
    final String[] tests = {"Joe", null, "1"};
    for (String test : tests) {
      int index = ArrayUtils.findFirst(names, test);
      System.out.println(index);
    }
  }
}
