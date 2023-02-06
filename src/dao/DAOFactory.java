package dao;

import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.EmployeeAttendanceDAOImpl;
import dao.custom.impl.EmployeeDAOImpl;
import dao.custom.impl.VehicleDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case EMPLOYEEATTENDANCE:
                return new EmployeeAttendanceDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            default:
                return null;
        }

    }

    public enum DAOTypes {
        CUSTOMER, EMPLOYEE, EMPLOYEEATTENDANCE, VEHICLE
    }
}
