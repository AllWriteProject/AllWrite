package com.AW.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="userdtls")
public class UserDtls {

	@Id
	@SequenceGenerator(name="seq1", sequenceName="seq1", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq1")
	private int id;
	@Column(name = "user_name")
	private String name;
	private String email;
	private String gender;
	private String phonenumber;
	private String password;
	private String about;
	private String role;
	public UserDtls() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDtls(int id, String name, String email, String gender, String phonenumber,
			String password, String about, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.password = password;
		this.about = about;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


}

