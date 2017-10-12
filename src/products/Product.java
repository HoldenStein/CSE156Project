package products;

public class Product {
	private String productCode;
//	private String productType;

	public Product(String productCode) {
		this.productCode = productCode;

	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductType() {
		return "This product Does not Exist";
	}


}
