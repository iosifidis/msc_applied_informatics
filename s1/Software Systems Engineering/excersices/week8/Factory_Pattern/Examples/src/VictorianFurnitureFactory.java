
public class VictorianFurnitureFactory extends FurnitureFactory {

	@Override
	public Furniture createChair() {
		return new ChairVictorian();
	}

	@Override
	public Furniture createTable() {
		return new TableVictorian();
	}

	@Override
	public Furniture createSofa() {
		return new SofaVictorian();
	}

}
