package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.EmployeeDAO;
import entity.Customers;
import entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(Employee dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Employee (employeeID, name, address, contactNumber, position) VALUES (?,?,?,?,?)", dto.getEmployeeID(), dto.getName(), dto.getAddress(), dto.getContactNumber(), dto.getPosition());
    }

    @Override
    public boolean delete(String employeeID) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Employee WHERE employeeID=?", employeeID);
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Employee SET name=?, address=?, contactNumber=?, position=? WHERE employeeID=?", dto.getName(), dto.getAddress(), dto.getContactNumber(), dto.getPosition(), dto.getEmployeeID());
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee");
        while (rst.next()) {
            allEmployee.add(new Employee(rst.getString("employeeID"), rst.getString("name"), rst.getString("address"), rst.getString("contactNumber"), rst.getString("position")));
        }
        return allEmployee;
    }

    @Override
    public ObservableList<Employee> search(String value) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee WHERE CONCAT(employeeID, name, position) LIKE '%" + value + "%'");
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        while (rst.next()) {
            employees.add(new Employee(rst.getString("employeeID"), rst.getString("name"), rst.getString("address"), rst.getString("contactNumber"), rst.getString("position")));
        }
        return employees;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT employeeID FROM Employee ORDER BY employeeID DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("employeeID");
            int newEmployeeID = Integer.parseInt(id.replace("E", "")) + 1;
            return String.format("E%03d", newEmployeeID);
        }else {
            return "E001";
        }
    }
}
