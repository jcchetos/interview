package org.jccastro.clip.assesment.transaction.datastore.filesystem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jccastro.clip.assesment.model.Transaction;
import org.jccastro.clip.assesment.model.TransactionTranslateUtil;
import org.jccastro.clip.assesment.transaction.TransactionManager;
import org.jccastro.clip.assesment.transaction.datastore.TransactionDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Data Store implementation to persist data transaction on file systems
 * @author JCCASTRO
 *
 */
@Component
public class TransactionFileSystemImplementation implements TransactionDataStore {

	private static Logger log = LoggerFactory.getLogger(TransactionManager.class);

	@Autowired
	private FileSystemPersistence fileSystemPersistence;

	@Override
	public void saveTransaction(Transaction transaction) {

		ObjectMapper objectMapper = new ObjectMapper();
		String transactionLine="";

		try {
			transactionLine = objectMapper.writeValueAsString(transaction);
			fileSystemPersistence.addLine(transactionLine, transaction.getUser());
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		} catch (URISyntaxException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public Transaction getTransaction(String userId, String transactionId) {

		return getUserTransactions(userId).stream().filter(tx -> tx.getId().equals(transactionId)).findFirst()
				.orElse(null);

	}

	@Override
	public List<Transaction> getUserTransactions(String userId) {

		Stream<String> lines = Stream.empty();
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			lines = fileSystemPersistence.getLines(userId);
		} catch (URISyntaxException | IOException e) {
			log.error("Data file is not available ", e.getCause());

		}

		if (lines != null) {
			transactions = lines.map(line -> {
				try {
					return TransactionTranslateUtil.translateLine(line);
				} catch (IOException e) {
					log.error("Transaction argument not in a JSON valid format");
				}
				return null;
			}).collect(Collectors.toList());
		}

		return transactions;

	}

}
