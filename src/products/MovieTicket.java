package products;

import org.joda.time.DateTime;

import dataContainer.Address;

public class MovieTicket extends Ticket {
	private DateTime movieDateTime;
	private String movieName;
	private Address address;
	private String screenNumber;
	private double pricePerUnit;

	private double detailSubTotal;
	private boolean hasDiscount;
	private int itemCount;

	public MovieTicket(String productCode, String productType, DateTime movieDateTime, String movieName,
			Address address, String screenNumber, double pricePerUnit) {
		super(productCode, productType);

		this.movieDateTime = movieDateTime;
		this.movieName = movieName;
		this.address = address;
		this.screenNumber = screenNumber;
		this.pricePerUnit = pricePerUnit;

	}

	// setters and getters

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

	public String getProductTypeName() {
		return "MovieTicket";
	}

	public double getDiscount() {
		return 0.07;
	}

	public void setDetailSubTotal(double detailSubTotal) {
		this.detailSubTotal = detailSubTotal;
	}

	public double getDetailSubTotal() {
		return this.detailSubTotal;
	}

	public void setHasDiscount(boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}

	public boolean getHasDiscounted() {
		return this.hasDiscount;
	}

	public DateTime getMovieDateTime() {
		return this.movieDateTime;
	}

	public String getMovieName() {
		return this.movieName;
	}

	public Address getAddress() {
		return this.address;
	}

	public String getScreenNumber() {
		return this.screenNumber;
	}

	public double getPricePerUnit() {
		return this.pricePerUnit;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

}
