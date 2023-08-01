/* 
 * Assignment #8
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.se433.fileservice;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A wrapper for file services.  Currently uses java.io.File, to
 * perform requests but could use something else without altering
 * the interface.
 */
public class FileService {
  
  public List<String> getDirectoryContents(String path) {
    File folder = new File(path);
    File[] listOfFiles = folder.listFiles();
  
    return Arrays.stream(listOfFiles)
      .map(f -> f.getName())
      .collect(Collectors.toList());
  }
  
  public boolean delete(String path) {
    System.out.println("deleting: " + path);
    File file = new File(path);
    return file.delete();
  }
}
