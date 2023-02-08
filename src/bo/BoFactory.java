package bo;

import bo.custom.impl.*;
import dao.custom.impl.EmployeeAttendanceDAOImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case EMPLOYEEATTENDANCE:
                return new EmployeeAttendanceBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case STOCKITEM:
                return new StockItemBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        CUSTOMER, EMPLOYEE, EMPLOYEEATTENDANCE, VEHICLE, STOCKITEM
    }
}
