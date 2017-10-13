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

	public DateTime getMovieDateTime() {
		return movieDateTime;
	}

	public String getMovieName() {
		return movieName;
	}

	public Address getAddress() {
		return address;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}


}
