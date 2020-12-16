package Day16;

import java.io.File;

import aocutil.Utilities;

public class D16P1 {
  public static void main(String[] args) {
    Ticket ticket = new Ticket(Utilities.parseInputFile(new File(args[0])));

    System.out.printf("The error rate is %d%n", ticket.errorRate());
  }
}
