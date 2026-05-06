package com.bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho tài khoản vãng lai (Checking Account).
 */
public class CheckingAccount extends Account {
  private static final Logger logger = LoggerFactory.getLogger(CheckingAccount.class);

  public CheckingAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    logger.info("Bắt đầu giao dịch nạp tiền vào tài khoản vãng lai...");
    double initialBalance = getBalance();
    
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      
      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      
      logger.info("Nạp tiền vào tài khoản {} thành công: +{}", getAccountNumber(), amount);
    } catch (InvalidFundingAmountException e) {
      logger.error("Lỗi nạp tiền do số tiền không hợp lệ: {}", e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    double initialBalance = getBalance();
    
    try {
      // Tài khoản vãng lai thường không bị giới hạn số tiền rút tối đa như tiết kiệm
      doWithdrawing(amount);
      double finalBalance = getBalance();
      
      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      
      logger.info("Tài khoản vãng lai {} rút {} thành công. Số dư còn: {}", 
          getAccountNumber(), amount, finalBalance);
    } catch (BankException e) {
      logger.error("Lỗi khi rút tiền: {}", e.getMessage());
    }
  }
}