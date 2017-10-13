package products;

public class Refreshment extends Service {

	private String name;
	private double cost;

	public Refreshment(String productCode, String productType, String name, double cost) {
		super(productCode,productType);
		this.name = name;
		this.cost = cost;
	}

	@Override
	public String getProduct() {
		return this.name;
	}
	public String getName() {
		return this.name;
	}

	@Override
	public double getCost() {
		return this.cost;
	}

	@Override
	public double getTax() {
		return 0.04;
	}
}
