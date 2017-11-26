package invoice;

public class InvoiceNode<T> {

	private InvoiceNode<T> next;
	private Invoice invoice;

	public InvoiceNode(Invoice invoice) {
		this.invoice = invoice;
		this.next = null;

	}

	public void setNext(InvoiceNode<T> next) {
		this.next = next;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public InvoiceNode<T> getNext() {
		return next;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	
}
