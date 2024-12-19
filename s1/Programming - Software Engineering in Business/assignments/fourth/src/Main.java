// Efstathios Iosifidis mai25017

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Branch branch = new Branch("Thessaloniki, Tsimiski Branch");

        PartTimeEmployee e1 = new PartTimeEmployee("Nikolaou", "067832710", 4);
        FullTimeEmployee e2 = new FullTimeEmployee("Papadopoulos", "067832711", 1300);

        branch.addEmployee(e1);
        branch.addEmployee(e2);

        branch.setEmployeeHours();
        branch.printPayroll("DECEMBER");

    }
}
