package org.jccastro.clip.assesment.transaction.datastore;
/**
 * 
 * @author JCCastro
 *
 */
public interface TransactionDataStoreFactory {
	TransactionDataStore getTransactionDataStore(String name);
}
