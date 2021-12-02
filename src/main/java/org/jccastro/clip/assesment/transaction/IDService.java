package org.jccastro.clip.assesment.transaction;

import java.util.UUID;

import org.springframework.stereotype.Component;
/**
 * Component to create Unique Ids
 * @author JCCastro
 *
 */
@Component ("idService")
public class IDService {

	/**
	 *  Generates ID
	 */
	public String generateID() {
		return UUID.randomUUID().toString();
	}
	
}
 