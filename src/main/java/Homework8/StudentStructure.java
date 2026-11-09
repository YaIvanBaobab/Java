package Homework8;

import java.util.*;

/**
 * Structure that let get students by their grade range and id
 */
public class StudentStructure {
  /**
   *
   * @param students - Map of students and their identifiers where we search
   * @param minGrade - the lower bound of search
   * @param maxGrade - the upper bound of search
   * @return ArrayList with all suitable students
   */
  public static List<Student> findStudentsByGradeRange(
      Map<Integer, Student> students,
      double minGrade,
      double maxGrade
      ) {
    if (minGrade > maxGrade || minGrade < 0 || maxGrade > 10) {
      throw new IllegalArgumentException();
    }
    List<Student> studentsList = new ArrayList<>();
    for (Student student : students.values()) {
      if (student.grade() >= minGrade && student.grade() <= maxGrade) {
        studentsList.add(student);
      }
    }
    return studentsList;
  }

  /**
   *
   * @param students - Map of students and their identifiers where we search
   * @param n - number of students that we want to get
   * @return ArrayList with all suitable students
   */
  public static List<Student> getTopNStudentsById(
      NavigableMap<Integer, Student> students,
      int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    List<Student> studentsList = new ArrayList<>();
    int cnt = 0;
    for (Student student : students.values()) {
      if (cnt >= n) {
        break;
      }
      studentsList.add(student);
      cnt++;
    }
    return studentsList;
  }

}
