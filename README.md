# Factory Robot Hazard Analyzer (Console-Based Java)

## Application Overview
The **Factory Robot Hazard Analyzer** is a console-based Java application that evaluates the **hazard risk score** of a factory robot based on:
- **Arm Precision**
- **Worker Density**
- **Machinery State**

The application validates inputs, calculates risk, and handles invalid scenarios using **custom exceptions**, demonstrating **Core Java**, **OOP**, and **Exception Handling** concepts.

---

## Tech Stack
- **Language:** Java
- **Type:** Console Application
- **Concepts:** OOP, Validation, Exception Handling, Modular Design
- **Process:** UC-wise development + GitFlow (feature branch per UC)

---

## Project Structure (Packages)
This project uses simple top-level packages:
- `app` — Console entry point and user interaction
- `domain` — Domain models and enums
- `service` — Validation and hazard calculation logic
- `exception` — Custom exception(s) for invalid scenarios
- `util` — Console input helpers

---

## Use Case Implementation Summary (UC1–UC8)

### UC1: Display Static Hazard Message
**Goal:** Display a static message indicating the purpose of the system.  
**What was implemented:**
- Created the class `FactoryRobotHazardAnalyzer`
- Printed **"Factory Robot Hazard Analyzer"** to the console
- Program exits after displaying the message

Key Concepts:
- `main()` method
- Print statements
- Program entry basics

---

### UC2: Accept Robot Hazard Inputs
**Goal:** Accept inputs required for hazard analysis from the user.  
**Inputs:**
- Arm Precision
- Worker Density
- Machinery State

What was implemented:
- Read all 3 inputs using `Scanner`
- Echoed back the user inputs
- No validation done in this UC

Key Concepts:
- `Scanner`
- Primitive + string input handling
- Console interaction

---

### UC3: Calculate Hazard Risk Score (No Validation)
**Goal:** Calculate and display the hazard risk score using the formula (assuming inputs valid).  
What was implemented:
- Implemented hazard risk computation
- Printed hazard risk score after taking inputs
- No validation checks

Key Concepts:
- Arithmetic expressions
- Basic business logic
- Method return values (conceptually)

---

### UC4: Introduce Validation Using Conditional Logic
**Goal:** Validate input ranges using `if-else`.  
What was implemented:
- Validated each input range
- If invalid, printed an error message
- If valid, proceeded to calculate risk

Key Concepts:
- Conditional statements
- Defensive programming
- Input validation

Drawback Noted:
- Validation cluttered `main()`
- No standard error handling

---

### UC5: Refactor Validation into a Separate Method
**Goal:** Move hazard calculation and validation into a separate method.  
What was implemented:
- `main()` calls `calculateHazardRisk(...)`
- Method validates inputs
- Method returns hazard score
- Cleaner, minimal `main()`

Key Concepts:
- Method abstraction
- Parameter passing
- Separation of concerns

---

### UC6: Introduce Custom Exception – RobotSafetyException
**Goal:** Handle invalid scenarios using a custom exception.  
What was implemented:
- Created `RobotSafetyException extends Exception`
- Validation throws `RobotSafetyException` when invalid
- `main()` catches exception and prints the message

Key Concepts:
- Custom exceptions
- `throw` / `throws`
- `try-catch`

Requirement Followed:
- Exception message displayed clearly

---

### UC7: Machinery State Risk Mapping
**Goal:** Map machinery state into risk factor using structured logic.  
Supported machinery states:
- **Worn**
- **Faulty**
- **Critical**

What was implemented:
- Mapped machinery state to a numeric risk factor
- Used risk factor inside hazard calculation
- Threw exception for unsupported machinery state

Key Concepts:
- Business rule encapsulation
- String comparison / structured mapping
- Controlled exception flow

---

### UC8: Fully Modular & OOPS-Compliant Hazard Analyzer
**Goal:** Deliver a clean, modular, extensible hazard analysis system.  
What was implemented:
- Inputs collected in `main()`
- Business logic handled in `RobotHazardAuditor` (or service layer)
- Validation via exception-based flow
- Clean output display
- Modular architecture using packages and SRP

Key Concepts:
- Encapsulation
- Modularity
- Single Responsibility Principle
- Reusability and maintainability

---

## OOP Principles Demonstrated
- **Encapsulation:** Scenario and processing logic stored inside classes
- **Abstraction:** User interacts via methods, not internal logic
- **Single Responsibility:** Each class has a single clear job
- **Extensibility:** Machinery mapping and logic can be extended easily

---

## GitFlow Development Process
This project was implemented UC-wise using GitFlow:
- One feature branch per UC
- UC branch merged into `develop`
- Feature branches kept using:
  - `git flow feature finish -k <feature-name>`
- Meaningful commits with consistent message format:
  - `[Devraj]: UCx - ...`

---

## How to Run
1. Open project in IntelliJ / Eclipse / VS Code
2. Run:
   - `app.FactoryRobotHazardAnalyzerApp`
3. Follow console prompts and view hazard risk output

---

## Notes
- Input validation ensures safety and avoids invalid scoring.
- Custom exception handling demonstrates structured error handling.
- Modular structure follows OOP and clean-code practices.

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
