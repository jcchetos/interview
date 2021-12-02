package org.jccastro.clip.assesment.config;

import org.jccastro.clip.assesment.transaction.datastore.TransactionDataStoreFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author JCCastro
 *
 */
@Configuration
@ComponentScan("org.jccastro.clip.assesment")
public class AppConfig {

	@Bean
	public ServiceLocatorFactoryBean serviceLocatorBean() {
		ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
		bean.setServiceLocatorInterface(TransactionDataStoreFactory.class);
		return bean;
	}

}
