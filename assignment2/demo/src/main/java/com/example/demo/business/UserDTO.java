package assignment2.sd.TUCN_app_2.business;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	
	private String userName;
	private String idNumber;
	private String cnp;
	private String address;
	private String firstName;
	private String lastName;
	
	public UserDTO(String userName, String idNumber, String cnp, String address, String firstName, String lastName) {
		this.userName = userName;
		this.idNumber = idNumber;
		this.cnp = cnp;
		this.address =  address;
		this.firstName = firstName;
		this.lastName =  lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
}
