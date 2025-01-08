
public class MainClass {

	public static void main(String[] args) {
		Order ord = new Order(new VictorianFurnitureFactory());
		ord.addChair();
		ord.addChair();
		ord.addTable();
		ord.addSofa();

		ord.printInfo();
	}

}
