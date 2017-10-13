package products;

public class Refreshment extends Service {

	private String name;
	private double cost;

	public Refreshment(String productCode, String productType, String name, double cost) {
		super(productCode,productType);
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public double getCost() {
		return cost;
	}
}
