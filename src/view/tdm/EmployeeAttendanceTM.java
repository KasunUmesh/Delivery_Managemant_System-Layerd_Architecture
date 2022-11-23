package view.tdm;

import java.time.LocalDate;

public class EmployeeAttendanceTM implements Comparable<EmployeeAttendanceTM> {

    private String employeeID;
    private String employeeName;
    private LocalDate attendDate;
    private String attend;

    public EmployeeAttendanceTM() {
    }

    public EmployeeAttendanceTM(String employeeID, String employeeName, LocalDate attendDate, String attend) {
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

    @Override
    public String toString() {
        return "EmployeeAttendanceTM{" +
                "employeeID='" + employeeID + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", attendDate=" + attendDate +
                ", attend='" + attend + '\'' +
                '}';
    }

    @Override
    public int compareTo(EmployeeAttendanceTM o) {
        return employeeID.compareTo(o.getEmployeeID());
    }
}
