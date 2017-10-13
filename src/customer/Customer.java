package customer;

import dataContainer.Address;
import dataContainer.Person;

public class Customer{

	private String customerCode;
	private String type;
	private Person Contact;
	private String customerName;
	private Address address;

	public Customer(String customerCode,String type, Person contact, String name, Address address) {
		this.customerCode = customerCode;
		this.Contact = contact;
		this.customerName = name;
		this.address = address;
		this.type = type;
	}

	// setters and getter
	public String getCustomerCode() {
		return customerCode;
	}

	public String getType() {
		return type;
	}

	public Person getContact() {
		return Contact;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public void setContact(Person contact) {
		this.Contact = contact;
	}

	public String getName() {
		return customerName;
	}

	public void setName(String customerName) {
		this.customerName = customerName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}



}
