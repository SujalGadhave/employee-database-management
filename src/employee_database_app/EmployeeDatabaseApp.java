package employee_database_app;

import java.util.List;
import java.util.Scanner;

public class EmployeeDatabaseApp {
	private static final Scanner scanner = new Scanner(System.in);
	private static final EmployeeDAO employeeDAO = new EmployeeDAO();

	public static void main(String[] args) {
		System.out.println("=== Employee Database Management System ===");
		System.out.println("Initializing database...");

		employeeDAO.createTable();

		while (true) {
			showMenu();
			int choice = getIntInput("Enter your choice: ");

			switch (choice) {
			case 1:
				addEmployee();
				break;
			case 2:
				viewAllEmployees();
				break;
			case 3:
				viewEmployeeById();
				break;
			case 4:
				updateEmployee();
				break;
			case 5:
				deleteEmployee();
				break;
			case 6:
				System.out.println("Thank you for using Employee Database Management System!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice! Please try again.");
			}
		}
	}

	private static void showMenu() {
		System.out.println("\n=== MENU ===");
		System.out.println("1. Add Employee");
		System.out.println("2. View All Employees");
		System.out.println("3. View Employee by ID");
		System.out.println("4. Update Employee");
		System.out.println("5. Delete Employee");
		System.out.println("6. Exit");
		System.out.println("================");
	}

	private static void addEmployee() {
		System.out.println("\n=== Add New Employee ===");

		System.out.print("Enter employee name: ");
		String name = scanner.nextLine();

		System.out.print("Enter employee email: ");
		String email = scanner.nextLine();

		System.out.print("Enter department: ");
		String department = scanner.nextLine();

		double salary = getDoubleInput("Enter salary: ");

		Employee employee = new Employee(name, email, department, salary);

		if (employeeDAO.addEmployee(employee)) {
			System.out.println("Employee added successfully!");
		} else {
			System.out.println("Failed to add employee. Please check if email already exists.");
		}
	}

	private static void viewAllEmployees() {
		System.out.println("\n=== All Employees ===");
		List<Employee> employees = employeeDAO.getAllEmployees();

		if (employees.isEmpty()) {
			System.out.println("No employees found in the database.");
		} else {
			System.out.printf("%-5s %-20s %-25s %-15s %-10s%n", "ID", "Name", "Email", "Department", "Salary");
			System.out.println("------------------------------------------------------------------------------");
			for (Employee employee : employees) {
				System.out.printf("%-5d %-20s %-25s %-15s $%-9.2f%n", employee.getId(), employee.getName(),
						employee.getEmail(), employee.getDepartment(), employee.getSalary());
			}
		}
	}

	private static void viewEmployeeById() {
		System.out.println("\n=== View Employee by ID ===");
		int id = getIntInput("Enter employee ID: ");

		Employee employee = employeeDAO.getEmployeeById(id);
		if (employee != null) {
			System.out.println("\nEmployee Details:");
			System.out.println("ID: " + employee.getId());
			System.out.println("Name: " + employee.getName());
			System.out.println("Email: " + employee.getEmail());
			System.out.println("Department: " + employee.getDepartment());
			System.out.println("Salary: $" + employee.getSalary());
		} else {
			System.out.println("Employee with ID " + id + " not found.");
		}
	}

	private static void updateEmployee() {
		System.out.println("\n=== Update Employee ===");
		int id = getIntInput("Enter employee ID to update: ");

		Employee existingEmployee = employeeDAO.getEmployeeById(id);
		if (existingEmployee == null) {
			System.out.println("Employee with ID " + id + " not found.");
			return;
		}

		System.out.println("Current employee details:");
		System.out.println(existingEmployee);
		System.out.println("\nEnter new details (press Enter to keep current value):");

		System.out.print("Name (" + existingEmployee.getName() + "): ");
		String name = scanner.nextLine();
		if (name.trim().isEmpty())
			name = existingEmployee.getName();

		System.out.print("Email (" + existingEmployee.getEmail() + "): ");
		String email = scanner.nextLine();
		if (email.trim().isEmpty())
			email = existingEmployee.getEmail();

		System.out.print("Department (" + existingEmployee.getDepartment() + "): ");
		String department = scanner.nextLine();
		if (department.trim().isEmpty())
			department = existingEmployee.getDepartment();

		System.out.print("Salary (" + existingEmployee.getSalary() + "): ");
		String salaryStr = scanner.nextLine();
		double salary = existingEmployee.getSalary();
		if (!salaryStr.trim().isEmpty()) {
			try {
				salary = Double.parseDouble(salaryStr);
			} catch (NumberFormatException e) {
				System.out.println("Invalid salary format, keeping current value.");
			}
		}

		Employee updatedEmployee = new Employee(id, name, email, department, salary);

		if (employeeDAO.updateEmployee(updatedEmployee)) {
			System.out.println("Employee updated successfully!");
		} else {
			System.out.println("Failed to update employee.");
		}
	}

	private static void deleteEmployee() {
		System.out.println("\n=== Delete Employee ===");
		int id = getIntInput("Enter employee ID to delete: ");

		Employee employee = employeeDAO.getEmployeeById(id);
		if (employee == null) {
			System.out.println("Employee with ID " + id + " not found.");
			return;
		}

		System.out.println("Employee to delete:");
		System.out.println(employee);
		System.out.print("Are you sure you want to delete this employee? (y/n): ");
		String confirm = scanner.nextLine();

		if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
			if (employeeDAO.deleteEmployee(id)) {
				System.out.println("Employee deleted successfully!");
			} else {
				System.out.println("Failed to delete employee.");
			}
		} else {
			System.out.println("Delete operation cancelled.");
		}
	}

	private static int getIntInput(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}
	}

	private static double getDoubleInput(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		}
	}
}
