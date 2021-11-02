package edu.ics372.groupProject1.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Transaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance variables
	private int memberID;
	private String date1;
	private String date2;	//Possibly remove this second date?
	private String id;
	private static int transactionID;

	// Basic constructor method
	public Transaction(int memberID, String date1, String date2) {
		this.memberID = memberID;
		this.date1 = date1;
		this.date2 = date2;
		this.id = "T" + ++transactionID;
	}
	
	public void printTransaction() {
		// NOTE: A user may visit the store more then one times in a single day
		// print date on which the user visit for each visit
		// print the total price paid for each visit
		System.out.println("\nTransaction\n");
	}

	// Instance methods
	public boolean isValidMemberID() {
		/*
		 * iterate through memberList check if one
		 * of the member's id matches the memberID given
		 */
		return false;
	}

	public boolean date1BeforeDate2() {
		/*
		 * mm/dd/yyyy check if date1 is not after(the month, day, or year is less then
		 * date2) second date
		 */
		int day1 = Integer.parseInt(date1.substring(3, 5));
		int month1 = Integer.parseInt(date1.substring(0, 2));
		int year1 = Integer.parseInt(date1.substring(6, 10));
		int day2 = Integer.parseInt(date2.substring(3, 5));
		int month2 = Integer.parseInt(date2.substring(0, 2));
		int year2 = Integer.parseInt(date2.substring(6, 10));

		if (year1 <= year2) {
			if (month1 <= month2) {
				if (day1 <= day2) {
					// System.out.println("The dates are in range:)");
					return true;
				} else {
					// System.out.println("The day is no good:(");
				}
			} else {
				// System.out.println("The month is no good:(");
			}
		} else {
			// System.out.println("The year is no good:(");
		}
		return false;
	}

	// Getter & Setter plus toString() methods
	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	@Override
	public String toString() {
		return "Transaction [memberID=" + memberID + ", date1=" + date1 + ", date2=" + date2 + "]";
	}
	
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(transactionID);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		transactionID = (int) input.readObject();
	}

}
