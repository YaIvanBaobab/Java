package Homework2;

import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  public static Scanner scanner;
  public static CashMachine cashMachine = new CashMachine();

  public static Double round(double amount) {
    return Math.round(amount * 100.0) / 100.0;
  }

  public static void main(String[] args) throws Exception {
    if ("1".equals(System.getenv("TEST"))) {
      System.setIn(new FileInputStream("./src/main/resources/input.txt"));
    }

    scanner = new Scanner(System.in);

    System.out.println("\nДобро пожаловать в сервис ВсеБанки.онлайн!");
    Account etalon = new Account(12345, 999, 10000.00, BankType.AUM);
    if (authorisation(etalon)) {
      testDepositMoney(etalon);
      testWithdrawMoney(etalon);
    }
  }

  public static boolean authorisation(Account account) throws Exception {
    System.out.println("""
        Введите через пробел номер вашей карты и пин-код от неё.
        (Например, "12345 678".)""");

    int cartNumber = safeEnterInt();
    int pinCode = safeEnterInt();
    if (!account.getCartNumber().equals(cartNumber)
        || !account.getPinCode().equals(pinCode)) {
      System.out.println("Доступ запрещён!");
      return false;
    }
    return true;
  }

  public static void testDepositMoney(Account account) {
    System.out.print("Введите сумму, которую хотите положить на счёт: ");
    double deposit = safeEnterDouble();
    double updatedBalance = cashMachine.depositMoney(account.getBalance(), deposit);
    account.setBalance(updatedBalance);
    System.out.println("Обновленный баланс: " + updatedBalance);
  }

  public static void testWithdrawMoney(Account account) {
    System.out.print("Введите сумму, которую хотите снять со счёта: ");
    double withdraw = safeEnterDouble();
    double updatedBalance = cashMachine.withdrawMoney(account.getBalance(), withdraw, account.getBankType());
    account.setBalance(updatedBalance);
    System.out.println("Обновленный баланс: " + updatedBalance);
  }

  public static double safeEnterDouble() {
    double value;
    while (true) {
      try {
        value = scanner.nextDouble();
        return round(value);
      } catch (InputMismatchException e) {
        System.out.print("Неверный ввод! Введите дробное число: ");
        scanner.next();
      }
    }
  }

  ;

  public static int safeEnterInt() {
    int value;
    while (true) {
      try {
        value = scanner.nextInt();
        return value;
      } catch (InputMismatchException e) {
        System.out.print("Неверный ввод! Введите целое число: ");
        scanner.next();
      }
    }
  }
}