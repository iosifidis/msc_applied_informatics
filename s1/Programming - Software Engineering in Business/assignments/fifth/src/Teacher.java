public class Teacher {

    private String surname;
    private String vat_number;

    public Teacher(String surname, String vat_number) {
        this.surname = surname;
        this.vat_number = vat_number;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getVatNumber() {
        return this.vat_number;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setVatNumber(String vat_number) {
        this.vat_number = vat_number;
    }

    public String toString() {
        return "Teacher: " + this.surname + "\nVAT Number: " + this.vat_number;
    }

    public double calculateSalary() {
        return 0;
    }

}