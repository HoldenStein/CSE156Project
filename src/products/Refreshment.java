package products;

public class Refreshment extends Service {

	private String name;
	private double cost;

	private boolean hasDiscount;
	private double detailSubTotal;
	private int itemCount;

	public Refreshment(String productCode, String productType, String name, double cost) {
		super(productCode, productType);
		this.name = name;
		this.cost = cost;
	}

	@Override
	public double getTotalCost() {

		double totalCost = detailSubTotal + getProductTax();

		return totalCost;
	}

	@Override
	public double getProductTax() {
		double tax = this.detailSubTotal * getServiceTax();
		return tax;
	}

	public double getDiscount() {
		return 0.05;
	}

	public String getName() {
		return this.name;
	}

	public double getCost() {
		return this.cost;
	}

	public boolean isHasDiscount() {
		return this.hasDiscount;
	}

	public void setHasDiscount(boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}

	public double getDetailSubTotal() {
		return this.detailSubTotal;
	}

	public void setDetailSubTotal(double detailSubTotal) {
		this.detailSubTotal = detailSubTotal;
	}

	public String getProductTypeName() {
		return "Refreshment";
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

}
