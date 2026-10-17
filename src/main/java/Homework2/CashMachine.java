package Homework2;

public class CashMachine implements WithdrawalOperations, DepositOperations {
  public double depositMoney(Double currentBalance, Double deposit) {
    if (deposit == null || deposit <= 0.0) {
      return currentBalance;
    }
    return currentBalance + deposit;
  }

  @Override
  public double withdrawMoney(double currentBalance, double initialAmount, BankType bankType) {
    double commission = applyCommission(initialAmount, bankType);
    double amountToWithdraw = roundResult(initialAmount + commission);
    if (amountToWithdraw > currentBalance) {
      System.out.println("На счёте недостаточно средств.");
      return currentBalance;
    }
    return roundResult(currentBalance - amountToWithdraw);
  }
}
