package org.jccastro.clip.assesment.transaction;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jccastro.clip.assesment.model.Transaction;
import org.jccastro.clip.assesment.model.TransactionReport;
import org.jccastro.clip.assesment.transaction.datastore.TransactionDataStore;
import org.jccastro.clip.assesment.transaction.exception.TransactionNotFoundException;
import org.jccastro.clip.assesment.transaction.exception.UserTransactionsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provide basic transaction services
 * @author JCCastro
 *
 */
@Service("transactionService")
public class TransactionService {

	@Autowired
	private IDService idService;

	@Resource(name = "transactionFileSystemImplementation")
	TransactionDataStore dataStore;

	/**
	 * Add a transaction with the given parameters
	 * @param amount
	 * @param description
	 * @param date
	 * @param userId
	 * @return
	 */
	public Transaction addTransaction(Double amount, String description, Date date, String userId) {

		Transaction tx = createTransaction(amount, description, date, userId);
		dataStore.saveTransaction(tx);
		return tx;

	}
/**
 *  obtain a transaction based on the transactionId and userId
 * @param userId
 * @param transactionId
 * @return
 * @throws TransactionNotFoundException
 */
	public Transaction getTransaction(String userId, String transactionId) throws TransactionNotFoundException {
		Transaction tx = dataStore.getTransaction(userId, transactionId);

		if (tx == null)
			throw new TransactionNotFoundException();
		return tx;

	}
/**
 *  Obtain all the transaction for the specific user
 * @param userId
 * @return
 * @throws UserTransactionsNotFoundException
 */
	public List<Transaction> listUserTransactions(String userId) throws UserTransactionsNotFoundException {
		List<Transaction> transactions = dataStore.getUserTransactions(userId);

		if (transactions == null || transactions.isEmpty()) {
			throw new UserTransactionsNotFoundException(userId);

		}
		return transactions;
	}
/**
 *  Obtain and sum all the transaction for the specific user
 * @param userId
 * @return
 */
	public TransactionReport sumUserTransactions(String userId) {

		Double amountSum = dataStore.getUserTransactions(userId).stream().mapToDouble(tx -> tx.getAmount()).sum();
		return new TransactionReport(userId, amountSum);

	}
/**
 * Creates a transaction object
 * @param amount
 * @param description
 * @param date
 * @param userId
 * @return
 */
	private Transaction createTransaction(Double amount, String description, Date date, String userId) {
		Transaction tx = new Transaction();
		tx.setId(idService.generateID());
		tx.setAmount(amount);
		tx.setDescription(description);
		tx.setDate(date);
		tx.setUser(userId);
		return tx;
	}

}
