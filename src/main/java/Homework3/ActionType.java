package Homework3;

public enum ActionType {
  BASE_ATTACK("базовая атака"),
  SPECIAL_SKILL("особое умение"),
  REST("пропуск хода");

  private final String meaning;

  ActionType(String meaning) {
    this.meaning = meaning;
  }

  public String getMeaning() {
    return this.meaning;
  }
}
