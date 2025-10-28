# SoftwareFundamentalsProject

## Overview
This project was developed as part of a course at Universidad Politécnica de Madrid.  
It allows users to **play Battleship** using the provided **Battleship** and **Externals** libraries.  
The project is built with a layered 3-tier architecture (Model, Persistence, CLI), including user management, CSV-based persistence, and a command-line interface for game interaction.  
Design analysis and use cases were prepared based on the libraries' documentation.

## Features
- Play Battleship through a fully functional CLI  
- Manage users with login, logout, and sign-up functionality (emulated using Externals)  
- Implement a 3-tier architecture (Display, Logic, State)  
- Store and retrieve data using CSV-based persistence  
- Apply object-oriented design principles and integrate the Battleship and Externals libraries

## My Contribution
This was a group project; my main contributions included:

- **Analysis & Design**: prepared use cases and design diagrams using PlantUML
- **Display Layer**: implemented the `Usuario` interface  
- **Logic Layer**: developed `UserController` and `Puntuacion` classes  
- **Logic/Command Layer**: implemented `login`, `logout`, `signup`, and `verPuntuaciones` commands  
- **State Layer**: implemented CSV-based persistence  
- **Application Entry Point**: developed `App.java` to integrate all layers

  ## Tech Stack
- **Language:** Java 17  
- **Build Tool:** Maven  
- **Persistence:** CSV files  
- **CLI:** Standard Java console input/outpup
- **Design & Diagrams:** PlantUML

## Dependencies
| Dependency | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Main programming language |
| Maven | 3.8+ | Build and dependency management |
| Battleship | 1.11 | Provided game library |
| Externals | 5.1 | Provided auxiliary library |
| Jackson Databind | 2.15.2 | JSON serialization/deserialization |
| JUnit | 3.8.1 | Unit testing framework |

## Setup and Execution
1. **Clone this repository**  
   ```bash
   git clone https://github.com/Alvaro05RB/SoftwareFundamentalsProject.git
2. Open the project in your IDE (IntelliJ IDEA, Eclipse, etc.)
3. Run the project by executing the App.java
