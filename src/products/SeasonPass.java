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

	public String getName() {
		return name;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public double getCost() {
		return cost;
	}

}
