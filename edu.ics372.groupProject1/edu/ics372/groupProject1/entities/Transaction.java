package edu.ics372.groupProject1.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {
	// Instance variables
	private static final long serialVersionUID = 1L;
	private String memberID;
	private Calendar date;
	private double total;

	public Transaction(String memberID, double total) {
		this.memberID = memberID;
		this.total = total;
		date = new GregorianCalendar();
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

	private String setDateToString(Calendar calDate1) {
		return calDate1.get(Calendar.MONTH) + "/" + calDate1.get(Calendar.DATE) + "/" + calDate1.get(Calendar.YEAR);
	}

	// Getter & Setter plus toString() methods
	public String getMemberID() {
		return memberID;
	}

	public String getTotal() {
		return "Total: " + total;
	}

	public String getDate() {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	@Override
	public String toString() {
		return "Transaction [memberID=" + memberID + ", date=" + date + "]";
	}
	
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(transactionID);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		transactionID = (int) input.readObject();
	}

}
