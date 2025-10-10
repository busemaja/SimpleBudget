# SimpleBudget App Requirement Specification

## 1. Purpose

The purpose of the SimpleBudget App is to provide a simple graphical interface for managing personal finances.  
It uses the `BudgetTracker` module to handle all transaction logic, calculations, and file operations.

## 2. Scope

The app allows the user to:
- Add new transactions (name, amount, category).
- Remove existing transactions.
- View current total and totals by category.
- View the largest transaction.
- Display and save a transaction log to file.

All business logic is handled by the `BudgetTracker` module. The JavaFX interface only presents data and handles user input.

## 3. Functional Requirements
ID|Requirement|Priority|Testability
|-|-|-|-|
FR1|The user shall be able to add a transaction through the GUI.|High|Add valid input → new transaction appears in list.
FR2|The user shall be able to remove a transaction through the GUI.|High|Remove selected transaction → it disappears.
FR3|The system shall display the current total of all transactions.|High|Compare displayed total with manual sum.
FR4|The system shall display totals per category.|Medium|Compare category totals with manual calculation.
FR5|The system shall show the largest transaction’s details.|Medium|Verify displayed info matches the largest amount.
FR6|The system shall display a log of all transaction actions.|Medium|Verify log entries correspond to performed actions.
FR7|The system shall allow the user to save the transaction log to a text file.|Low|Verify file exists and contains expected log data.

## 4. Non-Functional Requirements
ID|Requirement|Priority
|-|-|-|
NFR1|The application shall be implemented in Java using JavaFX for the GUI.|High
NFR2|All financial logic shall be handled by the `BudgetTracker` module.|High
NFR3|The GUI shall be intuitive, responsive, and visually minimal.|Medium
NFR4|Invalid user input shall trigger clear error messages.|Medium
NFR5|The system shall run on Windows 11 and Java 17 or later.|Low

## 5. Dependencies
- Java 17 or later
- Maven 3.8+
- JavaFX (org.openjfx)
- `BudgetTracker` module v1.0.0