package edu.depaul.ticket;

import static org.junit.jupiter.api.Assertions.*;

import edu.depaul.ticket.Instruction.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TicketCalculatorTest {

  private TicketCalculator calculator;

  @BeforeEach
  void setup() {

    // This loaded a default set of drivers.  See the PDF
    // for details
    calculator = new TicketCalculator();
  }

  @Test
  @DisplayName("Verify the system is running")
  void verifyInstructionsAreProduced() {
    Instruction instr = calculator.computeCost("DL12345", 25, 33, 2);
    assertEquals(Instruction.class, instr.getClass());
  }

  @Test
  @DisplayName("Minimum boundary test for speed range 10mph over for no ticket")
  void testMin10mphNoTicket() {
    Instruction instr = calculator.computeCost("DL12345", 25, 26, 2);
    assertEquals(0.0, instr.fine());
  }

  @Test
  @DisplayName("Minimum boundary test for speed range 10mph over for ticket")
  void testMin10mphTicket() {
    Instruction instr = calculator.computeCost("DL12121", 25, 26, 2);
    assertEquals(25.0, instr.fine());
  }

  @Test
  @DisplayName("Maximum boundary test for speed range 10mph over for no ticket")
  void testMax10mphNoTicket() {
    Instruction instr = calculator.computeCost("DL12345", 25, 35, 2);
    assertEquals(0.0, instr.fine());
  }

  @Test
  @DisplayName("Maximum boundary test for speed range 10mph over for ticket")
  void testMax10mphTicket() {
    Instruction instr = calculator.computeCost("DL12121", 25, 35, 2);
    assertEquals(25.0, instr.fine());
  }

  @Test
  @DisplayName("Minimum boundary test for speed range 10mph over for no ticket")
  void testMin10mphNoTicket4Axles() {
    Instruction instr = calculator.computeCost("DL12345", 25, 26, 4);
    assertEquals(0.0, instr.fine());
  }

  @Test
  @DisplayName("Minimum boundary test for speed range 10mph over for ticket")
  void testMin10mphTicket4Axles() {
    Instruction instr = calculator.computeCost("DL12121", 25, 26, 4);
    assertEquals(25.0, instr.fine());
  }

  @Test
  @DisplayName("Maximum boundary test for speed range 10mph over for no ticket")
  void testMax10mphNoTicket4Axles() {
    Instruction instr = calculator.computeCost("DL12345", 25, 35, 4);
    assertEquals(0.0, instr.fine());
  }

  @Test
  @DisplayName("Maximum boundary test for speed range 10mph over for ticket")
  void testMax10mphTicket4Axles() {
    Instruction instr = calculator.computeCost("DL12121", 25, 35, 4);
    assertEquals(25.0, instr.fine());
  }

  @ParameterizedTest(name = "case {index}: ")
  @CsvFileSource(resources = "/weak-normal.csv", numLinesToSkip = 0)
  @DisplayName("The weak normal test for speeding tickets")
  void testWeakNormal(int legalSpeed, int actualSpeed, String driverId, int numAxles, double expected) {
    Instruction instr = calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles);
    if (expected >= 0)
      assertEquals(expected, instr.fine());
    else
      assertEquals(Type.ARREST, instr.type());
  }

  @ParameterizedTest(name = "case {index}: ")
  @CsvFileSource(resources = "/weak-robust.csv", numLinesToSkip = 0)
  @DisplayName("The weak normal test for speeding tickets")
  void testWeakRobust(int legalSpeed, int actualSpeed, String driverId, int numAxles, double expected) {
    Instruction instr;
    if (expected >= 0) {
      instr = calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles);
      assertEquals(expected, instr.fine());
    }
    else if (expected == -1) {
      instr = calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles);
      assertEquals(Type.ARREST, instr.type());
    } else {
      assertThrows(IllegalArgumentException.class,
          () -> calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles));
    }
  }

  @ParameterizedTest(name = "case {index}: ")
  @CsvFileSource(resources = "/strong-normal.csv", numLinesToSkip = 0)
  @DisplayName("The weak normal test for speeding tickets")
  void testStrongNormal(int legalSpeed, int actualSpeed, String driverId, int numAxles, double expected) {
    Instruction instr = calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles);
    if (expected >= 0)
      assertEquals(expected, instr.fine());
    else
      assertEquals(Type.ARREST, instr.type());
  }

  @ParameterizedTest(name = "case {index}: ")
  @CsvFileSource(resources = "/strong-robust.csv", numLinesToSkip = 0)
  @DisplayName("The weak normal test for speeding tickets")
  void testStrongRobust(int legalSpeed, int actualSpeed, String driverId, int numAxles, double expected) {
    Instruction instr;
    if (expected >= 0) {
      instr = calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles);
      assertEquals(expected, instr.fine());
    }
    else if (expected == -1) {
      instr = calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles);
      assertEquals(Type.ARREST, instr.type());
    } else {
      assertThrows(IllegalArgumentException.class,
          () -> calculator.computeCost(driverId, legalSpeed, actualSpeed, numAxles));
    }
  }
}
