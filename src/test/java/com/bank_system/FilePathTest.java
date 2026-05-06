package com.bank_system;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePathTest {

  @Test
  public void testLogFilePath() {
    // Cách chuẩn hóa: Sử dụng File.separator (Nó sẽ tự thành "\" ở Win và "/" ở Linux/Mac)
    String expectedPath = "logs" + File.separator + "transaction.log";
    
    // Test 1: File API cũ
    File logFile = new File("logs", "transaction.log");
    assertEquals(expectedPath, logFile.getPath());

    // Test 2: Sử dụng java.nio.file.Path (API hiện đại, khuyên dùng)
    Path nioPath = Paths.get("logs", "transaction.log");
    assertEquals(expectedPath, nioPath.toString());
  }
}