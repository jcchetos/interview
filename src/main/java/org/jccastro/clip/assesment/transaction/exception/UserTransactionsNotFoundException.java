package org.jccastro.clip.assesment.transaction.exception;

/**
 * 
 * @author JCCastro
 *
 */
public class UserTransactionsNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserTransactionsNotFoundException(String userId) {
		super();
		this.userId = userId;
	}

	private String userId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Not found transactions for user_id: ".concat(userId);
	}

}
