package edu.ics372.groupProject1.entities;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {
	// Instance variables
	private static final long serialVersionUID = 1L;
	private int memberID;
	private String date1;
	private double transactionTotal;
	GregorianCalendar todaysDate;
	private String id;
	private static int transactionID;

	// Basic constructor method
	public Transaction(int memberID, double transactionTotal) {
		this.memberID = memberID;
		todaysDate.setTime(new Date());
		this.transactionTotal = transactionTotal;
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

	public GregorianCalendar getTodaysDate() {
		return todaysDate;
	}

	public double getTransactionTotal() {
		return transactionTotal;
	}

	public void printTransaction() {
		// NOTE: A user may visit the store more then one times in a single day
		// print date on which the user visit for each visit
		// print the total price paid for each visit
		System.out.println("\nTransaction\n");
	}
//	public String getDate2() {
//		return date2;
//	}

//	public void setDate2(String date2) {
//		this.date2 = date2;
//	}

	// commented out date2 for toString
	@Override
	public String toString() {
		return "Transaction [memberID=" + memberID + ", date1=" + date1 + "]";
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(transactionID);
	}
}
