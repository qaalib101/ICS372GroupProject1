package edu.ics372.groupProject1.facade;

public class Request extends DataTransfer {
	private static Request request;
	private Calendar date, startDate, endDate;
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

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}
