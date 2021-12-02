package org.jccastro.clip.assesment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * 
 * @author JCCastro
 *
 */
@JsonPropertyOrder({ "userId", "sum" })
public class TransactionReport {

	private String userId;
	private Double sum;

	public TransactionReport(String userId, Double sum) {
		this.userId = userId;
		this.sum = sum;
	}

	/**
	 * @return the userId
	 */
	@JsonProperty("user_id")
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the sum
	 */
	public Double getSum() {
		return sum;
	}

	/**
	 * @param sum
	 *            the sum to set
	 */
	public void setSum(Double sum) {
		this.sum = sum;
	}

}
