package com.bank_system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
  
  @Test
  public void testCheckingAccountCreation() {
    CheckingAccount acc = new CheckingAccount(123456789L, 1000.0);
    assertEquals(123456789L, acc.getAccountNumber());
    assertEquals(2000.0, acc.getBalance());
  }
}