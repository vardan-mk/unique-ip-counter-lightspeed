package com.lightspeed.ipcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UniqueIpCounterTest {

  @Test
  public void testCount() {
    final String file = "src/test/resources/ipv4_addresses.txt";
    final long expectedCount = 700L;
    final long actualCount = UniqueIpCounter.countUniques(file);
    assertEquals(expectedCount, actualCount);
  }

}
