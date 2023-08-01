/* 
 * Assignment #8
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.se433.fileservice;

import java.util.ArrayList;
import java.util.List;

/**
 * The job of the file monitor is to monitor a given directory
 * and when it finds temp files (*.tmp) in that directory, it deletes
 * them.  It performs this work with the aid of a FileService
 */
public class FileMonitor {
  
  private FileService fileService;
  
  public FileMonitor(FileService s) {
    fileService = s;
  }

  /**
   * Using the FileService, remove the tmp files from the given path
   * @param path
   * @return the names of the files that were removed
   */
  public List<String> clean(String path) {
    List<String> files = fileService.getDirectoryContents(path);
    List<String> removedFiles = new ArrayList<>();
    
    for (String name : files) {
      if (name.endsWith(".tmp")) {
        fileService.delete(name);
        removedFiles.add(name);
      }
    }

    return removedFiles;
  }
}
