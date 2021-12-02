package org.jccastro.clip.assesment.transaction;

import java.io.IOException;
import java.util.stream.Collectors;

import org.jccastro.clip.assesment.model.Transaction;
import org.jccastro.clip.assesment.model.TransactionTranslateUtil;
import org.jccastro.clip.assesment.transaction.exception.TransactionNotFoundException;
import org.jccastro.clip.assesment.transaction.exception.UserTransactionsNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * @author JCCastro
 *
 */
@Component("transactionManager")
public class TransactionManager {

	private static Logger log = LoggerFactory.getLogger(TransactionManager.class);

	@Autowired
	private TransactionService transactionService;

	public String addTransaction(String userId, String jsonLine) {

		String txString = "";
		try {
			log.debug("json " + jsonLine);
			Transaction tx = TransactionTranslateUtil.translateLine(jsonLine);
			txString = TransactionTranslateUtil.translateTransaction(
					transactionService.addTransaction(tx.getAmount(), tx.getDescription(), tx.getDate(), userId));
		} catch (IOException e) {
			log.error("Transaction argument is not in JSON valid format");
		}

		log.info(txString);

		return txString;

	}

	public String showTransaction(String userId, String transactionId) {

		String txString = "";
		try {
			txString = TransactionTranslateUtil
					.translateTransaction(transactionService.getTransaction(userId, transactionId));
		} catch (JsonProcessingException e) {
			log.error("Transaction in persistent data not in JSON valid format");
		} catch (TransactionNotFoundException tnfe) {
			log.error(tnfe.getMessage());
		}

		log.info(txString);
		return txString;

	}

	public String listTransactions(String userId) {

		String transactions = "";
		try {
			transactions = transactionService.listUserTransactions(userId).stream().map(tx -> {
				try {
					return TransactionTranslateUtil.translateTransaction(tx);
				} catch (JsonProcessingException e) {

					log.error("Transaction in persistent data not in JSON valid format");
				}
				return "";
			}).collect(Collectors.joining("," + System.lineSeparator()));
		} catch (UserTransactionsNotFoundException utnfe) {
			log.error(utnfe.getMessage());
		}

		log.info(transactions);
		
		return transactions;

	}

	public String sumTransactions(String userId) {
		String reportString = TransactionTranslateUtil.translateReport(transactionService.sumUserTransactions(userId));
		log.info(reportString);
		
		return reportString;

	}

}
