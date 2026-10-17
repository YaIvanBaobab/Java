package Homework2;

public class Account {
  public Integer cartNumber;
  public Integer pinCode;
  public Double balance;
  public BankType bankType;

  Account(int cartNumber, int pinCode, double balance, BankType bankType) throws Exception {
    if (cartNumber < 10000 || cartNumber > 99999) {
      throw new IllegalArgumentException("Cart number has to consist of 5 digits");
    }
    if (pinCode < 100 || pinCode > 999) {
      throw new IllegalArgumentException("Pin code has to consist of 3 digits");
    }
    if (bankType == null) {
      bankType = BankType.NEO;
    }
    this.cartNumber = cartNumber;
    this.balance = balance;
    this.pinCode = pinCode;
    this.bankType = bankType;
  }

  public Integer getCartNumber() {
    return cartNumber;
  }

  public Integer getPinCode() {
    return pinCode;
  }

  public Double getBalance() {
    return balance;
  }

  public BankType getBankType() {
    return bankType;
  }

  public void setBalance(Double newBalance) {
    if (newBalance != null) {
      balance = newBalance;
    }
  }

  public String toString() {
    return String.format("%s Карта: %d, Баланс: %.2f руб.", bankType.getName(), cartNumber, balance);
  }
}
