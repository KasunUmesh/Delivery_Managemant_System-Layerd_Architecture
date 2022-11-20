package bo.custom.impl;

import bo.custom.EmployeeBO;
import dao.DAOFactory;
import dao.custom.EmployeeDAO;
import dto.EmployeeDTO;
import entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tdm.CustomerTM;
import view.tdm.EmployeeTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    private EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee employee : all) {
            allEmployee.add(new EmployeeDTO(employee.getEmployeeID(), employee.getName(), employee.getAddress(), employee.getContactNumber(), employee.getPosition()));
        }
        return allEmployee;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmployeeID(), dto.getName(), dto.getAddress(), dto.getContactNumber(), dto.getPosition()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmployeeID(), dto.getName(), dto.getAddress(), dto.getContactNumber(), dto.getPosition()));
    }

    @Override
    public boolean deleteEmployee(String employeeID) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeID);
    }

    @Override
    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }

    @Override
    public ObservableList<EmployeeTM> searchEmployee(String s) throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeTM> allEmployee = FXCollections.observableArrayList();
        ObservableList<Employee> employee = employeeDAO.search(s);

        for (Employee e : employee){
            allEmployee.add(new EmployeeTM(e.getEmployeeID(), e.getName(), e.getAddress(), e.getContactNumber(), e.getPosition()));
        }
        return allEmployee;
    }
}
