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
                String precisionRaw = input.readString(sc, "Enter Arm Precision (0-100 %): ");
                String densityRaw = input.readString(sc, "Enter Worker Density (0-200 per 100 m^2): ");
                String stateRaw = input.readString(sc, "Enter Machinery State (NORMAL / MAINTENANCE_DUE / CRITICAL_FAULT): ");

                double precision = Double.parseDouble(precisionRaw.trim());
                double density = Double.parseDouble(densityRaw.trim());
                MachineryState state = MachineryState.fromUserInput(stateRaw);

                RobotScenario scenario = new RobotScenario(precision, density, state);
                validator.validate(scenario);

                double score = calculator.calculateScore(scenario);
                RiskLevel level = RiskLevel.fromScore(score);

                System.out.println();
                System.out.println("Analysis Result (UC7)");
                System.out.printf("Hazard Risk Score: %.2f / 100%n", score);
                System.out.println("Risk Level: " + level);

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid Number: Enter numeric values for precision and density.");
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
}