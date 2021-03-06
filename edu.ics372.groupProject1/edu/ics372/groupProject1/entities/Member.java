package edu.ics372.groupProject1.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.groupProject1.facade.Result;
import edu.ics372.groupProject1.iterators.FilteredIterator;
import edu.ics372.groupProject1.iterators.SafeTransactionIterator;

/**
 * Class that represents a member in a grocery store
 * 
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phone;
	private Calendar date;
	private double fee;
	private String id;
	private static final String MEMBER_STRING = "M";
	private static int idCounter;
	private Cart cart;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");

	private List<Transaction> transactions = new LinkedList<Transaction>();

	/**
	 * Creates a single member
	 * 
	 * @param name    name of the member
	 * @param address address of the member
	 * @param phone   phone number of the member
	 * @param date    date the member joins
	 * @param fee     amount member pays as fee
	 */
	public Member(String name, String address, String phone, Calendar date, double fee) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.date = date;
		this.fee = fee;
		this.cart = new Cart();
		id = MEMBER_STRING + ++idCounter;
	}

	/**
	 * returns a member's cart
	 * 
	 * @return cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * Returns a list of all the member's transactions
	 * 
	 * @return transactions
	 */
	public List<Transaction> transactionList() {
		return this.transactions;
	}

	/**
	 * Returns an iterator for transactions between a start date and end date
	 * 
	 * @param startDate
	 * @param endDate
	 * @return a safe transaction iterator
	 */
	public Iterator<Result> getTransactionsBetweenDates(Calendar startDate, Calendar endDate) {
		return new SafeTransactionIterator(new FilteredIterator(getTransactionsInRange(startDate, endDate),
				transaction -> transaction.datesInRange(startDate, endDate)));
	}

	/**
	 * Returns a list of transactions within the start and end date range
	 * 
	 * @param startDate
	 * @param endDate
	 * @return Iterator<Transaction>
	 */
	public Iterator<Transaction> getTransactionsInRange(Calendar startDate, Calendar endDate) {
		List<Transaction> subTransactionsList = new LinkedList<Transaction>();
		for (int index = 0; index < transactions.size(); index++) {
			Transaction transaction = transactions.get(index);
			// if transaction date is in range of given startDate and endDate, then add
			if (transaction.datesInRange(startDate, transaction.returnDate())
					&& transaction.datesInRange(transaction.returnDate(), endDate)) {
				subTransactionsList.add(transaction);
			}
		}
		return subTransactionsList.iterator();
	}

	/**
	 * Adds a given transaction to the transactions list
	 * 
	 * @param transaction that is added to transaction list
	 */
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	/**
	 * returns a list of transactions
	 * 
	 * @return transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;

	}

	/**
	 * Getter for name
	 *
	 * @return member name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for phone number
	 * 
	 * @return phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Getter for address
	 * 
	 * @return member address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Getter for date
	 * 
	 * @return date joined
	 */
	public String getDate() {
		return dateFormat.format(date);
	}

	/**
	 * Getter for fee
	 * 
	 * @return fee
	 */
	public String getFee() {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(fee);
	}

	/**
	 * Getter for id
	 * 
	 * @return member id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for name
	 * 
	 * @param newName member's new name
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Setter for address
	 * 
	 * @param newName member's new address
	 */
	public void setAddress(String newAddress) {
		address = newAddress;
	}

	/**
	 * Setter for phone
	 * 
	 * @param newName member's new phone
	 */
	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	/**
	 * Setter for date
	 * 
	 * @param newName member's new address
	 */
	public void setDate(Calendar newDate) {
		date = newDate;
	}

	/**
	 * Setter for phone
	 * 
	 * @param newName member's new phone
	 */
	public void setFee(Double newFee) {
		fee = newFee;
	}

	@Override
	public String toString() {
		String string = "Member name: " + name + "\naddress: " + address + "\nphone number: " + phone
				+ "\ndate joined: " + date + "\nfee paid: " + fee + "\nID: " + id;
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Member other = (Member) object;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}

}
