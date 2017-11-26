package invoice;

import java.util.ArrayList;

import org.joda.time.DateTime;

import customer.Customer;
import dataContainer.Person;
import products.Product;

public class Executive extends Invoice {

	public Executive(String invoiceCode, String customerCode, String salespersonCode, DateTime invoiceDate,
			ArrayList<Customer> customerList, ArrayList<Person> personList, ArrayList<Product> productList,
			ArrayList<String> productCodes) {

		super(invoiceCode, customerCode, salespersonCode, invoiceDate, customerList, personList, productList,
				productCodes);
		

	}

	public Executive(InvoiceList invoiceOrderedList) {
		super(invoiceOrderedList);

	}

	public void getSummaryReport() {

		String grandTotalString = null;
		double totalSubTotal = 0;
		double totalFees = 0;
		double totalTaxes = 0;
		double totalDiscount = 0;
		double grandTotal = 0;

		System.out.println("==========================");
		System.out.println("Executive Summary Report");
		System.out.println("==========================");

		System.out.printf("%-10s %-40s %-40s %-10s %10s %12s %12s %12s\n", "Invoice", "Customer", "Salesperson",
				"Subtotal", "Fees", "Taxes", "Discount", "Total");

		for (Invoice i : this.getInvoiceList()) {

			totalSubTotal += i.getTotalDetailCost();
			totalFees += i.getFees();
			totalTaxes += i.getTotalTaxAmount();
			totalDiscount += i.getTotalDiscountAmount();
			grandTotal += i.getFinalTotal();

			String customerString = i.getCustomerName() + " [" + i.getCustomerTypeName() + "]";
			System.out.printf("%-10s %-40s %-33s %-4s %10.2f %s %10.2f %s %10.2f %s %10.2f %s %10.2f %n",
					i.getInvoiceCode(), customerString, i.getSalesPersonName(), "$", i.getTotalDetailCost(), "$",
					i.getFees(), "$", i.getTotalTaxAmount(), "$", i.getTotalDiscountAmount(), "$",
					i.getTotalSubTotal());

			grandTotalString = String.format("%n%-85s %-4s %10.2f %s %10.2f %s %10.2f %s %10.2f %s %10.2f %n", "TOTALS", "$",
					totalSubTotal, "$", totalFees, "$", totalTaxes, "$", totalDiscount, "$", grandTotal);
		}

		System.out.print(
				"=========================================================================================================================================================");
		System.out.println(grandTotalString);
	}
}
