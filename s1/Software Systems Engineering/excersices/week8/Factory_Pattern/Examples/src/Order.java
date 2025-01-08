import java.util.ArrayList;

public class Order {
	ArrayList<Furniture> fList = new ArrayList<Furniture>();
	FurnitureFactory ff;
	
	public Order(FurnitureFactory f) {
		ff = f;
	}

	public void addChair() {
		fList.add(ff.createChair());
	}

	public void addSofa() {
		fList.add(ff.createSofa());
	}

	public void addTable() {
		fList.add(ff.createTable());
	}

	public void printInfo() {
		for (int i=0; i<fList.size(); i++) {
			fList.get(i).printInfo();
		}
	}
}
