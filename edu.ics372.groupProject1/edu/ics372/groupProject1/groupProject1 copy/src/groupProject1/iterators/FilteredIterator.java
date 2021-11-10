package groupProject1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import groupProject1.entities.Transaction;

public class FilteredIterator implements Iterator<Transaction> {
	private Transaction item;
	private Predicate<Transaction> predicate;
	private Iterator<Transaction> iterator;

	/**
	 * Sets the iterator and predicate fields and positions to the first item that
	 * satisfies the predicate.
	 * 
	 * @param iterator  the iterator to the list
	 * @param predicate specifies the test
	 */
	public FilteredIterator(Iterator<Transaction> iterator, Predicate<Transaction> predicate) {
		this.predicate = predicate;
		this.iterator = iterator;
		getNextItem();
	}

	@Override
	public boolean hasNext() {
		return item != null;
	}

	@Override
	public Transaction next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No such element");
		}
		Transaction returnValue = item;
		getNextItem();
		return returnValue;
	}

	/*
	 * This method searches for the next item that satisfies the predicate. If none
	 * is found, the item field is set to null.
	 */
	private void getNextItem() {
		while (iterator.hasNext()) {
			item = iterator.next();
			if (predicate.test(item)) {
				return;
			}
		}
		item = null;
	}

}
