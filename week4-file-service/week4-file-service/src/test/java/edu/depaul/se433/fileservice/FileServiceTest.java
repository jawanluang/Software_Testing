/* 
 * Assignment #8
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.se433.fileservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileServiceTest {

  @Test
  @DisplayName("Should return a list of files in current directory")
  void testBasics() {
    FileService service = new FileService();

    // should at least have the folder this code is in!
    List<String> list = service.getDirectoryContents(".");
    assertTrue(list.size() > 0);
  }
  
  @Test
  @DisplayName("no exception from deleting non-existant file")
  void testDeleteNonExistantFile() {
    FileService service = new FileService();
    service.delete("/no/such/file");
  }
}
