# Employee Database Management System - JDBC

A comprehensive Java application demonstrating JDBC connectivity with MySQL database for performing CRUD operations on employee records.

## 📋 Project Overview

This project implements a complete Employee Database Management System using Java JDBC to fulfill **Task 7** requirements. The application provides a console-based interface for managing employee records with full CRUD functionality.

### Key Features:
- ✅ Java JDBC connectivity with MySQL
- ✅ Complete CRUD operations (Create, Read, Update, Delete)
- ✅ PreparedStatement usage for SQL injection prevention
- ✅ Proper connection management and exception handling
- ✅ Interactive console-based user interface
- ✅ Input validation and error handling

## 🛠️ Technologies Used

- **Java 11+**: Core programming language
- **MySQL 8.0+**: Database management system
- **JDBC Driver**: MySQL Connector/J 8.0.33
- **Eclipse IDE**: Development environment

## ⚙️ Prerequisites & Setup

### 1. Install Required Software
- **Java JDK 11+**: Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- **MySQL 8.0+**: Download from [MySQL Official Site](https://dev.mysql.com/downloads/)
- **MySQL JDBC Driver**: Download from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

### 2. Database Setup
```bash
# Start MySQL service
sudo systemctl start mysql  # Linux
brew services start mysql   # macOS
# For Windows: Start MySQL from Services

# Connect to MySQL
mysql -u root -p
```

Run the database setup script:
```sql
-- Execute the contents of database/database_setup.sql
-- This will create the database, table, and sample data
```

### 3. Configuration
Update database connection details in `DatabaseConnection.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
private static final String USERNAME = "root";        // Your MySQL username
private static final String PASSWORD = "your_password"; // Your MySQL password
```

## 🚀 How to Run

### Method 1: Command Line
```bash
# Navigate to project directory
cd employee-database-jdbc

# Compile with MySQL driver in classpath
javac -cp "lib/mysql-connector-java.jar:." src/EmployeeDatabaseApp.java

# Run with MySQL driver in classpath
java -cp "lib/mysql-connector-java.jar:src" EmployeeDatabaseApp
```

### Method 2: VS Code
1. Open project folder in VS Code
2. Add MySQL JDBC driver to classpath
3. Use "Run Java" extension
4. Execute `EmployeeDatabaseApp.java`

## 📸 Application Screenshots

### 1. Adding New Employee
<img width="1028" height="527" alt="Screenshot 2025-08-14 194724" src="https://github.com/user-attachments/assets/9724f19d-7ab1-4d4a-998a-b88296b65c63" />
*Screenshot demonstrating the add employee functionality*

### 2. Viewing All Employees
<img width="1021" height="414" alt="Screenshot 2025-08-14 194907" src="https://github.com/user-attachments/assets/d12411bf-787f-4058-b265-e05887844eaf" />
*Screenshot showing all employees in tabular format*

### 3. Search Employee by ID
<img width="1017" height="524" alt="Screenshot 2025-08-14 194834" src="https://github.com/user-attachments/assets/d16d464c-79ff-48f8-bfe4-efd5516d8cc1" />
*Screenshot of searching and displaying specific employee*

### 4. Update Employee Details
<img width="1260" height="594" alt="Screenshot 2025-08-14 194848" src="https://github.com/user-attachments/assets/334bc4b8-baa8-4738-820e-f87f8dc2584c" />
*Screenshot showing the update employee process*

### 5. Delete Employee
<img width="1155" height="739" alt="Screenshot 2025-08-14 194927" src="https://github.com/user-attachments/assets/4a167dcf-f9f5-43fc-9ec8-af62d391c658" />
*Screenshot demonstrating employee deletion with confirmation*

### 6. Database Table View
<img width="1916" height="1058" alt="Screenshot 2025-08-14 194941" src="https://github.com/user-attachments/assets/4ba392ac-1cc8-4492-a9cc-1f7f34b91471" />
*Screenshot of MySQL database showing the employees table*

## 🏆 Learning Outcomes

- ✅ Master JDBC connection and configuration
- ✅ Implement secure database operations with PreparedStatement
- ✅ Handle SQL exceptions properly
- ✅ Design and implement CRUD operations
- ✅ Manage database resources efficiently
- ✅ Create user-friendly console interfaces
- ✅ Understand database connectivity best practices
