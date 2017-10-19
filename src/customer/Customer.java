package customer;

import dataContainer.Address;
import dataContainer.Person;

public class Customer {

	private String customerCode;
	private String customerType;
	private Person primaryContact;
	private String customerName;
	private Address address;

	public Customer(String customerCode, String customerType, Person primaryContact, String name, Address address) {
		this.customerCode = customerCode;
		this.primaryContact = primaryContact;
		this.customerName = name;
		this.address = address;
		this.customerType = customerType;
	}

	public boolean isStudent() {
		if (getCustomerType() == "S") {
			return true;
		}
		return false;
	}

	public boolean isGeneral() {
		if (getCustomerType() == "G") {
			return true;
		}
		return false;
	}

	public double getStudentDiscount() {
		return 0.08;
	}

	public double getStudentFee() {
		return 6.75;
	}

	// setters and getter
	public String getCustomerCode() {
		return customerCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public Person getPrimaryContact() {
		return primaryContact;
	}

	public String getName() {
		return customerName;
	}

	public Address getAddress() {
		return address;
	}

}
