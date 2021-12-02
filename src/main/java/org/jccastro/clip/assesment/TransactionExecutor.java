package org.jccastro.clip.assesment;

import org.jccastro.clip.assesment.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Decides which commands executes
 * @author JCCastro
 *
 */
@Component("transactionExecutor")
public class TransactionExecutor {

	@Autowired
	private TransactionManager transactionManager;

	public void execute(TransactionArguments arguments) {
		
		switch (arguments.getCommand()) {

		case "list":
			transactionManager.listTransactions(arguments.getUserId());
			break;
		case "sum":
			transactionManager.sumTransactions(arguments.getUserId());
			break;
		case "add":
			transactionManager.addTransaction(arguments.getUserId(), arguments.getTransaction()); 
			break;
		default:
			transactionManager.showTransaction(arguments.getUserId(), arguments.getTransactionId());
			break;

		}
	}
}
