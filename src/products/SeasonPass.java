package products;

import org.joda.time.DateTime;

public class SeasonPass extends Ticket {

	private String name;
	private  DateTime startDate;
	private DateTime endDate;
	private double cost;

	public SeasonPass(String productCode, String productType, String name, DateTime startDate, DateTime endDate, double cost) {
		super(productCode,productType);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
	}

	@Override
	public String getProduct() {
		return this.getProductType() + " " + this.name + ", Start Date: " + this.startDate.toString("MMM dd, yyyy") + " - End Date:" + this.endDate.toString("MMM dd, yyyy");
	}
	public String getName() {
		return this.name;
	}

	public DateTime getStartDate() {
		return this.startDate;
	}

	public DateTime getEndDate() {
		return this.endDate;
	}


	@Override
	public double getCost() {
		return this.cost;
	}

	@Override
	public double getTax() {
		return 0.06;
	}

}
