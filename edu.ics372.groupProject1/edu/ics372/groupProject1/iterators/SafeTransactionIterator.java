package edu.ics372.groupProject1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.groupProject1.entities.Transaction;
import edu.ics372.groupProject1.facade.Result;

public class SafeTransactionIterator implements Iterator<Result> {
	private Iterator<Transaction> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Book.
	 * 
	 * @param iterator Iterator<Transaction>
	 * @return
	 */
	public SafeTransactionIterator(Iterator<Transaction> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setTransactionFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}
