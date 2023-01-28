package dao.custom;

import dao.CrudDAO;
import entity.EmpAttendance;
import entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeAttendanceDAO extends CrudDAO<EmpAttendance, String> {

    List<String> getEmployeeID() throws SQLException, ClassNotFoundException;
    Employee getEmployee(String employeeID) throws SQLException, ClassNotFoundException;
}
