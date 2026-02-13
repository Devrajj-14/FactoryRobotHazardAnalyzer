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

_________________________________________________________________________________________________________


What the project is doing (end-to-end)
1) Accepts 3 inputs from the user
You enter:
Arm Precision (%)
Range: 0 to 100
Meaning: how accurate the robot arm is.
Lower precision ⇒ more chance of wrong movement ⇒ higher hazard.
Worker Density (per 100 m²)
Range: 0 to 200
Meaning: how many workers are in the area.
Higher density ⇒ more people exposed ⇒ higher hazard.
Machinery State
Allowed: NORMAL, MAINTENANCE_DUE, CRITICAL_FAULT
Meaning: overall mechanical health.
Worse state ⇒ higher hazard.
2) Validates the inputs (very important)
Before calculating anything, it checks:
precision must be a valid number and 0 ≤ precision ≤ 100
density must be a valid number and 0 ≤ density ≤ 200
machinery state must be one of the allowed states (case-insensitive parsing)
If anything is wrong, it throws a custom exception:
InvalidScenarioException
That exception is caught in main() and the program prints a clean message instead of crashing.
So this project is doing defensive programming: no score is calculated for invalid input.
3) Builds a domain object for clean structure
Instead of passing raw values everywhere, it creates:
RobotScenario(precision, density, state)
This is standard OOP:
data is packaged into a single meaningful object
validation/calculation services accept that object
4) Converts inputs into a hazard score (0–100)
After validation, it calculates a single hazard score so you can compare scenarios easily.
It turns each input into a hazard component:
A) Precision hazard
Precision is “good”, but we want hazard:
precisionHazard = 100 - precision
So:
precision=100 → hazard=0 (best)
precision=0 → hazard=100 (worst)
B) Density hazard (normalize to 0–100)
Density is 0 to 200, so it scales it:
densityHazard = (density / 200) * 100
So:
density=0 → hazard=0
density=200 → hazard=100
C) Machinery hazard (categorical → numeric mapping)
It maps the state to a hazard value:
NORMAL → 10
MAINTENANCE_DUE → 50
CRITICAL_FAULT → 90
This turns words into numbers so they can be combined with the other factors.
5) Weighted combination (the final score)
It combines those hazards using weights:
precision weight = 0.40
density weight = 0.35
machinery weight = 0.25
So:
score = 0.40*precisionHazard + 0.35*densityHazard + 0.25*machineryHazard
Then clamps score into 0–100.
This gives you one final number like 63.50 / 100.
6) Converts score into a risk category
It classifies the score into RiskLevel:
score < 25 → LOW
< 50 → MODERATE
< 75 → HIGH
else → CRITICAL
This is the “human readable label”.
7) Gives a recommendation
Based on risk level, it prints action guidance:
LOW → continue monitoring
MODERATE → increase supervision
HIGH → restrict operation & inspect
CRITICAL → stop operation & emergency maintenance
So the project is not only “calculating”, but also “decision-supporting”.
8) Runs in a loop for multiple analyses
It asks:
“Analyze another scenario? (Y/N)”
So you can test many scenarios in one run.
What algorithm are we using?
This project is not using sorting/searching algorithms like merge sort or BFS.
It mainly uses a scoring algorithm:
1) Weighted Risk Scoring (Weighted Sum Model)
This is the core algorithm.
Steps of the algorithm:
Normalize different inputs to a common 0–100 hazard scale
precision: inverted to hazard
density: scaled to percent
state: mapped to a numeric hazard
Aggregate hazards using weights
final score = weighted sum
This is a standard approach used in:
risk management scoring
safety scoring
credit scoring models (similar idea)
decision-support systems
Name: Weighted Sum Model (WSM) / Linear weighted scoring
2) Rule-based classification
After score is computed, it uses threshold rules:
If score < 25 ⇒ LOW, etc.
That is a simple:
if-else threshold classifier
(a basic rule-based system)
What programming concepts you are practicing here
OOP structure
domain classes (RobotScenario)
enums (MachineryState, RiskLevel)
services (ScenarioValidator, HazardCalculator)
Custom Exception Handling
InvalidScenarioException
try/catch flow control
Input hardening
avoid NumberFormatException crash using readDouble()
Refactoring UC-wise
each UC adds and improves structure without breaking previous work
Quick example (so you “see” the algorithm)
Input:
Precision = 70
Density = 120
State = MAINTENANCE_DUE
Compute:
precisionHazard = 100 - 70 = 30
densityHazard = (120/200)*100 = 60
machineryHazard = 50
Score:
0.40*30 = 12
0.35*60 = 21
0.25*50 = 12.5
Total = 45.5 → MODERATE
