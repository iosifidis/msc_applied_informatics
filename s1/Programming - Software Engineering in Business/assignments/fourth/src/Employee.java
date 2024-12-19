// Efstathios Iosifidis mai25017

public class Employee {
    protected String lastName;
    protected String taxId;

    public Employee(String lastName, String taxId) {
        this.lastName = lastName;
        this.taxId = taxId;
    }

    public void printPayrollReport(String month) {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }


    public void setHoursWorked(int eHours) {

    }
}
