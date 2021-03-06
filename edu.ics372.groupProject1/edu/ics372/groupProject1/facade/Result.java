package edu.ics372.groupProject1.facade;

/**
 * This class is used for returning many results of the GroceryStore system's
 * business logic to user interface.
 * 
 * @author Qaalib Farah, Ayden Sinn, Nate Goetsch, Leng Vang, John Quinlan
 *
 */
public class Result extends DataTransfer {
	public static final int PRODUCT_NOT_FOUND = 1;
	public static final int NO_SUCH_MEMBER = 2;
	public static final int NO_OUTSTANDING_ORDER = 3;
	public static final int OPERATION_COMPLETED = 4;
	public static final int OPERATION_FAILED = 5;
	public static final int DATES_NOT_IN_RANGE = 6;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
