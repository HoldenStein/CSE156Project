package invoice;

import customer.Customer;
import dataContainer.Person;
import products.Product;

import java.util.ArrayList;

public class Invoice {
    private String invoiceCode;
    private String customerCode;
    private String salespersonCode;
    private String invoiceDate;
    private ArrayList<Product> productList;
    private ArrayList<Customer> customerList;
    private ArrayList<Person> personList;
    private ArrayList<Invoice> invoiceList;
    private ArrayList<String> productCodes;

    public Invoice(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Customer> customerList, ArrayList<Person> personList, ArrayList<Product> productList, ArrayList<String> productCodes) {
        this.invoiceCode = invoiceCode;
        this.customerCode = customerCode;
        this.salespersonCode = salespersonCode;
        this.invoiceDate = invoiceDate;
        this.productList = productList;
        this.customerList = customerList;
        this.personList = personList;
        this.productCodes = productCodes;
    }

    public Invoice(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

//    Product Calculation Methods

    public double getDetailSubTotal() {
        double subTotal = 0;
        for(String s: this.productCodes) {
            String data[] = s.trim().split(":");
            int costMulti = Integer.parseInt(data[1]);
            if(data.length > 3) {
                subTotal += 0;
            }
            for(Product p : this.productList) {



                if(data[0].equals(p.getProductCode())) {
                    if(this.getCustomerType().equals("S")) {
                        double studentDiscount = 0.8;
                        subTotal += (p.getCost() * costMulti) * studentDiscount;
                    }

                    if(this.getCustomerType().equals("G")) {
                        subTotal += p.getCost() * costMulti;
                    }
                }
            }
        }
        return subTotal;
    }
    //    get ProductItem Details
    public ArrayList<String> getProductInfo() {
        ArrayList<String> productInfo = new ArrayList<String>();



//            Iterate throught the productCodes List
        for(String s : this.productCodes) {

//                split the productCode Data by a :
            String data[] = s.trim().split(":");
            int costMulti = Integer.parseInt(data[1]);

//                Iterate through the productList pertaining to the Invoice
            for(Product p : this.productList) {
                double subTotal = 0;
                double tax = 0;
                double total = 0;
                double discount = 0;

//                    Check if the product code from the personCodes List matches to a code from the ProductList
                if(data[0].equals(p.getProductCode())) {
//                        Add the product Details to an Array

//                    Checks for if there is a Movie Pass with the parking pass
                    if(data.length >= 3) {
                        productInfo.add(p.getProductCode() + " " + p.getProduct() + " " + data[2] + " (" + data[1] + " units @ " + p.getCost() + "/unit)\n"
                        + " SubTotal: " + subTotal + " Tax: " + tax + " Total: " + total);
                        break;
                    }



                    if(this.getCustomerType().equals("S")) {
                        double processingFee = 6.75;
                        discount = .08;
                        subTotal = (p.getCost() * costMulti) * discount;
                        tax = 0;
                        total = subTotal + processingFee;
                        productInfo.add(p.getProductCode() + " " + p.getProduct() + " (" + data[1] + " units @ " + p.getCost() + "/unit)\n"
                                + " SubTotal: " + subTotal + " Tax: " + tax + " Total: " + total);
                        break;
                    } else if(this.getCustomerType().equals("G")) {
                        subTotal = p.getCost() * costMulti;
                        tax = subTotal * p.getTax();
                        total = subTotal + tax;

                        productInfo.add(p.getProductCode() + " " + p.getProduct() + " (" + data[1] + " units @ " + p.getCost() + "/unit)\n"
                                + " SubTotal: " + subTotal + " Tax: " + tax + " Total: " + total);
                        break;
                    }

                    productInfo.add(p.getProductCode() + " " + p.getProduct() + "(" + data[1] + " units @ " + p.getCost() + "/unit)");
                }
            }

        }



        return productInfo;
    }




//    Summary Report Methods

    public String getCustomerName() {
        for(Customer c : this.customerList) {
            if(c.getCustomerCode().equals(this.customerCode)) {
                return c.getName();

            }
        }
        return "This is not a customer";
    }

    public String getSalesPersonName() {
        for(Person p: this.personList) {
            if(p.getPersonCode().equals(this.salespersonCode)) {
                return p.getLastName() + ", "  + p.getFirstName();
            }
        }

        return "This is not a person ";
    }


//      Detailed Report Methods

    public String getCustomerFullName() {
        for(Customer c : this.getCustomerList()) {
            if(c.getCustomerCode().equals(this.getCustomerCode())) {
                return c.getContact().getLastName() + ", " + c.getContact().getFirstName();
            }
        }

        return "This person does not exist";
    }

    public String getCustomerType() {
        for(Customer c : this.getCustomerList()) {
            if(c.getCustomerCode().equals(this.getCustomerCode())) {
                return c.getType();
            }
        }

        return "No Type";
    }

    public String getCustomerAddress() {
        for(Customer c : this.getCustomerList()) {
            if(c.getCustomerCode().equals(this.getCustomerCode())) {
                return c.getAddress().getFullAddress();
            }
        }

        return "No Address";
    }

//    Getter and Setter Methods

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

    public ArrayList<Product> getProductList() {
        return productList;
    }
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }


    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public ArrayList<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(ArrayList<String> productCodes) {
        this.productCodes = productCodes;
    }
}
