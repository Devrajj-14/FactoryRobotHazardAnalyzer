package util;

import java.util.Scanner;

public class ConsoleInput {

    public String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}