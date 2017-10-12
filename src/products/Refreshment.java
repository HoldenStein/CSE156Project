package products;

public class Refreshment extends Product {

	private String name;
	private double cost;

	public Refreshment(String productCode, String name, double cost) {
		super(productCode);
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public double getCost() {
		return cost;
	}

	@Override
	public String getProductType() {
		return "Refreshment";
	}

}
