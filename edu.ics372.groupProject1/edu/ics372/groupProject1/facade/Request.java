package edu.ics372.groupProject1.facade;

public class Request extends DataTransfer {
	private static Request request;

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

}
