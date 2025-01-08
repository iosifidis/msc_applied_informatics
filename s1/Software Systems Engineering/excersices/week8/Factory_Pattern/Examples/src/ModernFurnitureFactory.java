
public class ModernFurnitureFactory extends FurnitureFactory{

	@Override
	public Furniture createChair() {
		return new ChairModern();
	}

	@Override
	public Furniture createTable() {
		return new TableModern();
	}

	@Override
	public Furniture createSofa() {
		return new SofaModern();
	}

}
