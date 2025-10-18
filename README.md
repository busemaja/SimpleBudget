# SimpleBudget

A JavaFX desktop application for personal budget tracking and expense management. Built with a reusable BudgetTracker module that can be integrated into other Java applications.

## Documentation by Audience

Choose the documentation that fits your needs:

### End Users
**Want to use SimpleBudget to track your expenses?**
- **[User Guide](docs/User_guide.md)** - Installation instructions and how to use the application

### Application Developers
**Want to build, modify, or contribute to the SimpleBudget application?**
- **[Developer Guide](docs/Developer_guide.md)** - Architecture, setup, and contribution guidelines
- **[Test Specification](docs/Test_specification.md)** - Complete test plan and test cases
- **[Requirement Specification](docs/Requirement_specification.md)** - Functional and non-functional requirements

### Module Users and Developers
**Want to integrate the BudgetTracker module into your own Java application or contribute to its future?**
- **[Module Github](https://github.com/busemaja/BudgetTracker)** - Link to the module repository

### Academic Review
**Examining this project for course assessment (Hello Daniel, sir! 🫡)?**
- **[Requirement Specification](docs/Requirement_specification.md)** - Complete requirements (FR and NFR)
- **[Test Specification](docs/Test_specification.md)** - Test plan and coverage
- **[Test Report](docs/Test_report_2025-10-18.md)** - Test execution results
- **[Reflection](docs/reflection.md)** - Course reflection document

## Quick Start

### For End Users (Windows)
1. Download the latest version from [Github releases](https://github.com/busemaja/SimpleBudget/releases)
2. Install and run from Start menu
3. See [User Guide](docs/User_guide.md) for detailed instructions

### For Developers
```bash
# Clone repository
git clone https://github.com/busemaja/SimpleBudget.git
cd SimpleBudget

# Build and run
mvn javafx:run

# Run tests
mvn test

# Create Windows installer
mvn clean package jpackage:jpackage
```

See [Developer Guide](docs/Developer_guide.md) for complete setup instructions.

## Features

- ✅ Add and remove transactions with categories
- ✅ Real-time total sum calculation
- ✅ Largest transaction tracking
- ✅ Category-based pie chart visualization
- ✅ Export transaction log to file
- ✅ Clean JavaFX user interface

## Technology Stack

- **Java 21 LTS** - Core language
- **JavaFX 21.0.8** - GUI framework
- **Maven 3.9+** - Build and dependency management
- **JUnit 4.11** - Unit testing
- **jpackage** - Native Windows installer

## Project Structure

```
simplebudget/
├── src/main/java/
│   ├── com.simplebudget/          # JavaFX application
│   └── budgettracker/             # Reusable module
├── src/main/resources/            # FXML and resources
├── src/test/java/                 # Unit tests
├── docs/                          # Documentation
│   ├── User_guide.md
│   ├── Developer_guide.md
│   ├── Requirement_specification.md
│   ├── Test_specification.md
│   └── reflection.md              # Only used for academic review
└── pom.xml                        # Maven configuration
```

## Testing

- **32 test cases** total (26 manual + 6 automated)
- All functional requirements covered
- See [Test Specification](docs/Test_specification.md) for details

## License

See [LICENSE](LICENSE) file for details.

## Author

**Maria Jansson**
- GitHub: [@busemaja](https://github.com/busemaja)
- Repository: [SimpleBudget](https://github.com/busemaja/SimpleBudget)

## Contributing

Contributions are welcome! See [Developer Guide](docs/Developer_guide.md) for contribution guidelines.

## Support

For issues, questions, or feature requests, please open an issue on GitHub.
