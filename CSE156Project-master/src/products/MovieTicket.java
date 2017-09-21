package products;

import dataContainer.Address;

public class MovieTicket extends Product {
	private String movieDateTime;
	private String movieName;
	private Address address;
	private String screenNumber;
	private double pricePerUnit;

	public MovieTicket(String productCode, String movieDateTime, String movieName, Address address, String screenNumber,
			double perPerUnit) {
		super(productCode);
		this.movieDateTime = movieDateTime;
		this.movieName = movieName;
		this.address = address;
		this.screenNumber = screenNumber;
		this.pricePerUnit = perPerUnit;
	}

	public String getMovieDateTime() {
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

	public double getPerPerUnit() {
		return pricePerUnit;
	}

}
