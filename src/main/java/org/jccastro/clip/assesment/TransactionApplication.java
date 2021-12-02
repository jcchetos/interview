package org.jccastro.clip.assesment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author JCCastro
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class TransactionApplication implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(TransactionApplication.class);

	@Autowired
	TransactionExecutor transactionExecutor;

	public static void main(String[] args) {

		SpringApplication.run(TransactionApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("EXECUTING : command line runner");
		TransactionArguments arguments = CommandParser.getCommands(args);
		transactionExecutor.execute(arguments);

	}

}
