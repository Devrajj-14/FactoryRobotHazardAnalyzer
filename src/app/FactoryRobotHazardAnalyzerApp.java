package app;

import domain.MachineryState;
import domain.RiskLevel;
import domain.RobotScenario;
import exception.InvalidScenarioException;
import service.HazardCalculator;
import service.ScenarioValidator;
import util.ConsoleInput;

import java.util.Scanner;

public class FactoryRobotHazardAnalyzerApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput();

        ScenarioValidator validator = new ScenarioValidator();
        HazardCalculator calculator = new HazardCalculator();

        System.out.println("========================================");
        System.out.println("        FACTORY ROBOT HAZARD ANALYZER   ");
        System.out.println("========================================");

        boolean run = true;

        while (run) {
            try {
                double precision = input.readDouble(sc, "Enter Arm Precision (0-100 %): ");
                double density = input.readDouble(sc, "Enter Worker Density (0-200 per 100 m^2): ");
                String stateRaw = input.readString(sc, "Enter Machinery State (NORMAL / MAINTENANCE_DUE / CRITICAL_FAULT): ");

                MachineryState state = MachineryState.fromUserInput(stateRaw);
                RobotScenario scenario = new RobotScenario(precision, density, state);

                validator.validate(scenario);

                double score = calculator.calculateScore(scenario);
                RiskLevel level = RiskLevel.fromScore(score);

                System.out.println();
                System.out.println("Analysis Result");
                System.out.println("----------------------------------------");
                System.out.printf("Hazard Risk Score: %.2f / 100%n", score);
                System.out.println("Risk Level: " + level);
                System.out.println("Recommendation: " + recommendation(level));
                System.out.println("----------------------------------------");

            } catch (InvalidScenarioException e) {
                System.out.println();
                System.out.println("Invalid Scenario: " + e.getMessage());
            }

            System.out.println();
            run = input.readYesNo(sc, "Analyze another scenario? (Y/N): ");
            System.out.println();
        }

        System.out.println("Exiting Factory Robot Hazard Analyzer.");
        sc.close();
    }

    private static String recommendation(RiskLevel level) {
        if (level == null) return "Monitor system.";

        switch (level) {
            case LOW:
                return "Continue monitoring and follow standard safety checks.";
            case MODERATE:
                return "Increase supervision and run additional safety verification.";
            case HIGH:
                return "Restrict operation and schedule inspection immediately.";
            case CRITICAL:
                return "Stop operation now and perform emergency maintenance.";
            default:
                return "Monitor system.";
        }
    }
}