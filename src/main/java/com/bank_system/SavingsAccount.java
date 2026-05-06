package com.bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản tiết kiệm thực thi các quy định về nạp và rút tiền.
 */
public class SavingsAccount extends Account {
  private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);
  private static final double MAX_WITHDRAW = 1000.0;
  private static final double MIN_BALANCE = 5000.0;

  public SavingsAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    logger.info("Bắt đầu giao dịch nạp tiền vào tài khoản tiết kiệm...");
    double initialBalance = getBalance();
    
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      
      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_SAVINGS, amount, initialBalance, finalBalance);
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
      if (amount > MAX_WITHDRAW) {
        throw new InvalidFundingAmountException(amount);
      }
      if (initialBalance - amount < MIN_BALANCE) {
        throw new InsufficientFundsException(amount);
      }
      
      doWithdrawing(amount);
      double finalBalance = getBalance();
      
      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_SAVINGS, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      
      logger.info("Tài khoản tiết kiệm {} rút {} thành công. Số dư còn: {}", 
          getAccountNumber(), amount, finalBalance);
    } catch (BankException e) {
      logger.error("Lỗi khi rút tiền: {}", e.getMessage());
    }
  }
}