package org.jccastro.clip.assesment.transaction.exception;
/**
 * 
 * @author JCCastro
 *
 */
public class TransactionNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		
		return "Transaction was not found";
	}

}
