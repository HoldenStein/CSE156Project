package customer;

import dataContainer.Address;
import dataContainer.Person;

public class Student extends Customer{
	
	public Student(String customerCode,String type, Person contact, String name, Address address) {
		
		super(customerCode, type, contact, name, address);
		
	}
}
