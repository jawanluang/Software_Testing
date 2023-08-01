/* 
 * Assignment #8
 * Topic: Mocks
 * Author: Dan Walker
 */
package edu.depaul.se433.fileservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Demonstrate how the FileMonitor works
 */
public class FileApp {

  public static void main(String[] args) throws FileNotFoundException {
      try (PrintWriter writer = new PrintWriter(new File("./junk0510b.tmp"))) {
        writer.write("Testing...");
        writer.flush(); 
      }

    FileService fs = new FileService();
    FileMonitor monitor = new FileMonitor(fs);
    List<String> results = monitor.clean(".");

    // Should report the 1 file we created above
    System.out.println("Count of removed files (first time): " + results.size());

    // Calling it a 2nd time should result in zero removals
    results = monitor.clean(".");
    System.out.println("Count of removed files (second time): " + results.size());
  }

}
