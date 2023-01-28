package bo.custom.impl;

import bo.custom.EmployeeAttendanceBO;
import dao.DAOFactory;
import dao.custom.EmployeeAttendanceDAO;
import dao.custom.EmployeeDAO;
import dto.EmployeeAttendanceDTO;
import entity.EmpAttendance;
import entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tdm.EmployeeAttendanceTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceBOImpl implements EmployeeAttendanceBO {

    private EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEEATTENDANCE);

    @Override
    public ArrayList<EmployeeAttendanceDTO> getAllEmployeeAttend() throws SQLException, ClassNotFoundException {

        ArrayList<EmployeeAttendanceDTO> allEmployeeAttend = new ArrayList<>();
        ArrayList<EmpAttendance> all = employeeAttendanceDAO.getAll();
        for (EmpAttendance empAttendance : all) {
            allEmployeeAttend.add(new EmployeeAttendanceDTO(empAttendance.getEmployeeID(), empAttendance.getEmployeeName(), empAttendance.getAttendDate(), empAttendance.getAttend()));
        }
        return allEmployeeAttend;
    }

    @Override
    public List<String> getEmployeeID() throws SQLException, ClassNotFoundException {
        List<String> employeeID = employeeAttendanceDAO.getEmployeeID();

        return employeeID;
    }

    @Override
    public Employee getEmployee(String employeeID) throws SQLException, ClassNotFoundException {
        Employee e1 = employeeAttendanceDAO.getEmployee(employeeID);
        return e1;
    }

    @Override
    public boolean addEmployeeAttend(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException {

        return employeeAttendanceDAO.add(new EmpAttendance(dto.getEmployeeID(), dto.getEmployeeName(), dto.getAttendDate(), dto.getAttend()));
    }

    @Override
    public boolean updateEmployeeAttend(EmployeeAttendanceDTO dto) throws SQLException, ClassNotFoundException {
        return employeeAttendanceDAO.update(new EmpAttendance(dto.getEmployeeID(), dto.getEmployeeName(), dto.getAttendDate(), dto.getAttend()));
    }

    @Override
    public boolean deleteEmployeeAtted(String employeeID) throws SQLException, ClassNotFoundException {

        return employeeAttendanceDAO.delete(employeeID);
    }

    @Override
    public ObservableList<EmployeeAttendanceTM> searchEmployeeAttend(String s) throws SQLException, ClassNotFoundException {

        ObservableList<EmployeeAttendanceTM> allEmployeeAttend = FXCollections.observableArrayList();
        ObservableList<EmpAttendance> empAttendance = employeeAttendanceDAO.search(s);

        for (EmpAttendance e : empAttendance) {
            allEmployeeAttend.add(new EmployeeAttendanceTM(e.getEmployeeID(), e.getEmployeeName(), e.getAttendDate(), e.getAttend()));
        }
        return allEmployeeAttend;
    }


}
