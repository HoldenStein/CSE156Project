package com.ceg.ext;

/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Donot change any method signatures or the package name.
 * 
 */

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;


public class InvoiceData {

	public static void removeAllPersons() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement tableData = null;
		String query = "DELETE FROM Persons";

		try {
			tableData = conn.prepareStatement(query);
			tableData.executeUpdate();

			tableData.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeAllCustomers() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement tableData = null;
		String query = "DELETE FROM Customers";

		try {
			tableData = conn.prepareStatement(query);
			tableData.executeUpdate();

			tableData.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeAllProducts() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement tableData = null;
		String query = "DELETE FROM Products";

		try {
			tableData = conn.prepareStatement(query);
			tableData.executeUpdate();

			tableData.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removeAllInvoices() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement tableData = null;
		String query = "DELETE FROM Invoices";

		try {
			tableData = conn.prepareStatement(query);
			tableData.executeUpdate();

			tableData.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = "INSERT INTO Persons(personCode, personFName, personLName, personAddress, emailAddresses) VALUES(?,?,?,?, ' ');";

		try {
			String fullAddress = street + ", " + city + ", " + state + ", " + zip + ", " + country;
			p = conn.prepareStatement(query);
			p.setString(1, personCode);
			p.setString(2, firstName);
			p.setString(3, lastName);
			p.setString(4, fullAddress);
			p.executeUpdate();
			p.close();
			conn.close();


		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = null;
		try {
			String currentAddresses = InvoiceData.getEmailAddresses(personCode);

			if(currentAddresses.length() == 0) {
				query = "UPDATE Persons SET emailAddresses = "+ email +" WHERE personCode = " + personCode + ";";
			} else {
				query = "UPDATE Persons SET CONCAT(" + currentAddresses + ", "+ email +", ',') WHERE personCode = " + personCode + ";";
			}
			p = conn.prepareStatement(query);
			p.executeUpdate();
			p.close();
			conn.close();

		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}

	public static String getEmailAddresses(String personCode) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;
		ResultSet rs = null;
		String query = "SELECT emailAddresses FROM Persons WHERE personCode = " + personCode + ";";


		try {
			p = conn.prepareStatement(query);
			rs = p.executeQuery(query);
			p.close();
			conn.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return rs.toString();
	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = "INSERT INTO Customers(customerCode, customerType, primaryContact, customerName, address) VALUES(?,?,?,?,?);";

		try {
			String fullAddress = street + ", " + city + ", " + state + ", " + zip + ", " + country;
			p = conn.prepareStatement(query);
			p.setString(1, customerCode);
			p.setString(2, customerType);
			p.setString(3, primaryContactPersonCode);
			p.setString(4, name);
			p.setString(5, fullAddress);

			p.executeUpdate();
			p.close();
			conn.close();


		} catch(SQLException e) {
			e.printStackTrace();
		}
	}



	/**
	 * 6. Adds an movieTicket record to the database with the provided data.
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,String state, String zip, String country, String screenNo, double pricePerUnit) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = "INSERT INTO MovieTickets(productCodeM, productTypeM, movieDateTime, movieName, address, screenNumber, pricePerUnit) VALUES(?,?,?,?,?,?,?);";

		try {
			String fullAddress = street + ", " + city + ", " + state + ", " + zip + ", " + country;
			p = conn.prepareStatement(query);
			p.setString(1, productCode);
			p.setString(2, "M");
			p.setString(3, dateTime);
			p.setString(4, movieName);
			p.setString(5, fullAddress);
			p.setString(6, screenNo);
			p.setDouble(7, pricePerUnit);

			p.executeUpdate();
			p.close();
			conn.close();


		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 7. Adds a seasonPass record to the database with the provided data.
	 */
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,	double cost) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = "INSERT INTO SeasonPasses(productCodeS, productTypeS, seasonPassName, startDate,endDate, seasonPassCost) VALUES(?,?,?,?,?,?);";

		try {

			p = conn.prepareStatement(query);
			p.setString(1, productCode);
			p.setString(2, "S");
			p.setString(3, name);
			p.setString(4, seasonStartDate);
			p.setString(5, seasonEndDate);
			p.setDouble(6, cost);


			p.executeUpdate();
			p.close();
			conn.close();


		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = "INSERT INTO ParkingPasses(productCodeP, productTypeP, parkingFee) VALUES(?,?,?);";

		try {

			p = conn.prepareStatement(query);
			p.setString(1, productCode);
			p.setString(2, "P");
			p.setDouble(3, parkingFee);


			p.executeUpdate();
			p.close();
			conn.close();


		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 9. Adds a refreshment record to the database with the provided data.
	 */
	public static void addRefreshment(String productCode, String name, double cost) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = "INSERT INTO Refreshments(productCodeR, productTypeR,refreshmentName, refreshmentCost) VALUES(?,?,?,?);";

		try {

			p = conn.prepareStatement(query);
			p.setString(1, productCode);
			p.setString(2, "R");
			p.setString(3, name);
			p.setDouble(4, cost);


			p.executeUpdate();
			p.close();
			conn.close();


		} catch(SQLException e) {
			e.printStackTrace();
		}
	}



	/**
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;

		String query = "INSERT INTO ParkingPasses(invoiceCode, customerCode, salesPersonCode, invoiceDate) VALUES(?,?,?,?);";

		try {

			p = conn.prepareStatement(query);
			p.setString(1, invoiceCode);
			p.setString(2, customerCode);
			p.setString(3, salesPersonCode);
			p.setString(4, invoiceDate);


			p.executeUpdate();
			p.close();
			conn.close();


		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */
	public static String getProductCodes(String invoiceCode) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;
		ResultSet rs = null;
		String query = "SELECT productCodes FROM Invoices WHERE invoiceCode = " + invoiceCode + ";";


		try {
			p = conn.prepareStatement(query);
			rs = p.executeQuery(query);
			p.close();
			conn.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}

		return rs.toString();
	}
	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;
		String query = null;
		try {
		String currentCodes = InvoiceData.getProductCodes(invoiceCode);
		String newCode = productCode + ":" + quantity;
		if(currentCodes.length() == 0) {
			query = "UPDATE Invoices SET productCodes = "+ newCode +" WHERE invoiceCode = " + invoiceCode + ";";
		} else {
			query = "UPDATE Persons SET CONCAT("+ currentCodes +", "+ newCode +", ',') WHERE invoiceCode = " + invoiceCode + ";";
		}
		p = conn.prepareStatement(query);
		p.executeUpdate();
		p.close();
		conn.close();

	} catch (SQLException e ) {
		e.printStackTrace();
	}

	}

	/*
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;
		String query = null;
		try {
			String currentCodes = InvoiceData.getProductCodes(invoiceCode);
			String newCode = productCode + ":" + quantity;
			if(currentCodes.length() == 0) {
				query = "UPDATE Invoices SET productCodes = "+ newCode +" WHERE invoiceCode = " + invoiceCode + ";";
			} else {
				query = "UPDATE Persons SET CONCAT("+ currentCodes +", "+ newCode +", ',') WHERE invoiceCode = " + invoiceCode + ";";
			}
			p = conn.prepareStatement(query);
			p.executeUpdate();
			p.close();
			conn.close();

		} catch (SQLException e ) {
			e.printStackTrace();
		}

	}

     /**
     * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: ticketCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;
		String query = null;
		try {
			String currentCodes = InvoiceData.getProductCodes(invoiceCode);
			String newCode = productCode + ":" + quantity + ":" + ticketCode;
			if(currentCodes.length() == 0) {
				query = "UPDATE Invoices SET productCodes = "+ newCode +" WHERE invoiceCode = " + invoiceCode + ";";
			} else {
				query = "UPDATE Persons SET CONCAT("+ currentCodes +", "+ newCode +", ',') WHERE invoiceCode = " + invoiceCode + ";";
			}
			p = conn.prepareStatement(query);
			p.executeUpdate();
			p.close();
			conn.close();

		} catch (SQLException e ) {
			e.printStackTrace();
		}

	}
	
    /**
     * 15. Adds a particular refreshment (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement p = null;
		String query = null;
		try {
			String currentCodes = InvoiceData.getProductCodes(invoiceCode);
			String newCode = productCode + ":" + quantity;
			if(currentCodes.length() == 0) {
				query = "UPDATE Invoices SET productCodes = "+ newCode +" WHERE invoiceCode = " + invoiceCode + ";";
			} else {
				query = "UPDATE Persons SET CONCAT("+ currentCodes +", "+ newCode +", ',') WHERE invoiceCode = " + invoiceCode + ";";
			}
			p = conn.prepareStatement(query);
			p.executeUpdate();
			p.close();
			conn.close();

		} catch (SQLException e ) {
			e.printStackTrace();
		}

	}

}
