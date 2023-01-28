package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.EmployeeAttendanceDAO;
import entity.EmpAttendance;
import entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceDAOImpl implements EmployeeAttendanceDAO {
    @Override
    public boolean add(EmpAttendance dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO EmpAttendance (employeeID, employeeName, attendDate, attend) VALUES (?,?,?,?)", dto.getEmployeeID(), dto.getEmployeeName(), dto.getAttendDate(), dto.getAttend());
    }

    @Override
    public boolean delete(String employeeID) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM EmpAttendance WHERE employeeID=?", employeeID);
    }

    @Override
    public boolean update(EmpAttendance dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE EmpAttendance SET employeeName=?, attendDate=?, attend=? WHERE employeeID=?", dto.getEmployeeName(), dto.getAttendDate(), dto.getAttend(), dto.getEmployeeID());
    }

    @Override
    public ArrayList<EmpAttendance> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmpAttendance> allEmployeeAttend = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM EmpAttendance");
        while (rst.next()) {
            allEmployeeAttend.add(new EmpAttendance(rst.getString("employeeID"), rst.getString("employeeName"), LocalDate.parse(rst.getString("attendDate")), rst.getString("attend")));
        }
        return allEmployeeAttend;
    }

    @Override
    public Employee getEmployee(String employeeID) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee WHERE employeeID=?", employeeID);
        if (rst.next()){
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }else {
            return null;
        }
    }

    @Override
    public ObservableList<EmpAttendance> search(String value) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM EmpAttendance WHERE CONCAT(employeeID, employeeName, attendDate) LIKE '%" + value + "%'");
        ObservableList<EmpAttendance> empAttendances = FXCollections.observableArrayList();
        while (rst.next()) {
            empAttendances.add(new EmpAttendance(rst.getString("employeeID"), rst.getString("employeeName"), LocalDate.parse(rst.getString("attendDate")), rst.getString("attend")));
        }
        return empAttendances;
    }

    @Override
    public List<String> getEmployeeID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Employee");
        List<String> id = new ArrayList<>();
        while (rst.next()){
            id.add(
                    rst.getString(1)
            );
        }
        return id;
    }


}
