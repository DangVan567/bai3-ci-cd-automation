package com.bank_system;

/**
 * Ngoại lệ cơ sở cho các lỗi liên quan đến nghiệp vụ ngân hàng.
 */
public class BankException extends Exception {
  public BankException(String message) {
    super(message);
  }
}