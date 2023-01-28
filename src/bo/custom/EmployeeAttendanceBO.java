package bo.custom;

import bo.SuperBO;
import dto.EmployeeAttendanceDTO;
import javafx.collections.ObservableList;
import view.tdm.EmployeeAttendanceTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceBO extends SuperBO {

    ArrayList<EmployeeAttendanceDTO> getAllEmployeeAttend() throws SQLException, ClassNotFoundException;
    boolean addEmployeeAttend(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException;
    boolean updateEmployeeAttend(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException;
    boolean deleteEmployeeAtted(String employeeID) throws SQLException, ClassNotFoundException;
    ObservableList<EmployeeAttendanceTM> searchEmployeeAttend(String s) throws SQLException, ClassNotFoundException;
}
