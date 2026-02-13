package util;

import java.util.Scanner;

public class ConsoleInput {

    public String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = sc.nextLine();
            try {
                return Double.parseDouble(raw.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Enter a valid numeric value.");
            }
        }
    }

    public boolean readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim().toUpperCase();
            if (v.equals("Y") || v.equals("YES")) return true;
            if (v.equals("N") || v.equals("NO")) return false;
            System.out.println("Invalid input. Enter Y or N.");
        }
    }
}