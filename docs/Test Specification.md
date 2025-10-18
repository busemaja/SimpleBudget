# SimpleBudget Test Plan

## Test Case Table

| Test ID | Requirement | Test Name | Test Type | Input Data | Expected Output | Status |
|---------|-------------|-----------|-----------|------------|-----------------|--------|
| TC1.1 | FR1 | Add valid transaction with all fields | Manual | Name: "Coffee", Amount: 5.50, Category: FOOD | Transaction appears in list with ID, total updates by +5.50 | ⬜ |
| TC1.2 | FR1 | Add transaction with spaces in name | Manual | Name: "Train ticket", Amount: 45.00, Category: TRANSPORTATION | Transaction appears as "Train_ticket" (spaces replaced with underscore) | ⬜ |
| TC1.3 | FR1 | Add transaction with comma as decimal separator | Manual | Name: "Lunch", Amount: "12,50", Category: FOOD | Transaction accepted, amount correct | ⬜ |
| TC1.4 | FR1 | Add multiple transactions | Manual | Add 3 different transactions | All 3 appear in list with sequential IDs | ⬜ |
| TC1.5 | FR1 | Attempt to add transaction with empty name | Manual | Name: "", Amount: 10.00, Category: FOOD | Error message: "All fields must be filled in." | ⬜ |
| TC1.6 | FR1 | Attempt to add transaction with empty amount | Manual | Name: "Test", Amount: "", Category: FOOD | Error message: "All fields must be filled in." | ⬜ |
| TC1.7 | FR1 | Attempt to add transaction without selecting category | Manual | Name: "Test", Amount: 10.00, Category: (none) | Error message: "All fields must be filled in." | ⬜ |
| TC2.1 | FR2 | Remove existing transaction | Manual | Select transaction from list, click REMOVE | Transaction disappears from list, total updates correctly | ⬜ |
| TC2.2 | FR2 | Remove button disabled initially | Manual | App startup | REMOVE button is disabled | ⬜ |
| TC2.3 | FR2 | Remove button enabled after selection | Manual | Click transaction in list | REMOVE button becomes enabled | ⬜ |
| TC3.1 | FR3 | Total displays nothing initially | Manual | App startup, no transactions | Total displays: "Total sum: -" | ⬜ |
| TC3.2 | FR3 | Total updates when adding transaction | Manual | Add transaction with amount 25,50 | Total displays: "Total sum: 25,50" | ⬜ |
| TC3.3 | FR3 | Total sum of multiple transactions | Manual | Add: 10,00, 20,50, 5,25 | Total displays: "Total sum: 35,75" | ⬜ |
| TC3.4 | FR3 | Total updates when removing transaction | Manual | Total is 50,00, remove transaction of 15,00 | Total displays: "Total sum: 35,00" | ⬜ |
| TC4.1 | FR4 | Largest transaction info shown | Manual | Add transactions: 10.00, 50.00, 25.00 | Info field shows details of 50.00 transaction | ⬜ |
| TC4.2 | FR4 | Largest transaction updates dynamically | Manual | Largest is 50.00, add 75.00 | Info field updates to show 75.00 transaction | ⬜ |
| TC4.3 | FR4 | Largest transaction after removal | Manual | Largest is 100.00, remove it | Info field updates to show next largest | ⬜ |
| TC5.1 | FR5 | Save log to file successful | Manual | Add transactions, click "Save log to file", select folder | File "transactionlog_YYYY-MM-DD.txt" created in selected folder | ⬜ |
| TC5.2 | FR5 | Save log file contains transaction data | Manual | Save log after adding transactions | Open file, verify it contains transaction details | ⬜ |
| TC5.3 | FR5 | Save log with no transactions | Manual | No transactions added, click "Save log to file" | Message: "No transactions in the log." | ⬜ |
| TC5.4 | FR5 | Cancel save dialog | Manual | Click "Save log to file", click Cancel in dialog | Message: "No folder selected." | ⬜ |
| TC6.1 | NFR4 | Show pie chart with category percentages | Manual | Add transactions in different categories, click "Show graph" | Pie chart appears with category segments | ⬜ |
| TC6.2 | NFR4 | Pie chart shows only non-zero categories | Manual | Add transactions only to FOOD, click "Show graph" | Pie chart shows only FOOD category | ⬜ |
| TC6.3 | NFR4 | Close pie chart popup | Manual | Open pie chart, click "Close" | Chart popup closes | ⬜ |
| TC7.1 | General | Exit application | Manual | Click "Exit app" button | Application closes gracefully | ⬜ |
| TC7.2 | General | All categories available in dropdown | Manual | Click category dropdown | All transaction categories shown (FOOD, TRANSPORT, ENTERTAINMENT, etc.) | ⬜ |

## Automated Unit Tests

The following tests are implemented in `AppTest.java` and can be run with `mvn test`:

| Test ID | Test Name | What It Tests | Status |
|---------|-----------|---------------|--------|
| UT1 | testAddValidTransaction | Adapter can add transaction with valid data and total updates | ⬜ |
| UT2 | testAddTransactionInvalidCategory | Adapter rejects invalid category | ⬜ |
| UT3 | testGetTotalSumInitiallyZero | Total sum is 0.00 when no transactions | ⬜ |
| UT4 | testGetCategories | All transaction categories are available (FOOD, TRANSPORTATION, ENTERTAINMENT, etc.) | ⬜ |
| UT5 | testMultipleTransactionsSum | Multiple transactions sum correctly | ⬜ |
| UT6 | testGetTransactionList | Transaction list returns added transactions | ⬜ |

## Test Execution Instructions

### Manual Tests
1. Start the application: `mvn javafx:run`
2. Go through each test case in the table above
3. Mark ✅ if passed, ❌ if failed
4. Note any issues in a separate document

### Automated Tests
```bash
mvn test
```

All unit tests should pass. If any fail, check the console output for details.

## Test Coverage Summary


- **FR1 (Add Transaction):** 7 manual test cases + 2 automated tests
- **FR2 (Remove Transaction):** 3 manual test cases
- **FR3 (Display Total):** 4 manual test cases + 2 automated tests
- **FR4 (Largest Transaction):** 3 manual test cases
- **FR5 (Save Log):** 4 manual test cases
- **Additional:** 5 test cases (UI/UX)
- **Automated Unit Tests:** 6 test cases

**Total:** 26 manual test cases + 6 automated tests = 32 test cases covering all functional and UI/UX requirements
