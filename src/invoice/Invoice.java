package invoice;

import java.util.ArrayList;

public class Invoice {
    private String invoiceCode;
    private String customerCode;
    private String salespersonCode;
    private String invoiceDate;
    private ArrayList<String> productList;

    public Invoice(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<String> productList) {
        this.invoiceCode = invoiceCode;
        this.customerCode = customerCode;
        this.salespersonCode = salespersonCode;
        this.invoiceDate = invoiceDate;
        this.productList = productList;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }
    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getSalespersonCode() {
        return salespersonCode;
    }
    public void setSalespersonCode(String salespersonCode) {
        this.salespersonCode = salespersonCode;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<String> getProductList() {
        return productList;
    }
    public void setProductList(ArrayList<String> productList) {
        this.productList = productList;
    }

    public void getSummaryReport() {
        System.out.println("Executive Summary Report");
        System.out.println(
                String.format("%-10s %-30s %-20s %-15s %-15s %-15s %-15s %-15s", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes", "Discount", "Total")
        );


//        Iterate through customers

        System.out.println("TOTALS");
    }
}
