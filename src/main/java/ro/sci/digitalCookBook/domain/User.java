package ro.sci.digitalCookBook.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class User extends AbstractModel {

	private String userName;
	private String email;
	private String nume;
	private String prenume;
	private String isBucatar;
	private String parola;
	private String picturePath;
	private boolean active;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date addDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date lastLoginDate;

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

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getIsBucatar() {
		return isBucatar;
	}

	public void setIsBucatar(String isBucatar) {
		this.isBucatar = isBucatar;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", nume='" + nume + '\'' +
				", prenume='" + prenume + '\'' +
				", isBucatar='" + isBucatar + '\'' +
				", parola='" + parola + '\'' +
				", picturePath='" + picturePath + '\'' +
				", active=" + active +
				", addDate=" + addDate +
				", lastLoginDate=" + lastLoginDate +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return active == user.active &&
				Objects.equals(userName, user.userName) &&
				Objects.equals(email, user.email) &&
				Objects.equals(nume, user.nume) &&
				Objects.equals(prenume, user.prenume) &&
				Objects.equals(isBucatar, user.isBucatar) &&
				Objects.equals(parola, user.parola) &&
				Objects.equals(picturePath, user.picturePath) &&
				Objects.equals(addDate, user.addDate) &&
				Objects.equals(lastLoginDate, user.lastLoginDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, email, nume, prenume, isBucatar, parola, picturePath, active, addDate, lastLoginDate);
	}
}
