package products;

public abstract class Service implements Product {

	private String productCode;
	private String productType;

	public Service(String productCode, String productType) {
		this.productCode = productCode;
		this.productType = productType;
	}

	public double getServiceTax() {
		return 0.04;
	}

	@Override
	public String getProductCode() {
		return productCode;
	}

	@Override
	public String getProductType() {
		return productType;
	}

	@Override
	public double getProductTax() {
		return 0;
	}

	@Override
	public double getTotalCost() {
		return 0;
	}

}
