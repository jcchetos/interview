package org.jccastro.clip.assesment.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author JCCastro
 *
 */
public class TransactionTranslateUtil {

	public static Transaction translateLine(String jsonLine) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(jsonLine, Transaction.class);

	}

	public static String translateReport(TransactionReport report) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(report);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return "";
	}

	public static String translateTransaction(Transaction tx) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.writeValueAsString(tx);

	}

}
