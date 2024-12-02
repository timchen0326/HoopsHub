# Project Team Members

| Name           | GitHub Username       |
|----------------|-----------------------|
| Alex Lee       | alexanderlee10        |
| Ian Lin        | ianlin1222            |
| Mohit Bendale  | Mohit1298             |
| Ryan Lee       | rjlee224              |
| Tim Chen       | timchen0326           |

# Hoops Hub

### **Contributors**
Alexander Lee, Tim Chen, Ryan Lee, Ian Lin, Mohit Bendale

---

### **Overview**
The Hoops Hub is a modular and interactive software designed for managing user accounts, performing player statistics analysis, and offering an engaging guessing game. This project was built to test and compare our knowledge of the NBA. The application integrates a robust search feature, history tracking, and customizable themes, all wrapped in an intuitive graphical user interface (GUI). It solves the problem of comparing NBA knowledge among friends and can also help other NBA enthusiasts test their knowledge.

---

### **Table of Contents**
1. [Overview](#overview)
2. [Features](#features)
   - [Account Management](#account-management)
   - [Player Statistics Analysis](#player-statistics-analysis)
   - [Guessing Game](#guessing-game)
   - [Search Functionality](#search-functionality)
   - [Customizable Themes and Audio](#customizable-themes-and-audio)
3. [System Architecture](#system-architecture)
4. [Installation](#installation)
5. [Usage Guide](#usage-guide)
6. [Key Classes and Responsibilities](#key-classes-and-responsibilities)
7. [Contributing](#contributing)
8. [License](#license)
9. [Feedbacks](#feedbacks)
10. [Acknowledgments](#acknowledgments)

---

### **Features**
#### **Account Management**
- Securely create new accounts.
- Login with existing credentials.
- Persistent session handling for user data like win/loss records and search history.

#### **Player Statistics Analysis**
- Fetch player statistics for specific years.
- View detailed data like points, rebounds, assists, and win ratios.
- Calculate average statistics based on selected metrics.

#### **Guessing Game**
- Make educated guesses on player statistics.
- Receive dynamic feedback and track wins/losses.

#### **Search Functionality**
- Search for users by username.
- View detailed search results, including wins, losses, and win ratios.
- Access and filter search history.

#### **Customizable Themes and Audio**
- Toggle between light and dark modes.
- Mute or unmute background music for an enhanced experience.

---

### **System Architecture**
The application follows a clean, modular architecture for scalability and maintainability:
- **Entity Layer**: Represents core data models like `User`, `PlayerStatistic`, and `SearchResult`.
- **Use Case Layer**: Encodes application logic such as account creation, search operations, and fetching player statistics.
- **Interface Adapters**: Facilitates communication between use cases and the GUI.
- **Frameworks and Drivers**: Manages data persistence and external service integrations.

---

### **Installation**
#### **Requirements**
- Java Development Kit (JDK): Version 17 or later.
- IDE: IntelliJ IDEA or Eclipse recommended.
- Dependencies: OkHttp, JSON, JUnit, and Swing for GUI.

#### **Steps**
1. Clone the repository:
2. Import the project into your preferred IDE.
3. Ensure all dependencies are installed via your build tool (e.g., Maven/Gradle).
4. Run the `MainApplication` file located in the `app` package.

### **Common Issues**
- **API Errors**: Ensure external API endpoints are accessible.
- **Dependency Conflicts**: Verify dependency versions align with project requirements.
- **Database Setup**: Confirm access to any required database or mock endpoints.

---

## Usage Guide

### **Navigation**
- **Login Page**: Start by logging in or creating a new account.
- **Home Panel**: Access features like "Play Game", "Search", "Search History", and "Settings".
- **Play Game**: Select a player, view statistics, and participate in the guessing game.
- **Search**: Find users and analyze their performance metrics.
- **Settings**: Toggle themes and manage background music.

---

## Key Classes and Responsibilities

- **MainFrame**: Manages panel navigation and initializes core components.
- **PlayGamePanel**: Core game interface for interacting with player statistics.
- **SearchPanel**: Provides user search functionality and result visualization.
- **ThemeManager**: Handles theme customization for light and dark modes.
- **MusicManager**: Manages background music playback.

---

## Contributing

We welcome contributions to Hoopshub! Here’s how to get started:

1. **Fork the Repository**: Navigate to the repository on GitHub, click "Fork", and clone it to your local machine.
2. **Create a Branch**: Create a branch for your changes with a descriptive name.
3. **Make Changes**: Follow coding standards, write tests, and add comments.
4. **Commit Changes**: Write clear and descriptive commit messages.
5. **Push and Submit**: Push your branch and open a pull request with a detailed explanation.

### **Merge Request Guidelines**
- Focus on one feature or bug fix per pull request.
- Include tests and update documentation if necessary.
- Ensure all tests pass before submitting.

### **Review Process**
- A team member will review your pull request and may request changes.
- Once approved, the changes will be merged into the main branch.

---

## License

This project is distributed under the **Creative Commons CC0 1.0 Universal** license. For details, please refer to the [license text](https://creativecommons.org/publicdomain/zero/1.0/).

---

## Feedbacks

We value your feedback! Please submit your suggestions or issues using [this form](https://docs.google.com/forms/d/e/1FAIpQLScS_66ZCrhX8Kh9Jc7PPe7rHInrigdtikFToifu5wCCC7QcOg/viewform?usp=sf_link).

---

## Acknowledgments

Special thanks to our team for their dedication and effort in building this application!


