package dao;

import dao.custom.impl.*;

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
            case STOCKITEM:
                return new StockItemDAOImpl();
            case ORDER:
                return new CompanyOrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }

    }

    public enum DAOTypes {
        CUSTOMER, EMPLOYEE, EMPLOYEEATTENDANCE, VEHICLE, STOCKITEM, ORDER, ORDERDETAILS
    }
}
