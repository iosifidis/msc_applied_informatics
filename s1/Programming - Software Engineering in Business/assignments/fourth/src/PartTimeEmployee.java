// Efstathios Iosifidis mai25017

public class PartTimeEmployee extends Employee {
    private int hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String lastName, String taxId, int hourlyRate) {
        super(lastName, taxId);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }

    public void setHoursWorked(int hours) {
        hoursWorked = hours;
    }

    public double calculateSalary() {
        double salary = hourlyRate * hoursWorked;
        if (salary < 500) {
            return salary + (salary * 0.2);
        }
        return salary;
    }

    public void printPayrollReport(String month) {
        super.printPayrollReport(month);
        System.out.println("PAYMENT RECORD FOR A PART-TIME EMPLOYEE");
        System.out.println("Employee's last name: " + lastName);
        System.out.println("Tax ID number: " + taxId);
        System.out.println("Hours: " + hoursWorked);
        System.out.println("Monthly total: " + calculateSalary() + " Euro");
        System.out.println("Remarks");
        System.out.println("Hourly wage = " + hourlyRate + " Euro");
        System.out.println("\n");
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }
}
