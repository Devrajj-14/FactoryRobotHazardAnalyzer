package domain;

public class RobotScenario {
    private final double armPrecisionPercent;
    private final double workerDensityPer100m2;
    private final MachineryState machineryState;

    public RobotScenario(double armPrecisionPercent, double workerDensityPer100m2, MachineryState machineryState) {
        this.armPrecisionPercent = armPrecisionPercent;
        this.workerDensityPer100m2 = workerDensityPer100m2;
        this.machineryState = machineryState;
    }

    public double getArmPrecisionPercent() {
        return armPrecisionPercent;
    }

    public double getWorkerDensityPer100m2() {
        return workerDensityPer100m2;
    }

    public MachineryState getMachineryState() {
        return machineryState;
    }
}