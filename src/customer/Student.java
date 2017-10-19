package customer;

import dataContainer.Address;
import dataContainer.Person;

public class Student extends Customer {

	public Student(String customerCode, String customerType, Person primaryContact, String name, Address address) {
		super(customerCode, customerType, primaryContact, name, address);
	}

	public double getStudentDiscount() {
		return 0.08;
	}

	public double getStudentFee() {
		return 6.75;

	}
}