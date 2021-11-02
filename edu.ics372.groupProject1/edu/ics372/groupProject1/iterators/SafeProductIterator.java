package edu.ics372.groupProject1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.groupProject1.entities.Product;
import edu.ics372.groupProject1.facade.Result;

public class SafeProductIterator implements Iterator<Result> {
	private Iterator<Product> iterator;
	private Result result = new Result();

	/**
	 * This safe iterator class help support the iteration to Product.
	 * It does so by setting the the Product fields into the class result
	 * and returns the result.
	 * 
	 * @param iterator Iterator<Product>
	 */
	public SafeProductIterator(Iterator<Product> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setProductFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}
