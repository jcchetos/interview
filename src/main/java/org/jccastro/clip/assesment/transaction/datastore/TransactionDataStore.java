package org.jccastro.clip.assesment.transaction.datastore;

import java.util.List;

import org.jccastro.clip.assesment.model.Transaction;

/**
 * Interface for the basic transaction operations
 * @author JCCastro
 *
 */
public interface TransactionDataStore {

	/**
	 * 
	 * @param transaction
	 */
	public void saveTransaction(Transaction transaction);
	
	/**
	 * 
	 * @param userId
	 * @param transactionId
	 * @return
	 */
	public Transaction getTransaction(String userId, String transactionId);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Transaction> getUserTransactions(String userId);

}
