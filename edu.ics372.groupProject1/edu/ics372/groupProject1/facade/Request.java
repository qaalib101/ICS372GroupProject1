package edu.ics372.groupProject1.facade;

import java.util.Calendar;

/**
 * This class is a singleton. It is used for requesting many of the results of
 * the GroceryStore system's business logic to user interface.
 * 
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
public class Request extends DataTransfer {
	private static Request request;
	private Calendar date, startDate, endDate;

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

	/**
	 * Store more helper methods for printing out transactions
	 * 
	 * Such as boolean checks to verify the date range, and setting date1 & date2.
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * setter for date
	 * 
	 * @param date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * getter for startDate
	 * 
	 * @return startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * setter for startDate
	 * 
	 * @param startDate
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * getter for endDate
	 * 
	 * @return endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * setter for endDate
	 * 
	 * @param endDate
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}
