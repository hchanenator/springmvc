/**
 * 
 */
package com.packt.webstore.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author herb
 *
 */
public class ShippingDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1384374404218873836L;

	private String firstName;
	private String lastName;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date shippingDate;

	private Address shippingAddress;

	public ShippingDetail() {
		this.shippingAddress = new Address();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((shippingDate == null) ? 0 : shippingDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingDetail other = (ShippingDetail) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (shippingDate == null) {
			if (other.shippingDate != null)
				return false;
		} else if (!shippingDate.equals(other.shippingDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShippingDetail [name=" + firstName + " " + lastName + ", shippingDate=" + shippingDate + ", shippingAddress="
				+ shippingAddress + "]";
	}

}
