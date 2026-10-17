package Homework2;

public interface WithdrawalOperations extends Operations{
  double withdrawMoney(double currentBalance, double withdrawalAmount, BankType bankType);

  default double applyCommission(Double amount, BankType bankType) {
    if (amount == null || bankType == null) {
      return 0.00;
    }
    double commission = amount * bankType.getFee();
    return roundResult(commission);
  }
}
