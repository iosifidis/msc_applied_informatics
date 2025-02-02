
public class Main {

	public static void main(String[] args) {
		
		Company companyX = new Company("Company X");
		
		Desktop d1 = new Desktop("Intel i7", 16, 512, true, 650);
        Desktop d2 = new Desktop("AMD Ryzen 5", 8, 256, false, 500);
        Laptop l1 = new Laptop("Intel i5", 8, 512, 10);
        Laptop l2 = new Laptop("Apple M1", 16, 1024, 15);
		
		
		
		// Προσθήκη στόλου στην εταιρία
    	companyX.addPC(l1);
    	companyX.addPC(l2);
    	companyX.addPC(d1);
    	companyX.addPC(d2);
    	
    	// Εκκίνηση της γραφικής διεπαφής
        new GUI(companyX);

	}

}
