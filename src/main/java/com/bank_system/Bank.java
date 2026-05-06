package com.bank_system;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho ngân hàng, quản lý danh sách khách hàng.
 */
public class Bank {
  private static final Logger logger = LoggerFactory.getLogger(Bank.class);
  private static final String ID_PATTERN = "\\d{9}";

  private List<Customer> customerList;

  public Bank() {
    this.customerList = new ArrayList<>();
  }

  public List<Customer> getCustomerList() {
    return customerList;
  }

  /**
   * Thiết lập danh sách khách hàng.
   *
   * @param customerList danh sách khách hàng mới
   */
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customerList = new ArrayList<>();
    } else {
      this.customerList = customerList;
    }
  }

  /**
   * Đọc danh sách khách hàng từ luồng đầu vào.
   *
   * @param inputStream luồng dữ liệu đầu vào
   */
  public void readCustomerList(InputStream inputStream) {
    logger.debug("Bắt đầu đọc dữ liệu khách hàng...");
    if (inputStream == null) {
      return;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      Customer current = null;
      
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }
        
        int lastSpaceIndex = line.lastIndexOf(' ');
        if (lastSpaceIndex <= 0) {
          continue;
        }

        String token = line.substring(lastSpaceIndex + 1).trim();
        if (token.matches(ID_PATTERN)) {
          String name = line.substring(0, lastSpaceIndex).trim();
          current = new Customer(Long.parseLong(token), name);
          customerList.add(current);
          logger.info("Đã thêm khách hàng: {}", name);
        } else if (current != null) {
          processAccountLine(line, current);
        }
      }
    } catch (Exception e) {
      logger.error("Lỗi khi đọc dữ liệu khách hàng: {}", e.getMessage(), e);
    }
  }

  private void processAccountLine(String line, Customer current) {
    String[] parts = line.split("\\s+");
    if (parts.length < 3) {
      return;
    }
    
    try {
      long accountNumber = Long.parseLong(parts[0]);
      double balance = Double.parseDouble(parts[2]);
      
      if (Account.CHECKING_TYPE.equals(parts[1])) {
        current.addAccount(new CheckingAccount(accountNumber, balance));
      } else if (Account.SAVINGS_TYPE.equals(parts[1])) {
        current.addAccount(new SavingsAccount(accountNumber, balance));
      }
    } catch (NumberFormatException e) {
      logger.warn("Định dạng số không hợp lệ trên dòng: {}", line);
    }
  }

  /**
   * Lấy thông tin khách hàng sắp xếp theo ID.
   *
   * @return chuỗi thông tin khách hàng
   */
  public String getCustomersInfoByIdOrder() {
    List<Customer> copy = new ArrayList<>(customerList);
    copy.sort(Comparator.comparingLong(Customer::getIdNumber));

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < copy.size(); i++) {
      sb.append(copy.get(i).getCustomerInfo());
      if (i < copy.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Lấy thông tin khách hàng sắp xếp theo tên.
   *
   * @return chuỗi thông tin khách hàng
   */
  public String getCustomersInfoByNameOrder() {
    List<Customer> copy = new ArrayList<>(customerList);
    copy.sort(Comparator.comparing(Customer::getFullName)
        .thenComparingLong(Customer::getIdNumber));

    StringBuilder sb = new StringBuilder();
    for (Customer c : copy) {
      sb.append(c.getCustomerInfo()).append("\n");
    }
    return sb.toString().trim();
  }
}