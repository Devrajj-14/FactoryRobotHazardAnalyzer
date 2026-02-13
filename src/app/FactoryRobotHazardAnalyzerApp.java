package app;

import util.ConsoleInput;
import java.util.Scanner;

public class FactoryRobotHazardAnalyzerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput();

        System.out.println("========================================");
        System.out.println("        FACTORY ROBOT HAZARD ANALYZER   ");
        System.out.println("========================================");

        String precisionRaw = input.readString(sc, "Enter Arm Precision (0-100 %): ");
        String densityRaw = input.readString(sc, "Enter Worker Density (0-200 per 100 m^2): ");
        String stateRaw = input.readString(sc, "Enter Machinery State (NORMAL / MAINTENANCE_DUE / CRITICAL_FAULT): ");

        System.out.println();
        System.out.println("Captured Inputs (UC2):");
        System.out.println("Arm Precision: " + precisionRaw);
        System.out.println("Worker Density: " + densityRaw);
        System.out.println("Machinery State: " + stateRaw);

        sc.close();
    }
}