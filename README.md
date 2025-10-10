# Simple Budget Application

A Java-based budget management application.

## Requirements

- **Java 21 LTS** (JDK 21.0.5 or later)
- **Maven 3.9.11** or later

## Building the Project

```bash
mvn clean compile
```

## Running Tests

```bash
mvn test
```

## Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.simplebudget.App"
```

## Java 21 Upgrade

This project has been upgraded to use Java 21 LTS with the following changes:

### Updated Dependencies
- Maven Compiler Plugin: `3.13.0` (supports Java 21)
- Maven Surefire Plugin: `3.5.1` (updated for better Java 21 compatibility)  
- Maven Clean Plugin: `3.4.0`
- Maven Resources Plugin: `3.3.1`

### Configuration
- `maven.compiler.release`: Set to `21`
- All Maven plugins updated to latest versions compatible with Java 21

### Verification
- ✅ Project compiles successfully with Java 21
- ✅ Unit tests pass
- ✅ Application runs correctly