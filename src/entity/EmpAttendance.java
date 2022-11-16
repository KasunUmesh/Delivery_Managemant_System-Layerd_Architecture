package entity;

import java.time.LocalDate;

public class EmpAttendance {
    private String employeeID;
    private String employeeName;
    private LocalDate attendDate;
    private String attend;

    public EmpAttendance() {
    }

    public EmpAttendance(String employeeID, String employeeName, LocalDate attendDate, String attend) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.attendDate = attendDate;
        this.attend = attend;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(LocalDate attendDate) {
        this.attendDate = attendDate;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }
}
