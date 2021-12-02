import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.jccastro.clip.assesment.config.AppConfig;
import org.jccastro.clip.assesment.model.Transaction;
import org.jccastro.clip.assesment.model.TransactionReport;
import org.jccastro.clip.assesment.transaction.TransactionManager;
import org.jccastro.clip.assesment.transaction.TransactionService;
import org.jccastro.clip.assesment.transaction.datastore.TransactionDataStore;
import org.jccastro.clip.assesment.transaction.datastore.TransactionDataStoreFactory;
import org.jccastro.clip.assesment.transaction.datastore.filesystem.FileSystemPersistence;
import org.jccastro.clip.assesment.transaction.datastore.filesystem.TransactionFileSystemImplementation;
import org.jccastro.clip.assesment.transaction.exception.TransactionNotFoundException;
import org.jccastro.clip.assesment.transaction.exception.UserTransactionsNotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author JCCastro
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class TransactionFileSystemTest {

	@Autowired
	private FileSystemPersistence fileSystemPersistence;

	@Autowired
	private TransactionFileSystemImplementation transactionFileSystem;

	@Autowired
	private TransactionDataStoreFactory transactionDataStoreFactory;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionManager transactionManager;

	Logger log = (Logger) LoggerFactory.getLogger(TransactionFileSystemTest.class);

	@Test
	public void writeLine() throws URISyntaxException, IOException {

		fileSystemPersistence.addLine("amount: 112.80, description: Felipe Tacos, date:2018-12-31", "tello");
	}

	@Test
	public void getLines() throws URISyntaxException, IOException {

		fileSystemPersistence.getLines("jccastro").forEach(line -> log.trace(line));

	}

	@Test
	public void saveTransaction() {

		Transaction tx = new Transaction();
		tx.setAmount(3412.23);
		tx.setDescription("Tacos MC");
		tx.setDate(new Date());
		tx.setId("rtyukjewehjkl");
		tx.setUser("jccastro");

		transactionFileSystem.saveTransaction(tx);
	}

	@Test
	public void getTransactionFileSystem() {
		TransactionDataStore fileSystemDataStore = transactionDataStoreFactory
				.getTransactionDataStore("transactionFileSystemImplementation");

		assertNotNull(fileSystemDataStore);
	}

	@Test
	public void addTransaction() {
		Transaction tx = transactionService.addTransaction(183.43, "StarWars FCCDS", new Date(), "tello");
		assertNotNull(tx);
	}

	@Ignore
	@Test
	public void getTransaction() throws TransactionNotFoundException {
		Transaction tx = null;
		tx = transactionService.getTransaction("jccastro", "4564ewr564543213.63");
		assertNotNull(tx);
	}

	@Test
	public void getTransactions() throws UserTransactionsNotFoundException {
		List<Transaction> txs = transactionService.listUserTransactions("jccastro");
		assertNotNull(txs);
	}

	@Test
	public void sumUserTransactions() {
		TransactionReport report = transactionService.sumUserTransactions("jccastro");
		assertNotNull(report);
	}

	@Test
	public void addTransactionFromLine() {
		String tx = transactionManager.addTransaction("jccastro",
				"{ \"amount\": 77.80, \"description\": \"JC Tacos\", \"date\":\"2018-12-31\"}");
		assertTrue(tx.length() > 0);
	}

	@Ignore
	@Test
	public void showTransaction() {
		String tx = transactionManager.showTransaction("jccastro", "16f907d1-490e-45d1-a2ae-ea377ffb8803");
		assertTrue(tx.contains("16f907d1-490e-45d1-a2ae-ea377ffb8803"));

	}

	@Test
	public void listTransactions() {
		String txs = transactionManager.listTransactions("jccastro");
		assertTrue(txs.length() > 0);
	}

	@Test
	public void sumTransactions() {
		String txs = transactionManager.sumTransactions("jccastro");
		assertTrue(txs.length() > 0);
	}
}
