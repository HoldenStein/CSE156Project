package invoice;

import java.util.ArrayList;

public class Detail extends Invoice {
    public Detail(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<String> productList) {
        super(invoiceCode, customerCode, salespersonCode, invoiceDate, productList);
    }
}
