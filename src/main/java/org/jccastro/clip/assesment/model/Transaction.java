
package org.jccastro.clip.assesment.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author JCCastro Represents a transaction Object
 *
 */
@JsonPropertyOrder({ "id", "amount", "description", "date", "user" })
public class Transaction {

	private String id;
	private Double amount;
	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	private String userId;

	/**
	 * @return the id
	 */
	@JsonProperty("transaction_id")
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param d
	 *            the amount to set
	 */
	public void setAmount(double d) {
		this.amount = d;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the user
	 */
	@JsonProperty("user_id")
	public String getUser() {
		return userId;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.userId = user;
	}

}
