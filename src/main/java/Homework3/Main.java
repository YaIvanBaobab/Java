package Homework3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  static Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) throws Exception{
    Hero fighter1 = createHero();
    Hero fighter2 = createHero();
    Arena arena = new Arena(fighter1, fighter2);

    arena.startTournament();
  }

  private static Hero createHero() throws Exception{
    System.out.println();
    System.out.println("Создание нового персонажа.");

    System.out.println("Кем будет персонаж?");
    int type;
    do {
      System.out.println("1 - Воином, 2 - Магом.");
      type = inputNat();
    } while (type > 2);

    scanner.nextLine();
    System.out.println("Введите имя:");
    String name = scanner.nextLine();

    System.out.println("Введите максимальное здоровье персонажа:");
    int maxHealth = inputNat();

    System.out.println("Введите силу его базовой атаки:");
    int baseAttack = inputNat();

    if (type == 1) {
      System.out.println("Введите размер брони Воина:");
    } else {
      System.out.println("Введите запас маны у Мага:");
    }
    int special = inputNat();

    if (type == 1) {
      return new Warrior(name, maxHealth, baseAttack, special);
    } else {
      return new Mage(name, maxHealth, baseAttack, special);
    }
  }

  private static int inputNat() {
    while (true) {
      try {
        int value = scanner.nextInt();
        if (value <= 0) {
          throw new InputMismatchException();
        }
        return value;
      }  catch (Exception e) {
        System.out.println("Введите натуральное число!");
        scanner.nextLine();
      }
    }
  }
}
