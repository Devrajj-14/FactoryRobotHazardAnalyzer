package app;

import domain.MachineryState;
import domain.RobotScenario;
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

        double precision = Double.parseDouble(precisionRaw.trim());
        double density = Double.parseDouble(densityRaw.trim());
        MachineryState state = MachineryState.fromUserInput(stateRaw);

        RobotScenario scenario = new RobotScenario(precision, density, state);

        System.out.println();
        System.out.println("Scenario Built (UC3):");
        System.out.println("Precision: " + scenario.getArmPrecisionPercent());
        System.out.println("Density: " + scenario.getWorkerDensityPer100m2());
        System.out.println("State: " + scenario.getMachineryState());

        sc.close();
    }
}