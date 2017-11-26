package products;

public class ParkingPass extends Service {

	private double parkingFee;

	private boolean hasTicket;
	private double numOfTicket;
	private double detailSubTotal;
	private String ticketCode;
	private int itemCount;

	private int numOfFreeParking;

	public ParkingPass(String productCode, String productType, double parkingFee) {
		super(productCode, productType);
		this.parkingFee = parkingFee;
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

	public void setHasTicket(boolean hasTicket) {
		this.hasTicket = hasTicket;
	}

	public boolean getHasTicket() {

		return this.hasTicket;
	}

	public double getParkingFee() {
		return this.parkingFee;
	}

	public String getName() {

		return "ParkingPass";
	}

	public double getDetailSubTotal() {
		return this.detailSubTotal;
	}

	public void setDetailSubTotal(double detailSubTotal) {
		this.detailSubTotal = detailSubTotal;
	}

	public double getNumOfTicket() {
		return this.numOfTicket;
	}

	public void setNumOfTicket(double numOfTicket) {
		this.numOfTicket = numOfTicket;
	}

	public String getProductTypeName() {
		return "ParkingPass";
	}

	public String getTicketCode() {
		return this.ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getNumOfFreeParking() {
		return this.numOfFreeParking;
	}

	public void setNumOfFreeParking(int numOfFreeParking) {
		this.numOfFreeParking = numOfFreeParking;
	}

}
