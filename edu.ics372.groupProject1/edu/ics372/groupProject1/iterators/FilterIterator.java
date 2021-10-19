package edu.ics372.groupProject1.iterators;

import java.util.Iterator;
import java.util.function.Predicate;

import edu.ics372.groupProject1.entities.Transaction;

public class FilterIterator implements Iterator<Transaction> {
	private Transaction item;
	private Predicate<Transaction> predicate;
	private Iterator<Transaction> iterator;

	public FilterIterator() {

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction next() {
		// TODO Auto-generated method stub
		return null;
	}

}
