package service;

import domain.MachineryState;
import domain.RobotScenario;
import exception.InvalidScenarioException;

public class ScenarioValidator {

    public void validate(RobotScenario scenario) throws InvalidScenarioException {
        if (scenario == null) {
            throw new InvalidScenarioException("Scenario cannot be null.");
        }

        double precision = scenario.getArmPrecisionPercent();
        double density = scenario.getWorkerDensityPer100m2();
        MachineryState state = scenario.getMachineryState();

        if (Double.isNaN(precision) || Double.isInfinite(precision)) {
            throw new InvalidScenarioException("Arm precision must be a valid number.");
        }
        if (precision < 0.0 || precision > 100.0) {
            throw new InvalidScenarioException("Arm precision must be between 0 and 100 percent.");
        }

        if (Double.isNaN(density) || Double.isInfinite(density)) {
            throw new InvalidScenarioException("Worker density must be a valid number.");
        }
        if (density < 0.0 || density > 200.0) {
            throw new InvalidScenarioException("Worker density must be between 0 and 200 per 100 m^2.");
        }

        if (state == null) {
            throw new InvalidScenarioException("Machinery state must be NORMAL / MAINTENANCE_DUE / CRITICAL_FAULT.");
        }
    }
}