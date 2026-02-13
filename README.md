# Factory Robot Hazard Analyzer (Console-Based Java)

## Project Overview
**Factory Robot Hazard Analyzer** is a console-based Java application that evaluates the hazard risk of a factory robot using three inputs:

- **Arm Precision:** `0–100%`
- **Worker Density:** `0–200 per 100 m²`
- **Machinery State:** `NORMAL` / `MAINTENANCE_DUE` / `CRITICAL_FAULT`

The system validates all inputs, calculates a **hazard risk score (0–100)**, classifies the **risk level**, and provides a **recommendation**. Invalid or unsafe scenarios are handled using a **custom exception**.

---

## Packages Used
- **app** — main console runner and output display
- **domain** — domain models and enums
- **service** — validation and hazard calculation logic
- **exception** — custom exception for invalid scenarios
- **util** — reusable console input helper functions

---

## Use Case Summary (UC1–UC8)

### UC1 — Project Skeleton and Entry Banner
- Created project folder structure (`src/` + packages)
- Added `FactoryRobotHazardAnalyzerApp` as the entry point
- Printed a clean startup banner in the console
- Added `.gitignore` for Java + IDE clutter

### UC2 — Console Input Utility and Raw Input Capture
- Built `ConsoleInput` utility for consistent prompt-based input
- Read raw inputs from the user and echoed them back for verification

### UC3 — Domain Model and Machinery State Parsing
- Added domain model:
    - `RobotScenario` to store precision, density, and machinery state
- Added `MachineryState` enum with `fromUserInput()` parser to support clean conversion from console input

### UC4 — Validation with Custom Exception
- Added `InvalidScenarioException` for invalid/unsafe scenario inputs
- Added `ScenarioValidator` service to validate:
    - precision range (`0–100`)
    - density range (`0–200`)
    - valid machinery state (must map correctly)
    - numeric sanity checks (NaN/Infinity)
- Updated main flow to safely catch validation and numeric parsing issues

### UC5 — Hazard Risk Score Calculation
- Implemented `HazardCalculator` service to compute a hazard risk score (`0–100`) using:
    - precision hazard contribution (lower precision → higher hazard)
    - density hazard contribution (higher density → higher hazard)
    - machinery state hazard mapping (`NORMAL` / `MAINTENANCE_DUE` / `CRITICAL_FAULT`)
- Score is **weighted and clamped** to always stay within `0–100`

### UC6 — Risk Level Classification
- Added `RiskLevel` enum:
    - `LOW` / `MODERATE` / `HIGH` / `CRITICAL`
- Classified the hazard score into a risk level using thresholds
- Displayed score and risk level in the console

### UC7 — Multiple Scenario Analysis (Loop)
- Added a user-controlled repeat loop
- Program can analyze multiple scenarios in one run without restarting
- Added `readYesNo()` to reliably handle Y/N inputs

### UC8 — Hardened Numeric Input and Polished Output
- Enhanced `ConsoleInput` with `readDouble()` to prevent crashes on invalid numeric input
- Improved output formatting for readability
- Added risk recommendations based on risk level (action guidance)
- Final console flow is stable, repeatable, and user-friendly

---

## Key Features
- Input validation with custom exception handling
- Domain-driven structure with clean separation of concerns
- Hazard risk score calculation with consistent classification
- Repeatable analysis loop for multiple scenarios
- Robust input parsing that prevents crashes
- Clear console output and safety recommendations

---

## Development Notes
- GitFlow followed UC-wise with feature branches
- Feature branches were preserved after completion using `-k`
- Commits were kept meaningful and aligned with each UC milestone