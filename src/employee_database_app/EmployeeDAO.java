package employee_database_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	public void createTable() {
		String createTableSQL = """
				CREATE TABLE IF NOT EXISTS employees (
				    id INT AUTO_INCREMENT PRIMARY KEY,
				    name VARCHAR(100) NOT NULL,
				    email VARCHAR(100) UNIQUE NOT NULL,
				    department VARCHAR(50) NOT NULL,
				    salary DECIMAL(10,2) NOT NULL
				)
				""";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(createTableSQL)) {

			statement.executeUpdate();
			System.out.println("Employee table created successfully.");

		} catch (SQLException e) {
			System.err.println("Error creating table: " + e.getMessage());
		}
	}

	public boolean addEmployee(Employee employee) {
		String insertSQL = "INSERT INTO employees (name, email, department, salary) VALUES (?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertSQL)) {

			statement.setString(1, employee.getName());
			statement.setString(2, employee.getEmail());
			statement.setString(3, employee.getDepartment());
			statement.setDouble(4, employee.getSalary());

			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error adding employee: " + e.getMessage());
			return false;
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		String selectSQL = "SELECT * FROM employees";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("email"), resultSet.getString("department"), resultSet.getDouble("salary"));
				employees.add(employee);
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving employees: " + e.getMessage());
		}

		return employees;
	}

	public Employee getEmployeeById(int id) {
		String selectSQL = "SELECT * FROM employees WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"),
						resultSet.getString("department"), resultSet.getDouble("salary"));
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving employee: " + e.getMessage());
		}

		return null;
	}

	public boolean updateEmployee(Employee employee) {
		String updateSQL = "UPDATE employees SET name = ?, email = ?, department = ?, salary = ? WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(updateSQL)) {

			statement.setString(1, employee.getName());
			statement.setString(2, employee.getEmail());
			statement.setString(3, employee.getDepartment());
			statement.setDouble(4, employee.getSalary());
			statement.setInt(5, employee.getId());

			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error updating employee: " + e.getMessage());
			return false;
		}
	}

	public boolean deleteEmployee(int id) {
		String deleteSQL = "DELETE FROM employees WHERE id = ?";

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteSQL)) {

			statement.setInt(1, id);
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error deleting employee: " + e.getMessage());
			return false;
		}
	}
}