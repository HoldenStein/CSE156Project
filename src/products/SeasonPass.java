package products;

import org.joda.time.DateTime;

public class SeasonPass extends Ticket {

	private String name;
	private DateTime startDate;
	private DateTime endDate;
	private double cost;

	private double detailSubTotal;
	private boolean hasFee;
	private boolean hasProtrated;
	private double prorated;
	private int itemCount;
	private int daysRemaining;
	private int totalSeasonDays;

	public SeasonPass(String productCode, String productType, String name, DateTime startDate, DateTime endDate,
			double cost) {
		super(productCode, productType);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;

	}

	@Override
	public double getTotalCost() {

		double totalCost = detailSubTotal + getProductTax();

		return totalCost;
	}

	@Override
	public double getProductTax() {
		double tax = this.detailSubTotal * getTicketTax();
		return tax;
	}

	public double getFee() {

		return 8.0;
	}

	public void setDetailSubTotal(double detailSubTotal) {
		this.detailSubTotal = detailSubTotal;
	}

	public double getDetailSubTotal() {
		return this.detailSubTotal;
	}

	public void setHasFee(boolean hasFee) {
		this.hasFee = hasFee;
	}

	public boolean getHasFee() {
		return this.hasFee;

	}

	public void setProrated(double prorated) {
		this.prorated = prorated;
	}

	public double getProrated() {
		return this.prorated;
	}

	public boolean hasProtrated() {
		return this.hasProtrated;
	}

	public void setHasProtrated(boolean hasProtrated) {
		this.hasProtrated = hasProtrated;
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

	public double getCost() {
		return this.cost;
	}

	public String getProductTypeName() {
		return "SeasonPass";
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getDaysRemaining() {
		return this.daysRemaining;
	}

	public void setDaysRemaining(int daysRemaining) {
		this.daysRemaining = daysRemaining;
	}

	public int getTotalSeasonDays() {
		return this.totalSeasonDays;
	}

	public void setTotalSeasonDays(int totalSeasonDays) {
		this.totalSeasonDays = totalSeasonDays;
	}

}
