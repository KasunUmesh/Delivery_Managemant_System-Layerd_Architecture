package bo.custom;

import bo.SuperBO;
import dto.EmployeeDTO;
import javafx.collections.ObservableList;
import view.tdm.EmployeeTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean addEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String employeeID) throws SQLException, ClassNotFoundException;

    String generateNewEmployeeID() throws SQLException, ClassNotFoundException;

    ObservableList<EmployeeTM> searchEmployee(String s) throws SQLException, ClassNotFoundException;
}
