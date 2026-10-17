package Homework2;

public interface Operations {
  default double roundResult(double amount) {
    return Math.round(amount * 100.0) / 100.0;
  }
}
