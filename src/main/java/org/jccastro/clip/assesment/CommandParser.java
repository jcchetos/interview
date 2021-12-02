package org.jccastro.clip.assesment;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author JCCastro
 *
 */
public class CommandParser {

	private static Logger log = LoggerFactory.getLogger(CommandParser.class);

	private static final String LIST_COMMAND = "list";
	private static final String SUM_COMMAND = "sum";

	public static TransactionArguments getCommands(String[] args) {
		int numArgs = args.length;
		TransactionArguments arguments = new TransactionArguments();

		if (numArgs < 2) {
			log.error("Command is not valid");
		}

		if (numArgs == 2) {

			arguments.setUserId(args[0]);
			if (args[1].equals(LIST_COMMAND) || args[1].equals(SUM_COMMAND))
				arguments.setCommand(args[1]);
			else {
				arguments.setTransactionId(args[1]);
				arguments.setCommand("");
			}
		}

		if (numArgs > 2) {

			arguments.setUserId(args[0]);
			arguments.setCommand(args[1]);
			arguments.setTransaction(getTransaction(args));

		}
		return arguments;

	}

	public static String getTransaction(String[] args) {

		String[] argsTx = Arrays.copyOfRange(args, 2, args.length);
		String txString = Arrays.asList(argsTx).stream().map(Object::toString).collect(Collectors.joining(" "));
		txString = txString.replace('”', '"');
		txString = txString.replace('“', '"');
		return txString;

	}
}
