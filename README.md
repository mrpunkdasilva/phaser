# Phaser - Android ToDo List Application

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)](https://android-arsenal.com/api?level=21)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()
[![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)]()

## Overview

Phaser is a modern, intuitive ToDo List application for Android that helps users organize their tasks efficiently. Built with a clean architecture and material design principles, Phaser provides a seamless user experience for managing daily tasks.

[//]: # (![Phaser App Screenshot]&#40;docs/screenshot.png&#41;)

## Features

- ✅ Create, read, update, and delete tasks
- 🔄 Drag and drop to reorder tasks
- 🎨 Clean and intuitive material design interface
- 💾 Persistent local storage using SQLite
- 🔔 Task status tracking
- 🌓 Support for Android 5.0 (API level 21) and above

## Technology Stack

- **Language**: Java
- **Platform**: Android
- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 32 (Android 12)
- **Database**: SQLite
- **UI Components**: RecyclerView, Material Design Components
- **Architecture**: MVC (Model-View-Controller)

## Getting Started

### Prerequisites

- Android Studio 4.0+
- JDK 8+
- Android SDK with API level 32

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/phaser.git
   ```

2. Open the project in Android Studio.

3. Sync Gradle files and build the project.

4. Run the application on an emulator or physical device.

### Using Docker

You can also build and run the project using Docker:

1. Build the Docker image:
   ```bash
   docker build -t phaser-android .
   ```

2. Run the container:
   ```bash
   docker run -it phaser-android
   ```

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/etec/com/br/gustavohj/phaser/
│   │   │   ├── Adapter/         # RecyclerView adapters
│   │   │   ├── Model/           # Data models
│   │   │   ├── Utils/           # Utility classes including DatabaseHandler
│   │   │   ├── MainActivity.java # Main application activity
│   │   │   ├── AddNewTask.java  # Dialog for adding new tasks
│   │   │   └── ...
│   │   ├── res/                 # Resources (layouts, drawables, etc.)
│   │   └── AndroidManifest.xml  # App manifest
│   ├── test/                    # Unit tests
│   └── androidTest/             # Instrumentation tests
└── build.gradle                 # App-level build configuration
```

## Development

### Building the Project

```bash
./gradlew assembleDebug
```

### Running Tests

```bash
./gradlew test
```

### Creating a Release Build

```bash
./gradlew assembleRelease
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Material Design for Android
- Android Jetpack libraries
- All contributors who have helped shape this project

---
