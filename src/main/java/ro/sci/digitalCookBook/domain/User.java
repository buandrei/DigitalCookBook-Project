package ro.sci.digitalCookBook.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Objects;


public class User extends AbstractModel {

	//    @NotEmpty(message = "{username.notempty}")
	private String userName;

	//    @NotEmpty(message = "{email.notempty}")
	private String email;

	//    @NotEmpty(message = "{lastname.notempty}")
	private String lastName;

	//    @NotEmpty(message = "{firstname.notempty}")
	private String firstName;

	//    @NotEmpty(message = "{isbucatar.notempty}")
	private String isBucatar;

	//    @NotEmpty(message = "{passwprd.notempty}")
	private String password;

	private String active;

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIsBucatar() {
		return isBucatar;
	}

	public void setIsBucatar(String isBucatar) {
		this.isBucatar = isBucatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "App_user{" +
				"userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", isBucatar='" + isBucatar + '\'' +
				", password='" + password + '\'' +
				", active='" + active + '\'' +
				", role='" + role + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User app_user = (User) o;
		return Objects.equals(userName, app_user.userName) &&
				Objects.equals(email, app_user.email) &&
				Objects.equals(lastName, app_user.lastName) &&
				Objects.equals(firstName, app_user.firstName) &&
				Objects.equals(isBucatar, app_user.isBucatar) &&
				Objects.equals(password, app_user.password) &&
				Objects.equals(active, app_user.active) &&
				Objects.equals(role, app_user.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, email, lastName, firstName, isBucatar, password, active, role);
	}
}
