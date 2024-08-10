package com.lightspeed.ipcounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UniqueIpCounter {

  private static final short BYTE_SIZE = 256;

  public static long countUniques(final String filePath) {

    boolean[][][][] table = new boolean[BYTE_SIZE][BYTE_SIZE][BYTE_SIZE][BYTE_SIZE];

    final long startTime = System.nanoTime();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = reader.readLine()) != null) {
        final String[] octets = line.trim().split("\\.");
        final short octet1 = Short.parseShort(octets[0]);
        final short octet2 = Short.parseShort(octets[1]);
        final short octet3 = Short.parseShort(octets[2]);
        final short octet4 = Short.parseShort(octets[3]);

        table[octet1][octet2][octet3][octet4] = true;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    final long endTime = System.nanoTime() - startTime;

    System.out.println(
        "Duration of reading from file all records is " + endTime / 1_000_000_000. + "seconds\n");

    return traverse(table);
  }

  private static long traverse(final boolean [][][][] table) {
    long count = 0L;

    for (short o1 = 0; o1 < BYTE_SIZE; o1++) {
      for (short o2 = 0; o2 < BYTE_SIZE; o2++) {
        for (short o3 = 0; o3 < BYTE_SIZE; o3++) {
          for (short o4 = 0; o4 < BYTE_SIZE; o4++) {
            if (table[o1][o2][o3][o4]) {
              count++;
            }
          }
        }
      }
    }

    return count;
  }
}

