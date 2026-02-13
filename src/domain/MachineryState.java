package domain;

public enum MachineryState {
    NORMAL,
    MAINTENANCE_DUE,
    CRITICAL_FAULT;

    public static MachineryState fromUserInput(String raw) {
        if (raw == null) return null;

        String v = raw.trim().toUpperCase();

        if (v.equals("NORMAL")) return NORMAL;

        if (v.equals("MAINTENANCE_DUE") || v.equals("MAINTENANCE") || v.equals("DUE")) {
            return MAINTENANCE_DUE;
        }

        if (v.equals("CRITICAL_FAULT") || v.equals("CRITICAL") || v.equals("FAULT")) {
            return CRITICAL_FAULT;
        }

        return null;
    }
}