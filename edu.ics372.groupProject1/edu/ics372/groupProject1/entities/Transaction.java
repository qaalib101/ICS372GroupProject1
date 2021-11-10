package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
* The class Transaction is an object that stores information about the
* totalPrice of that member's transaction. It also holds information about 
* the date of which that Transaction was constructed, and a method to
* check if two given dates are within an acceptable range. Which that is
* used for returning the member's transactions within the given range.
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
public class Transaction implements Serializable {
	// Instance variables
	private static final long serialVersionUID = 1L;
	private Calendar date;
	private double totalPrice;

	public Transaction(double totalPrice) {
		this.totalPrice = totalPrice;
		date = new GregorianCalendar();
	}

	/**
	 * Checks whether a transaction is within an acceptable date range
	 * 
	 * @param startDate the start date of the date range
	 * @param endDate   the end date of the date range
	 * @return true if startDate is before endDate
	 */
	public boolean datesInRange(Calendar startDate, Calendar endDate) {
		int year1 = startDate.get(Calendar.YEAR);
		int month1 = startDate.get(Calendar.MONTH);
		int day1 = startDate.get(Calendar.DATE);
		int year2 = endDate.get(Calendar.YEAR);
		int month2 = endDate.get(Calendar.MONTH);
		int day2 = endDate.get(Calendar.DATE);
		if (year1 <= year2) {
			if (month1 <= month2) {
				if (day1 <= day2) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns the total price of the Transaction as a string
	 * 
	 * @return totalPrice
	 */
	public String getTotalPrice() {
		return "Total Price: " + totalPrice;
	}

	/**
	 * Returns the date of the Transaction as a Calendar type
	 * 
	 * @return date as a calendar
	 */
	public Calendar returnDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Transaction [totalPrice:=" + totalPrice + ", date=" + date + "]";
	}
}
