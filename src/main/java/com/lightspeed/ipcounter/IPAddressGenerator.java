package com.lightspeed.ipcounter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class IPAddressGenerator {

  private static final short BYTE_SIZE = 256;
  private static final int TOTAL_IPS = 1_000_000;
  private static final double REPEAT_RATE = 0.3; // 30% repetition
  private static final String OUTPUT_FILE = "src/main/resources/ipv4_addresses.txt";

  public static void main(String[] args) {

    final Random random = new Random();
    final int repeatCount = (int) (REPEAT_RATE * TOTAL_IPS);
    final Set<String> uniqueIPs = new HashSet<>();

    while (uniqueIPs.size() < (1 - REPEAT_RATE) * TOTAL_IPS) {
      uniqueIPs.add(generateRandomIPAddress(random));
    }

    final List<String> allIPs = new ArrayList<>(uniqueIPs);
    final List<String> uniqueIPList = new ArrayList<>(uniqueIPs);

    for (int i = 0; i < repeatCount; i++) {
      final String ip = uniqueIPList.get(random.nextInt(uniqueIPList.size()));
      allIPs.add(ip);
    }

    // Create randomized order
    Collections.shuffle(allIPs);

    writeIPsToFile(allIPs, OUTPUT_FILE);
  }

  private static String generateRandomIPAddress(final Random random) {
    return String.format("%d.%d.%d.%d",
        random.nextInt(BYTE_SIZE),
        random.nextInt(BYTE_SIZE),
        random.nextInt(BYTE_SIZE),
        random.nextInt(BYTE_SIZE)
    );

  }

  private static void writeIPsToFile(final List<String> ipAddresses, final String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      for (final String ip : ipAddresses) {
        writer.write(ip);
        writer.newLine();
      }
      System.out.println("File written to " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
