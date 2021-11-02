package edu.ics372.groupProject1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.facade.Result;

public class SafeOrderIterator implements Iterator<Result> {
	private Iterator<Order> iterator;
	private Result result = new Result();

	/**
	 * This safe iterator class help support the iteration to Order.
	 * It does so by setting the the Order fields into the class result
	 * and returns the result.
	 * 
	 * @param iterator Iterator<Product>
	 */
	public SafeOrderIterator(Iterator<Order> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setOrderFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}