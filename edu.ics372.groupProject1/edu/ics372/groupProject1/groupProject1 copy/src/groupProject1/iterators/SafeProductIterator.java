package groupProject1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import groupProject1.entities.Product;
import groupProject1.facade.Result;

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
