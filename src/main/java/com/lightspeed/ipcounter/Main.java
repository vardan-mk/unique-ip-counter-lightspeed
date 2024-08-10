package com.lightspeed.ipcounter;

public class Main {
  public static void main(String[] args) {

    final String filePath = "src/main/resources/ipv4_addresses.txt";

    System.out.println("#######################################");

    final long startTime = System.nanoTime();
    final long count = UniqueIpCounter.countUniques(filePath);
    final long endTime = System.nanoTime() - startTime;

    System.out.println("Duration of whole process of reading file and count unique is : " + endTime / 1_000_000_000. + "seconds\n");
    System.out.println("Total count of unique IP address in file is: " + count);

    System.out.println("#######################################");
  }
}