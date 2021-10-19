package edu.ics372.groupProject1.iterators;

import java.util.Iterator;

import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.facade.Result;

public class SafeProductIterator implements Iterator<Result> {
	private Iterator<Product> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Book.
	 * 
	 * @param iterator Iterator<Product>
	 */
	public SafeProductIterator(Iterator<Product> iterator) {
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
