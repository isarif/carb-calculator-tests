<!-- Badges -->
<p align="center">
  <a href="https://github.com/isarif/carb-calculator-tests/actions">
    <img src="https://github.com/isarif/carb-calculator-tests/actions/workflows/ci.yml/badge.svg" alt="Build Status">
  </a>

  <img src="https://img.shields.io/badge/Java-11-blue.svg" alt="Java 11">
  
  <img src="https://img.shields.io/badge/License-MIT-green.svg" alt="MIT License">

  <img src="https://img.shields.io/badge/Selenium-4.x-brightgreen.svg" alt="Selenium 4">
  
  <img src="https://img.shields.io/badge/Cucumber-JVM-orange.svg" alt="Cucumber JVM">
</p>


# Carbohydrate Calculator – Test Strategy & Automated UI Scenarios

UI Test Automation suite for the Carbohydrate Calculator on Calculator.net, featuring a full test strategy,
20+ manual test cases, and 3 automated end-to-end scenarios built using Selenium WebDriver + Java + Cucumber.

## How to run

```bash
mvn test
```

Cucumber HTML report will be generated at `target/cucumber-report.html`.

The Excel file `Carb_Calculator_Test_Strategy_and_Cases.xlsx` in the repo root contains:
- Test Strategy
- Test Cases
- Automation Scenarios mapping manual to automated tests.

## 🎥 Automation Demo (Watch the Tests Run!)

Below is a short recording of the Selenium + Cucumber tests running locally:

![Automation Running](docs/automation.gif)


## 🔍 How I Approached This Assignment

To approach this assignment, I treated it like a real-world QA engineering task: starting with a structured test strategy, expanding into detailed manual test coverage, and finally automating the most critical end-to-end flows.

### 1. Test Strategy & Planning
I began by analyzing the Carbohydrate Calculator to identify functional areas, boundaries, dependencies, and potential failure points.  
From this analysis, I created:

- A **comprehensive test strategy**
- Over **20 detailed test cases** across functional, validation, negative, boundary, accessibility, and UX categories
- A dedicated section mapping **which manual tests were chosen for automation**

This helped ensure clarity, traceability, and balanced coverage.

### 2. Automation Design
I selected **Java + Selenium WebDriver + Cucumber** to demonstrate BDD-style readability and modern automation practices.  
To keep the project scalable and maintainable, I built:

- A **Page Object Model (POM)** for clean abstraction
- A **DriverFactory** for WebDriver lifecycle management
- Cucumber **step definitions** that remain readable and business-friendly
- A reusable **JUnit runner** wired to Maven

The automated scenarios cover:
- US units calculation  
- Metric units calculation  
- Katch–McArdle BMR formula validation (Body Fat %)  

These represent critical functional paths, edge-case validation, and configuration-dependent logic.

### 3. CI Integration
To mirror professional QA workflows, I added a **GitHub Actions CI pipeline** that:

- Builds the project with Maven
- Runs all Cucumber scenarios on push/PR
- Uploads the **HTML Cucumber report** as a CI artifact

This demonstrates how automation integrates into continuous testing environments.

### 4. Deliverables
The repository includes:

- Full test strategy + test cases (Excel file)
- Automated test suite in structured Maven project
- CI pipeline
- Clean documentation explaining design choices and execution steps

Overall, this approach reflects how I typically design, organize, and deliver high-quality automated UI testing work in a real engineering environment.

---
