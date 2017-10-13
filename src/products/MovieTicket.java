package products;

import org.joda.time.DateTime;

import dataContainer.Address;

public class MovieTicket extends Ticket {
	private DateTime movieDateTime;
	private String movieName;
	private Address address;
	private String screenNumber;
	private double pricePerUnit;

	
	public MovieTicket(String productCode, String productType, DateTime movieDateTime, String movieName, Address address, String screenNumber,
			double pricePerUnit ) {
		super(productCode,productType);
		
		this.movieDateTime = movieDateTime;
		this.movieName = movieName;
		this.address = address;
		this.screenNumber = screenNumber;
		this.pricePerUnit = pricePerUnit;
		
	}


	@Override
	public String getProduct() {
		return this.getProductType() + " - " + this.movieName + " - " + "@" + this.address.getFullAddress() + ", " + this.movieDateTime.toString("MMM dd, yyyy HH:mm");
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

	@Override
	public double getCost() {
		return this.pricePerUnit;
	}

	@Override
	public double getTax() {
		return 0.06;
	}


}
