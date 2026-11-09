package Homework8;

/**
 * Immutable object with which we work in the 3rd task
 *
 * @param id - a unique positive student identifier
 * @param name - name of student
 * @param grade - in the range from 0.0 to 10.0
 */
public record Student(int id, String name, double grade) {

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Student)) {
      return false;
    }
    return this.id == ((Student) o).id;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }
}
