package test.java.com.bank_system;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePathTest {

  @Test
  public void testLogFilePath() {
    // Java tự động tạo đường dẫn tùy thuộc vào hệ điều hành đang chạy
    File logFile = new File("logs", "transaction.log");
    
    // Cố tình so sánh với đường dẫn "cứng" kiểu Windows (\)
    // Sẽ báo lỗi: expected: <logs\transaction.log> but was: <logs/transaction.log> trên Linux/Mac
    assertEquals("logs\\transaction.log", logFile.getPath()); 
  }
}