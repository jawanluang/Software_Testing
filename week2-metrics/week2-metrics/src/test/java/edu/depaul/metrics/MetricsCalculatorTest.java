package edu.depaul.metrics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetricsCalculatorTest {

  // the number of minutes in 1 week
  private static final int ONE_WEEK = 10080;

  @Test
  @DisplayName("MTBF should be 3331.67 for given events")
  void mtbf_test1() {
    int[] events = {75, 3, 7};

    MetricsCalculator calculator = new MetricsCalculator();

    double mtbf = calculator.getMTBF(events, ONE_WEEK);
    assertEquals(3331.67, mtbf, 1e-2);
  }

  @Test
  @DisplayName("MTBF should be 2014 for the given events")
  void mtbf_test2() {
    int[] events = {2,2,2,2,2};   // 1 ten minute event

    MetricsCalculator calculator = new MetricsCalculator();

    double mtbf = calculator.getMTBF(events, ONE_WEEK);
    assertEquals(2014.0, mtbf, 1e-2);
  }

  @Test
  @DisplayName("MTTR should be 28.33 for the given events")
  void mttr_test1() {
    int[] events = {75, 3, 7};

    MetricsCalculator calculator = new MetricsCalculator();

    double mttr = calculator.getMTTR(events, ONE_WEEK);
    assertEquals(28.33, mttr, 1e-2);
  }

  @Test
  @DisplayName("MTTR should be 2 for the given events")
  void mttr_test2() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    double mtbf = calculator.getMTTR(events, ONE_WEEK);
    assertEquals(2.0, mtbf, 1e-2);
  }

  @Test
  @DisplayName("Downtimes of 75,3, and 7 should result is availability of 99.15%")
  void availability_test1() {
    int[] events = {75, 3, 7};

    MetricsCalculator calculator = new MetricsCalculator();

    double availability = calculator.getAvailability(events, ONE_WEEK);
    assertEquals(99.15, availability, 1e-2);
  }

  @Test
  @DisplayName("5 Downtimes of 2 should result is availability of 99.9%")
  void availability_test2() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    double availability = calculator.getAvailability(events, ONE_WEEK);
    assertEquals(99.9, availability, 1e-2);
  }

  @Test
  @DisplayName("When sessions per hour is 12 and session length is 15 an downtime is 30, affected users is 9")
  void affected_users_is_9() {

    MetricsCalculator calculator = new MetricsCalculator();

    double users = calculator.getAffectedUsers(12, 15, 30);
    assertEquals(9.0, users, 1e-4);
  }

  @Test
  @DisplayName("When sessions per hour is 60 and session length is 15 an downtime is 60, affected users is 75")
  void affected_users_is_75() {
    MetricsCalculator calculator = new MetricsCalculator();

    double users = calculator.getAffectedUsers(60, 15, 60);
    assertEquals(75.0, users, 1e-4);
  }

  @Test
  @DisplayName("when affected users is 75, reliability is 94.791")
  void getReliability1() {
    MetricsCalculator calculator = new MetricsCalculator();

    double rel = calculator.getReliability(60, 15, 60, 24);
    assertEquals(94.791, rel, 1e-3);
  }

  @Test
  @DisplayName("when affected users is 9, reliability is 94.791")
  void getReliability2() {
    MetricsCalculator calculator = new MetricsCalculator();

    double rel = calculator.getReliability(12, 15, 30, 24);
    assertEquals(96.875, rel, 1e-3);
  }

  @Test
  @DisplayName("MTBF: when an event in events is negative, calculator should throw an exception")
  void negativeMTBF() {
    int[] events = {2,-2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTBF(events, ONE_WEEK));
  }

  @Test
  @DisplayName("MTBF: when events is empty, calculator should throw an exception")
  void emptyMTBF() {
    int[] events = {};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTBF(events, ONE_WEEK));
  }

  @Test
  @DisplayName("MTBF: when scope is equal to 0, calculator should throw an exception")
  void badScopeMTBF1() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTBF(events, 0));
  }

  @Test
  @DisplayName("MTBF: when scope is negative, calculator should throw an exception")
  void badScopeMTBF2() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTBF(events, -10));
  }

  @Test
  @DisplayName("MTBF: when a downtime event is greater than the scope, calculator throws an exception")
  void badDownTimeEventMTBF() {
    int[] events = {100, 100, 100};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTBF(events, 50));
  }

  @Test
  @DisplayName("MTBF: when the total downtime events is greater than the scope, calculator throws an exception")
  void negativeUptimeMTBF() {
    int[] events = {25, 25, 25};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTBF(events, 50));
  }

  @Test
  @DisplayName("MTTR: when an event in events is negative, calculator should throw an exception")
  void negativeMTTR() {
    int[] events = {2,2,2,-2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTTR(events, ONE_WEEK));
  }

  @Test
  @DisplayName("MTBF: when events is empty, calculator should throw an exception")
  void emptyMTTR() {
    int[] events = {};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTTR(events, ONE_WEEK));
  }

  @Test
  @DisplayName("MTTR: when scope is equal to 0, calculator should throw an exception")
  void badScopeMTTR1() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTTR(events, 0));
  }

  @Test
  @DisplayName("MTTR: when scope is negative, calculator should throw an exception")
  void badScopeMTTR2() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTTR(events, -10));
  }

  @Test
  @DisplayName("MTTR: when a downtime event is greater than the scope, calculator throws an exception")
  void badDownTimeEventMTTR() {
    int[] events = {100, 100, 100};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getMTTR(events, 50));
  }

  @Test
  @DisplayName("getAvailability: when an event in events is negative, calculator should throw an exception")
  void negativeAvailability() {
    int[] events = {-2,-2,-2,-2,-2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAvailability(events, ONE_WEEK));
  }

  @Test
  @DisplayName("getAvailability: when events is empty, calculator should throw an exception")
  void emptyAvailability() {
    int[] events = {};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAvailability(events, ONE_WEEK));
  }

  @Test
  @DisplayName("getAvailability: when scope is equal to 0, calculator should throw an exception")
  void badScopeAvailability1() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAvailability(events, 0));
  }

  @Test
  @DisplayName("getAvailability: when scope is negative, calculator should throw an exception")
  void badScopeAvailability2() {
    int[] events = {2,2,2,2,2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAvailability(events, -10));
  }

  @Test
  @DisplayName("getAvailability: when a downtime event is greater than the scope, calculator throws an exception")
  void badDownTimeEventAvailability() {
    int[] events = {100, 2, 2};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAvailability(events, 50));
  }

  @Test
  @DisplayName("getAvailability: when the total downtime events is greater than the scope, calculator throws an exception")
  void negativeUptimeAvailability() {
    int[] events = {25, 25, 25};

    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAvailability(events, 50));
  }

  @Test
  @DisplayName("GetAffected: when the sessions per hour is zero, calculator throws an exception")
  void zeroSessionsPerHourGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(0, 15, 30));
  }

  @Test
  @DisplayName("GetAffected: when the session length is zero, calculator throws an exception")
  void zeroSessionLengthGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(12, 0, 30));
  }

  @Test
  @DisplayName("GetAffected: when the downtime event is zero, calculator throws an exception")
  void zeroDownTimeGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(12, 15, 0));
  }

  @Test
  @DisplayName("GetAffected: when all parameters are zero, calculator throws an exception")
  void zeroGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(0, 0, 0));
  }

  @Test
  @DisplayName("GetAffected: when the session per hour is negative, calculator throws an exception")
  void negativeSessionsPerHourGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(-12, 15, 30));
  }

  @Test
  @DisplayName("GetAffected: when the session length is negative, calculator throws an exception")
  void negativeSessionLengthGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(12, -15, 30));
  }

  @Test
  @DisplayName("GetAffected: when the downtime event is negative, calculator throws an exception")
  void negativeDownTimeGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(12, 15, -30));
  }

  @Test
  @DisplayName("GetAffected: when all parameters are negative, calculator throws an exception")
  void negativeGetAffected() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getAffectedUsers(-12, -15, -30));
  }

  @Test
  @DisplayName("Reliability: when the sessions per hour is zero, calculator throws an exception")
  void zeroSessionsPerHourReliability() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(0, 15, 60, 24));
  }

  @Test
  @DisplayName("Reliability: when the sessions length is zero, calculator throws an exception")
  void zeroSessionLengthReliability() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(60, 0, 60, 24));
  }

  @Test
  @DisplayName("Reliability: when the downtime event is zero, calculator throws an exception")
  void zeroDownTimeReliability() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(60, 15, 0, 24));
  }

  @Test
  @DisplayName("Reliability: when the sessions per hour is negative, calculator throws an exception")
  void negativeSessionsPerHourReliability() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(-60, 15, 60, 24));
  }

  @Test
  @DisplayName("Reliability: when the sessions length is negative, calculator throws an exception")
  void negativeSessionLengthReliability() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(60, -15, 60, 24));
  }

  @Test
  @DisplayName("Reliability: when the downtime event is negative, calculator throws an exception")
  void negativeDownTimeReliability() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(60, 15, -60, 24));
  }

  @Test
  @DisplayName("Reliability: when scope is zero, calculator throws an exception")
  void badScopeReliability1() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(60, 15, 60, 0));
  }

  @Test
  @DisplayName("Reliability: when scope is negative, calculator throws an exception")
  void badScopeReliability2() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(60, 15, 60, -24));
  }

  @Test
  @DisplayName("Reliability: when the downtime event is greater than the scope, calculator throws an exception")
  void badDownTimeEventReliability() {
    MetricsCalculator calculator = new MetricsCalculator();

    assertThrows(IllegalArgumentException.class, () -> calculator.getReliability(60, 15, 120, 1));
  }
}
