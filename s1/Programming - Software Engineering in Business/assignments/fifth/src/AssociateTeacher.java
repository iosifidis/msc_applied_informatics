public class AssociateTeacher extends Teacher{

    private double hourly_salary;
    private int num_of_hours_worked;

    public AssociateTeacher(String surname, String vat_number, int hourly_salary, int num_of_hours_worked) {
        super(surname, vat_number);
        this.hourly_salary = hourly_salary;
        this.num_of_hours_worked = num_of_hours_worked;
    }

    public double getHourlySalary() {
        return this.hourly_salary;
    }

    public int getNumOfHoursWorked() {
        return this.num_of_hours_worked;
    }

    public void setHourlySalary(double hourly_salary) {
        this.hourly_salary = hourly_salary;
    }

    public void setNumOfHoursWorked(int num_of_hours_worked) {
        this.num_of_hours_worked = num_of_hours_worked;
    }

    public String toString() {
        return super.toString() + "\nHourly Salary: " + this.hourly_salary + "\nNumber of Hours Worked: " + this.num_of_hours_worked;
    }

    public double calculateSalary() {
        return this.hourly_salary * this.num_of_hours_worked;
    }

}