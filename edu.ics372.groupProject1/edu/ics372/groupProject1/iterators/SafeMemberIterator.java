package edu.ics372.groupProject1.iterators;

import java.util.Iterator;

import edu.ics372.groupProject1.entities.Member;
import edu.ics372.groupProject1.facade.Result;

public class SafeMemberIterator implements Iterator<Result> {
	private Iterator<Member> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Book.
	 * 
	 * @param iterator Iterator<Book>
	 */
	public SafeMemberIterator(Iterator<Member> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		return null;
	}
}
