package dataContainer;

public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;

	// Constructor
	public Address(String fulladdress) {
		String[] address = fulladdress.split(",");
		this.street = address[0];
		this.city = address[1];
		this.state = address[2];
		this.zip = address[3];
		this.country = address[4];

	}

	// Getter methods

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}

}