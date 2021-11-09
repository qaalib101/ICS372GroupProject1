package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {
	// Instance variables
	private static final long serialVersionUID = 1L;
	private Calendar date;
	private double totalPrice;
	private double transactionTotal;
	// private String date2; // Possibly remove this second date?
	GregorianCalendar todaysDate;

	public Transaction(int memberID, double transactionTotal) {
		this.totalPrice = totalPrice;
		date = new GregorianCalendar();
		todaysDate.setTime(new Date());
		transactionTotal = transactionTotal;
//		this.date1 = date1;
		// this.date2 = date2;
		this.id = "T" + ++transactionID;
	}

	/**
	 * Checks whether this transaction is on the given date
	 * 
	 * @param date The date for which transactions are being sought
	 * @return true iff the dates match
	 */
	public boolean datesInRange(Calendar calDate1, Calendar calDate2) {
		String date1 = setDateToString(calDate1);
		String date2 = setDateToString(calDate1);
		int month1 = Integer.parseInt(date1.substring(0, 1));
		int day1 = Integer.parseInt(date1.substring(3, 4));
		int year1 = Integer.parseInt(date1.substring(6, 7));
		int month2 = Integer.parseInt(date2.substring(0, 1));
		int day2 = Integer.parseInt(date2.substring(3, 4));
		int year2 = Integer.parseInt(date2.substring(6, 7));
		if (year1 <= year2) {
			if (month1 <= month2) {
				if (day1 <= day2) {
					return true;
				}
			}
		}
		return false;
	}

	private String setDateToString(Calendar date) {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	// Getter & Setter plus toString() methods
	public String getTotalPrice() {
		return "TotalPrice: " + totalPrice;
	}

	public String getDate() {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	@Override
	public String toString() {
		return "Transaction [totalPrice: " + totalPrice + ", date=" + date + "]";
	}
}
