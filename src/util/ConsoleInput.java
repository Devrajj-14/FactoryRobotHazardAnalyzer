package util;

import java.util.Scanner;

public class ConsoleInput {

    public String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
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