/*
 * Assignment #8
 * Topic: Mocks
 * Author: <YOUR NAME>
 */

package edu.depaul.se433.fileservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileMonitorTest {


  @Test
  @DisplayName("When there are .tmp files, then FileMonitor returns expected sub list from a call to clean")
  void testCleanTmpFiles() {
    List<String> listFiles = Arrays.asList("./junkFile.tmp", "./goodFile.txt");

    FileService mockFS = mock(FileService.class);
    when(mockFS.getDirectoryContents(any())).thenReturn(listFiles);

    FileMonitor fm = new FileMonitor(mockFS);

    List<String> test = Arrays.asList("./junkFile.tmp");
    assertEquals(test, fm.clean("."));
  }

  @Test
  @DisplayName("When there are no .tmp files, then FileMonitor makes no delete() calls")
  void testCleanNoTmpFiles() {
    List<String> listFiles = Arrays.asList("./goodFile.txt");

    FileService mockFS = mock(FileService.class);
    when(mockFS.getDirectoryContents(any())).thenReturn(listFiles);

    FileMonitor fm = new FileMonitor(mockFS);
    fm.clean(".");

    verify(mockFS, times(0)).delete(any());
  }

  @Test
  @DisplayName("When there is an empty list, then FileMonitor doesn't throw an exception")
  void testCleanEmpty() {
    List<String> listFiles = new ArrayList<>();

    FileService mockFS = mock(FileService.class);
    when(mockFS.getDirectoryContents(any())).thenReturn(listFiles);

    FileMonitor fm = new FileMonitor(mockFS);

    List<String> test = new ArrayList<>();
    assertEquals(test, fm.clean("."));
  }
}
