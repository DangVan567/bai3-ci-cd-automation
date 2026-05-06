package com.bank_system;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp đại diện cho khách hàng của ngân hàng.
 */
public class Customer {
  private long idNumber;
  private String fullName;
  private List<Account> accountList;

  /**
   * Tạo một khách hàng mới.
   *
   * @param idNumber số CMND/CCCD của khách hàng
   * @param fullName họ và tên khách hàng
   */
  public Customer(long idNumber, String fullName) {
    this.idNumber = idNumber;
    this.fullName = fullName;
    this.accountList = new ArrayList<>();
  }

  public long getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(long idNumber) {
    this.idNumber = idNumber;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<Account> getAccountList() {
    return accountList;
  }

  /**
   * Thêm một tài khoản cho khách hàng.
   *
   * @param account tài khoản cần thêm
   */
  public void addAccount(Account account) {
    if (account != null) {
      accountList.add(account);
    }
  }

  /**
   * Xóa một tài khoản của khách hàng.
   *
   * @param account tài khoản cần xóa
   */
  public void removeAccount(Account account) {
    if (account != null) {
      accountList.remove(account);
    }
  }

  /**
   * Lấy thông tin cơ bản của khách hàng.
   *
   * @return chuỗi chứa thông tin khách hàng
   */
  public String getCustomerInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append("Số CMND: ").append(idNumber)
      .append(". Họ tên: ").append(fullName).append(".");
    return sb.toString();
  }
}