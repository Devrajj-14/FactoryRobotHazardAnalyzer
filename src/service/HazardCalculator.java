package service;

import domain.MachineryState;
import domain.RobotScenario;

public class HazardCalculator {

    private static final double W_PRECISION = 0.40;
    private static final double W_DENSITY = 0.35;
    private static final double W_MACHINERY = 0.25;

    public double calculateScore(RobotScenario scenario) {
        double precisionHazard = 100.0 - scenario.getArmPrecisionPercent();

        double densityHazard = (scenario.getWorkerDensityPer100m2() / 200.0) * 100.0;
        densityHazard = clamp(densityHazard, 0.0, 100.0);

        double machineryHazard = machineryHazardValue(scenario.getMachineryState());

        double score = (W_PRECISION * precisionHazard)
                + (W_DENSITY * densityHazard)
                + (W_MACHINERY * machineryHazard);

        return clamp(score, 0.0, 100.0);
    }

    private double machineryHazardValue(MachineryState state) {
        if (state == null) return 50.0;

        switch (state) {
            case NORMAL:
                return 10.0;
            case MAINTENANCE_DUE:
                return 50.0;
            case CRITICAL_FAULT:
                return 90.0;
            default:
                return 50.0;
        }
    }

    private double clamp(double v, double min, double max) {
        if (v < min) return min;
        if (v > max) return max;
        return v;
    }
}