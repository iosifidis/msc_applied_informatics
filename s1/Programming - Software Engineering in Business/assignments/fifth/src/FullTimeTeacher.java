public class FullTimeTeacher extends Teacher{

    private int monthly_salary;
    private double percentage_of_money_reserved;
    private int num_of_protected_members;

    public FullTimeTeacher(String surname, String vat_number, int monthly_salary, double percentage_of_money_reserved, int num_of_protected_members) {
        super(surname, vat_number);
        this.monthly_salary = monthly_salary;
        this.percentage_of_money_reserved = percentage_of_money_reserved;
        this.num_of_protected_members = num_of_protected_members;
    }

    public int getMonthlySalary() {
        return this.monthly_salary;
    }

    public double getPercentageOfMoneyReserved() {
        return this.percentage_of_money_reserved;
    }

    public int getNumOfProtectedMembers() {
        return this.num_of_protected_members;
    }

    public void setMonthlySalary(int monthly_salary) {
        this.monthly_salary = monthly_salary;
    }

    public void setPercentageOfMoneyReserved(double percentage_of_money_reserved) {
        this.percentage_of_money_reserved = percentage_of_money_reserved;
    }

    public void setNumOfProtectedMembers(int num_of_protected_members) {
        this.num_of_protected_members = num_of_protected_members;
    }

    public String toString() {
        return super.toString() + "\nMonthly Salary: " + this.monthly_salary + "\nPercentage of Money Reserved: " + this.percentage_of_money_reserved + "\nNumber of Protected Members: " + this.num_of_protected_members;
    }

    public double calculateSalary() {
        return this.monthly_salary * (1-this.percentage_of_money_reserved) + (this.num_of_protected_members * 100);
    }

}