package edu.ics372.groupProject1.iterators;

import java.util.Iterator;

import edu.ics372.groupProject1.entities.Order;
import edu.ics372.groupProject1.facade.Result;

public class SafeOrderIterator implements Iterator<Result> {
	private Iterator<Order> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Book.
	 * 
	 * @param iterator Iterator<Product>
	 */
	public SafeOrderIterator(Iterator<Order> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Result next() {
		// TODO Auto-generated method stub
		return null;
	}

}