package invoice;

import java.util.Comparator;
import java.util.Iterator;

public class InvoiceList implements Iterable<Invoice> {

	private InvoiceNode<Invoice> start;
	private int size;
	private Comparator<Invoice> comp;

	public InvoiceList(Comparator<Invoice> comp) {
		this.start = null;
		this.size = 0;
		this.comp = comp;
	}

	public Comparator<Invoice> getComp() {
		return comp;
	}

	@Override
	public Iterator<Invoice> iterator() {
		return new IteratorInvoice();
	}

	class IteratorInvoice implements Iterator<Invoice> {
		InvoiceNode<Invoice> current = start;

		@Override
		public boolean hasNext() {
			if (current == null) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public Invoice next() {
			Invoice invoice = current.getInvoice();
			current = current.getNext();
			return invoice;
		}

	}
	
	

	public InvoiceNode<Invoice> getNodeAtIndex(int index) {

		if (index < 0 || index >= size) {

			throw new IllegalArgumentException("index out of bounds");

		}

		InvoiceNode<Invoice> node = start;

		for (int i = 0; i < index; i++) {

			if (node.getNext() == null) {
				return null;
			}

			node = node.getNext();

		}

		return node;

	}
	// Comparing invoiceTotal(TotalSubTotal) from highest to lowest

	public void add(Invoice invoice) {

		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(invoice);

		if (start == null) {
			start = newInvoiceNode;
			newInvoiceNode.setNext(null);
			size++;

			// only start node
		} else if (size == 1) {

			// case 1: new node is smaller
			// set start next as new node
			// set new node as null (end)

			if (getComp().compare(invoice, start.getInvoice()) < 0) {

				start.setNext(newInvoiceNode);
				newInvoiceNode.setNext(null);
				size++;

				// case 2: new node is larger
				// set the start to null (end)
				// make new node
			} else if (getComp().compare(invoice, start.getInvoice()) > 0) {

				newInvoiceNode.setNext(start);
				start.setNext(null);
				start = newInvoiceNode;
				size++;

			}
			// Has more than two nodes
		} else if (size > 1) {

			// case 1: new node is larger than start (first on the list)
			if (getComp().compare(invoice, start.getInvoice()) > 0) {

				newInvoiceNode.setNext(start);
				start.setNext(null);

				start = newInvoiceNode;

				size++;

				// case 2: new node is larger than neighbor of start node, new node in between
			} else if (getComp().compare(invoice, start.getNext().getInvoice()) > 0) {

				InvoiceNode<Invoice> startNeighbor = new InvoiceNode<Invoice>(start.getNext().getInvoice());

				start.setNext(newInvoiceNode);
				newInvoiceNode.setNext(startNeighbor);
				startNeighbor.setNext(null);

				size++;

				// case 3: new node is greater than or equal to neighbor of start ( or some
				// subsequent neighbors of start)
			} else {

				int index = 0;
				int count = 0;

				while (index == 0) {
					if (getComp().compare(invoice, getNodeAtIndex(count).getInvoice()) >= 0) {
						index = count;
					}
					count++;

				}

				newInvoiceNode.setNext(getNodeAtIndex(index));
				getNodeAtIndex(index - 1).setNext(newInvoiceNode);

				size++;

			}

		}

	}
	//Method to remove a node
public boolean remove(int position) {
		
		if(position < 1 || position > size){
			return false;
		}
		
		InvoiceNode<Invoice> currentNode = start;
		
		
		if (position == 1){
			start = start.getNext();
			size--;
			return true;
		}else if(position == size){
			for(int i = 1; i < position - 1; i++){
				if(currentNode.getNext() == null){
					return false;
				}
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(null);
			size--;
			return true;
		}else{
			InvoiceNode<Invoice> previousNode = start;
			for(int i = 1; i < position-1; i++){
				if(previousNode.getNext() == null){
					return false;
				}
				previousNode = previousNode.getNext();
			}
			InvoiceNode<Invoice> nodeToBeRemoved = previousNode.getNext();
			InvoiceNode<Invoice> nextNode = nodeToBeRemoved.getNext();
			previousNode.setNext(nextNode);
			size--;
			return true;
		}

		
	}
	
	

}