package Homework8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class for checking speed of operations in ArrayList & LinkedList
 */
public class CollectionPerformanceExperiment {
  private static volatile long checkSum;

  /**
   * A method to run tests
   *
   * @param args - takes nothing
   */
  public static void main(String[] args) {
    CollectionPerformanceExperiment experiment = new CollectionPerformanceExperiment();

    List<String> columns = List.of(
        "Operation",
        "ArrayList (ns)",
        "LinkedList (ns)",
        "ArrayList / LinkedList"
    );

    List<String> rows = List.of(
        "Add at the end",
        "Add at the beginning",
        "Insert in the middle",
        "Get all the elements",
        "Remove (beginning)",
        "Remove (end)"
    );

    List<Consumer<List<Integer>>> tests = List.of(
        experiment::testAppend,
        experiment::testPrepend,
        experiment::testInsert,
        experiment::testGet,
        experiment::testRemoveBeginning,
        experiment::testRemoveEnd
    );

    boolean[] fill = {false, false, true, true, true, true};

    // warm up
    List<Supplier<List<Integer>>> factories = List.of(ArrayList::new, LinkedList::new);
    for (Supplier<List<Integer>> factory : factories) {
      experiment.runTests(tests, fill, factory, 10);
    }

    // calculate results
    List<List<Long>> results = new ArrayList<>();
    for (Supplier<List<Integer>> factory : factories) {
      List<Long> result = experiment.runTests(tests, fill, factory, 10);
      results.add(result);
    }

    experiment.printResults(columns, rows, results);
  }

  private void printResults(List<String> columns, List<String> rows, List<List<Long>> results) {
    for (String column : columns) {
      System.out.printf("%-20s ", column);
    }
    System.out.print('\n');

    for (int i = 0; i < rows.size(); i++) {
      System.out.printf("%-20s ", rows.get(i));
      for (int j = 0; j < 2; j++) {
        System.out.printf("%-20s ", results.get(j).get(i));
      }
      double first = results.getFirst().get(i);
      double second = results.get(1).get(i);
      System.out.printf("%-20.4f ", first / second);
      System.out.print('\n');
    }
  }

  private List<Long> runTests(List<Consumer<List<Integer>>> tests, boolean[] fill, Supplier<List<Integer>> factory, int times) {
    long start;

    List<Long> result = new ArrayList<>(tests.size());
    for (int j = 0; j < tests.size(); j++) {
      long current = 0;
      for (int i = 0; i < times; i++) {
        List<Integer> list = factory.get();
        if (fill[j]) {
          testAppend(list);
        }
        start = System.nanoTime();
        tests.get(j).accept(list);
        current += System.nanoTime() - start;
      }
      result.add(current / times);
    }
    return result;
  }

  private void testAppend(List<Integer> list) {
    for (int i = 0; i < 10_000; i++) {
      list.add(i);
    }
  }

  private void testPrepend(List<Integer> list) {
    for (int i = 0; i < 10_000; i++) {
      list.addFirst(i);
    }
  }

  private void testInsert(List<Integer> list) {
    for (int i = 0; i < 10_000; i++) {
      list.add(list.size() / 2, 0);
    }
  }

  private void testGet(List<Integer> list) {
    long sum = 0;
    for (int i = 0; i < list.size(); i++) {
      sum += list.get(i);
    }
    checkSum = sum;
  }

  private void testRemoveBeginning(List<Integer> list) {
    while (!list.isEmpty()) {
      list.removeFirst();
    }
  }

  private void testRemoveEnd(List<Integer> list) {
    while (!list.isEmpty()) {
      list.removeLast();
    }
  }
}
