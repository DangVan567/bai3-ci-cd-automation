package com.bank_system;

import java.util.Locale;

/**
 * Ngoại lệ ném ra khi số tiền giao dịch âm hoặc bằng không.
 */
public class InvalidFundingAmountException extends BankException {
  public InvalidFundingAmountException(double amount) {
    super("Số tiền không hợp lệ: $" + String.format(Locale.US, "%.2f", amount));
  }
}