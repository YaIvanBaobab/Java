package Homework8;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static Homework8.StudentStructure.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentStructureTest {
  private static final HashMap<Integer, Student> studentsMap = new HashMap<>();
  private static final TreeMap<Integer, Student> studentsTree = new TreeMap<>(Comparator.reverseOrder());
  private static final List<Student> studentsList = List.of(
      new Student(1, "A", 0),
      new Student(2, "B", 10),
      new Student(3, "C", 3),
      new Student(4, "D", 5),
      new Student(5, "E", 7)
  );

  @BeforeAll
  public static void setUp() {
    for (Student student : studentsList) {
      studentsMap.put(student.id(), student);
      studentsTree.put(student.id(), student);
    }
  }

  private boolean checkDisorderedResult(List<Student> response, ArrayList<Integer> correct) {
    if (response.size() != correct.size()) {
      return false;
    }
    for (Student student : response) {
      if (correct.contains(student.id())) {
        correct.remove(Integer.valueOf(student.id()));
      }
      else {
        return false;
      }
    }
    return true;
  }

  private boolean checkOrderedResult(List<Student> response, ArrayList<Integer> correct) {
    if (response.size() != correct.size()) {
      return false;
    }
    for (int i = 0; i < response.size(); i++) {
      if (!(correct.get(i) == response.get(i).id())) {
        return false;
      }
    }
    return true;
  }

  @Test
  public void testFindStudentsByGrade_CommonCase() {
    var original = new HashMap<>(studentsMap);
    List<Student> result1 = findStudentsByGradeRange(studentsMap, 0, 10);
    assertTrue(checkDisorderedResult(result1, new ArrayList<>(List.of(1, 2, 3, 4, 5))));

    assertInstanceOf(ArrayList.class, result1);

    List<Student> result2 = findStudentsByGradeRange(studentsMap, 3, 6);
    assertTrue(checkDisorderedResult(result2, new ArrayList<>(List.of(3, 4))));

    assertEquals(studentsMap, original);
  }

  @Test
  public void testFindStudentsByGrade_EmptyCase() {
    List<Student> result3 = findStudentsByGradeRange(new HashMap<>(), 0, 10);
    assertTrue(result3.isEmpty());

    List<Student> result4 = findStudentsByGradeRange(studentsMap, 1, 1);
    assertTrue(result4.isEmpty());
  }

  @Test
  public void testFindStudentsByGrade_Exception() {
    assertThrows(IllegalArgumentException.class, () -> findStudentsByGradeRange(new HashMap<>(), -3, 1));

    assertThrows(IllegalArgumentException.class, () -> findStudentsByGradeRange(new HashMap<>(), 3, 13));

    assertThrows(IllegalArgumentException.class, () -> findStudentsByGradeRange(new HashMap<>(), 3, 0));
  }

  @Test
  public void testFindStudentsByGrade_ResultIsMutable() {
    List<Student> result = findStudentsByGradeRange(studentsMap, 0, 10);
    result.add(new Student(6, "F", 8));
    assertEquals(6,  result.size());
  }

  @Test
  public void testGetTopNStudentsById_CommonCase() {
    var original = new TreeMap<>(studentsTree);

    List<Student> result1 = getTopNStudentsById(studentsTree, 5);
    assertTrue(checkOrderedResult(result1, new ArrayList<>(List.of(5, 4, 3, 2, 1))));

    assertInstanceOf(ArrayList.class, result1);

    List<Student> result2 = getTopNStudentsById(studentsTree, 3);
    assertTrue(checkOrderedResult(result2, new ArrayList<>(List.of(5, 4, 3))));

    List<Student> result3 = getTopNStudentsById(studentsTree, 15);
    assertTrue(checkOrderedResult(result3, new ArrayList<>(List.of(5, 4, 3, 2, 1))));

    assertEquals(studentsTree, original);
  }


  @Test
  public void testGetTopNStudentsById_EmptyCase() {
    assertTrue(getTopNStudentsById(studentsTree, 0).isEmpty());
    assertTrue(getTopNStudentsById(new TreeMap<>(), 0).isEmpty());
    assertTrue(getTopNStudentsById(new TreeMap<>(), 10).isEmpty());
  }


  @Test
  public void testGetTopNStudentsById_Exception() {
    assertThrows(IllegalArgumentException.class, () -> getTopNStudentsById(studentsTree, -1));
  }

  @Test
  public void testGetTopNStudentsById_ResultIsMutable() {
    List<Student> result = getTopNStudentsById(studentsTree, 5);
    result.add(new Student(6, "F", 8));
    assertEquals(6,  result.size());
  }
}
