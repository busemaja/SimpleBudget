# SimpleBudget - Developer Documentation

## Project Overview

SimpleBudget is a JavaFX-based desktop budget management application that uses the BudgetTracker module for transaction management.

## Architecture

```
simplebudget/
├── src/main/java/
│   ├── com.simplebudget/          # JavaFX application layer
│   │   ├── App.java               # Main application entry point
│   │   ├── AppController.java     # FXML controller
│   │   └── BudgetTrackerAdapter.java  # Adapter between UI and module
│   └── budgettracker/             # Reusable module
│       ├── BudgetTracker.java     # Core transaction management
│       ├── Transaction.java       # Transaction model
│       ├── LogEntry.java          # Log entry model
│       ├── FileHandler.java       # File operations
│       └── TransactionCategories.java  # Category enums
├── src/main/resources/
│   └── main-view.fxml             # UI layout (built using SceneBuilder)
└── src/test/java/
    └── com.simplebudget/
        └── AppTest.java           # Unit tests
```

## Technology Stack

- **Java 21 LTS** - Core programming language
- **JavaFX 21.0.8** - GUI framework
- **Maven 3.9+** - Build tool and dependency management
- **JUnit 4.11** - Unit testing framework
- **jpackage** - Native installer creation

## Development Setup

### Prerequisites

1. **JDK 21** or later
2. **Maven 3.9.11** or later
3. **WiX Toolset 3.0+**
4. IDE with JavaFX support (IntelliJ IDEA, Eclipse, or VS Code)

> **Note:** WiX Toolset is required even for `.exe` output if you use `--win-menu`, `--win-shortcut`, or similar jpackage options.

### Clone and Build

```bash
git clone https://github.com/busemaja/SimpleBudget.git
cd SimpleBudget
mvn clean install
```

### Running in Development

```bash
mvn javafx:run
```

### Running Tests

```bash
mvn test
```

## Key Components

### App.java

Entry point for the JavaFX application. Loads the FXML layout and initializes the primary stage.

### AppController.java

Controller class that handles all UI interactions. Connects UI components to the BudgetTrackerAdapter.

### BudgetTrackerAdapter.java

Adapter pattern implementation that provides a clean interface between the UI layer and the BudgetTracker module. Handles data transformation and validation.

### BudgetTracker Module

See the [Module Github](https://github.com/busemaja/BudgetTracker) for details on using the module.

## Building Distribution

### Create Windows Installer

```bash
mvn clean package jpackage:jpackage
```

The installer will be created in `target/dist/SimpleBudget-1.0.1.exe`

### Configuration

Installer settings are in `pom.xml` under the jpackage plugin configuration:
- Application name and version
- Start menu and desktop shortcuts
- JavaFX module path configuration

## Testing Strategy

- **Unit Tests**: Test the BudgetTrackerAdapter and core logic
- **Manual Tests**: UI/UX testing following the Test Specification
- See [Test Specification.md](Test_specification.md) for complete test plan

## Code Style

- Use camelCase for methods and variables
- Use PascalCase for class names
- Keep methods focused and under 30 lines when possible
- Add Javadoc comments for public APIs
- Follow standard Java conventions (and take great inspiration from Clean Code by Robert C. Martin!)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Git Workflow

- **main branch**: Stable, release-ready code
- **feature branches**: New features and bug fixes
- Commit messages should be clear and descriptive
- Tag releases with semantic versioning (e.g., v1.0.1)

## Version Management

Version numbers are maintained in:
- `pom.xml` - Project version (`<version>`)
- `pom.xml` - Installer version (`<appVersion>`)

## Documentation

- **User_guide.md** - End user documentation
- **Requirement_specification.md** - Functional and non-functional requirements
- **Test_specification.md** - Complete test plan

## License

See [LICENSE](../LICENSE) file for details.

## Contact

Project maintained by: Maria Jansson
Repository: https://github.com/busemaja/SimpleBudget
