package edu.ics372.groupProject1.facade;

public class Request extends DataTransfer {
	private static Request request;
	private Calendar date, date1, date2;
	private double total;
	
	private Request() {

	}

	/**
	 * Returns the only instance of the class.
	 * 
	 * @return the only instance
	 */
	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}
	
	/*
	 * Store more helper methods for printing out transactions
	 * 
	 * Such as boolean checks to verify the date range, and setting date1 & date2.
	 */

	public Calendar getDate() {
		return date;
	}

	public Calendar getDate1() {
		return date1;
	}

	public void setDate1(Calendar date1) {
		this.date1 = date1;
	}

	public Calendar getDate2() {
		return date2;
	}

	public void setDate2(Calendar date2) {
		this.date2 = date2;
	}

	public double getTotal() {
		return total;
	}
}
