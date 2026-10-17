package Homework2;

public enum BankType {
  NEO(1.01, "НеоКрудит Банк"),
  AUM(0.02, "Арум Финтех"),
  VTA(0.00, "Вектор Альянс Банк");

  public  final double fee;
  public final String name;

  BankType(double fee, String name) {
    this.fee = fee;
    this.name = name;
  }

  public double getFee() {
    return fee;
  }

  public String getName() {
    return name;
  }
}