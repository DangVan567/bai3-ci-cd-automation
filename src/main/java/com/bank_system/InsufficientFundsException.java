package com.bank_system;

import java.util.Locale;

/**
 * Ngoại lệ ném ra khi tài khoản không có đủ số dư để thực hiện giao dịch.
 */
public class InsufficientFundsException extends BankException {
  public InsufficientFundsException(double amount) {
    super("Tài khoản không đủ số dư để giao dịch: $" + String.format(Locale.US, "%.2f", amount));
  }
}