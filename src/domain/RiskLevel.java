package domain;

public enum RiskLevel {
    LOW,
    MODERATE,
    HIGH,
    CRITICAL;

    public static RiskLevel fromScore(double score) {
        if (score < 25.0) return LOW;
        if (score < 50.0) return MODERATE;
        if (score < 75.0) return HIGH;
        return CRITICAL;
    }
}