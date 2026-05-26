# Library Management System

A lightweight, console-based Library Management System written in Java. This project demonstrates core Object-Oriented Programming (OOP) concepts such as inheritance, encapsulation, polymorphism, and abstraction by simulating daily library operations like managing books, students, and handling checkout/return workflows.

## Features

* **Book Management:** Add new titles to the system and view the catalog with real-time availability tracking.
* **Student Directory:** Register students to the library network with unique IDs and department details.
* **Issue & Return Workflow:** Check out books to students (enforcing a strict maximum limit of 3 books per user) and process returns to restore book availability instantly.
* **Dynamic Tracking:** Automated tracking of which books are currently borrowed by specific students using dynamic arrays (`ArrayList`).

---

## OOP Concepts Applied

* **Abstraction:** Implemented via the `LibraryOperations` interface to define strict rules for issuing and returning books.
* **Inheritance:** The `Person` base class is extended by specialized `Student` and `Librarian` subclasses to reuse core identity logic.
* **Encapsulation:** Class attributes (like availability states and ID records) are kept private/protected and accessed strictly through safe getter and setter methods.
* **Polymorphism:** Methods like `displayDetails()` are overridden across different classes to output context-specific information at runtime.

---

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 8 or higher installed on your machine.
* A terminal, command prompt, or an IDE (like VS Code, IntelliJ IDEA, or Eclipse).

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/ApoorvAryan/OOPs-Project.git](https://github.com/ApoorvAryan/OOPs-Project.git)
   cd OOPs-Project

2.   Compile the source code:

    Bash
    javac Main.java
3.  Run the compiled application:

    Bash
    java Main
    Project Structure
    Plaintext

# Project Structure
├── Main.java          # Single source file containing entry point and all class  definitions
└── README.md          # Project documentation