# Real-time Stock Monitoring System

## 📑 Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [🚀 Quick Run](#quick-run)
4. [Results Achieved](#results-achieved)
5. [Student Implementation](#student-implementation)
6. [Project Structure](#project-structure)
7. [Design Patterns](#design-patterns)
8. [License](#license)

---

**Course:** Advanced Programming (CO2039)

---

## Overview

A simulation of a real-time stock monitoring system that aggregates price data from multiple sources (HOSE, HNX), updates a central feeder, and notifies views via the Observer pattern. Demonstrates design patterns (Observer, Adapter) and scheduled task execution in Java. This implementation got Perfect Score.

---

## Features

- **Multi-source Data Aggregation:** Fetches stock prices from HOSE and HNX exchanges via adapter pattern.
- **Real-time Price Updates:** Continuously monitors and updates stock prices at configurable intervals (1 minute per assignment).
- **Observer-based Notification:** Registered viewers are automatically notified when prices change, enabling decoupled view updates.
- **Customizable Alerts:** `StockAlertView` raises alerts when prices meet user-defined thresholds (above/below configured values).
- **Real-time Price Display:** `StockRealtimePriceView` displays the latest price information immediately upon updates.
- **Ticker Summaries:** `StockTickerView` provides periodic aggregated metrics (high, low, open, close, volume, average price) every 10 seconds.
- **Centralized Logging:** All system messages are routed through a unified `Logger` utility for consistency and easy output redirection.
- **Error Handling:** Gracefully handles edge cases such as registering viewers for non-existent stock codes.

---

## Quick Run

**Requirements:** Java 8+ and Maven 3+

```bash
mvn clean package
mvn exec:java -Dexec.mainClass=com.myproject.Main
```

*Note: `PriceFetchManager` runs at 1-minute intervals. Adjust for faster testing in code if needed.*

---

## Results Achieved

**Evaluation:** Perfect Score (Full Score)

**Correctness:** All four required classes implemented; Observer and Adapter patterns correctly applied; price updates propagate to all registered observers.

**Business Logic:** Register/unregister validation works correctly; error messages follow specification; all logging via `Logger` (no direct prints); alert evaluation and ticker aggregation accurate.

**Technical Quality:** Thread-safe concurrent access; proper OOP design; edge cases handled gracefully.

---

## Student Implementation

Four core files implemented under `src/main/java/com/myproject/`:

1. **`HoseAdapter.java`** — Implements `PriceFetcher`; calls `HosePriceFetchLib` and converts data to `StockPrice`.
2. **`StockFeeder.java`** — Observer Subject; manages monitored stocks and viewer registration per stock code; notifies observers on price updates.
3. **`StockAlertView.java`** — Observer; logs alerts via `Logger.logAlert()` when price thresholds are met.
4. **`StockRealtimePriceView.java`** — Observer; logs updates in real-time via `Logger.logRealtime()`.

---

## Project Structure

```
real-time-stock-monitor/
    pom.xml
    README.md  (this report)
    src/
        main/
            java/com/myproject/
                Main.java
                Stock.java
                StockPrice.java
                StockFeeder.java            # ← student implementation
                StockViewer.java
                StockAlertView.java         # ← student implementation
                StockRealtimePriceView.java # ← student implementation
                StockTickerView.java
                Logger.java
                PriceFetchManager.java
                PriceFetcher.java
                HosePriceFetchLib.java
                HoseAdapter.java            # ← student implementation
                HoseData.java
                HnxPriceFetchLib.java
                HnxAdapter.java
                HnxData.java
    src/main/resources/
        stocks.json
```

---

## Design Patterns

- **Observer:** `StockFeeder` (Subject) notifies registered viewers on price updates.
- **Adapter:** `PriceFetcher` interface adapts external price libraries to a common API.
- **Logging:** Centralized `Logger` for consistent output formatting.

---

## License

This project is released under the MIT License. See the included `LICENSE` file in the repository root for the full text.

SPDX-License-Identifier: MIT

---

## 👤 Author

**Truong Trung Bao**  
Student, Ho Chi Minh City University of Technology (HCMUT — VNU)

- GitHub: [BrianTr9](https://github.com/BrianTr9)
- University: [Ho Chi Minh City University of Technology (HCMUT)](https://www.hcmut.edu.vn)
- Course: CO2039 - Advanced Programming (Semester 1, 2025)

