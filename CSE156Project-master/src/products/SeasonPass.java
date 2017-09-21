package products;

public class SeasonPass extends Product {

	private String name;
	private String startDate;
	private String endDate;
	private double cost;

	public SeasonPass(String productCode, String name, String startDate, String endDate, double cost) {
		super(productCode);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public double getCost() {
		return cost;
	}

}
