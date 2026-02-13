package app;

import domain.MachineryState;
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

            System.out.println();
            System.out.printf("Hazard Risk Score (UC5): %.2f / 100%n", score);
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Invalid Number: Enter numeric values for precision and density.");
        } catch (InvalidScenarioException e) {
            System.out.println();
            System.out.println("Invalid Scenario: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}