package com.tia102g4.member.model;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "member")
public class Member {
	
	
	
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
    
	@Column(name = "regis_date", insertable = false, updatable = false)
	private Timestamp regisDate = new Timestamp(System.currentTimeMillis());
	
	@Column(name = "name")
	private String name;
	
	@Column(name="account")
	private String account;
	
	@Column(name = "password")
	private String password;
	
	@Column(name ="email")
	private String email;
	
	@Column(name = "gender")
	private Boolean gender;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "sticker", columnDefinition = "LONGBLOB")
	@Lob
	private byte[] sticker;
	
	
	
	@Column(name = "acc_status", nullable = false)
	private Boolean accStatus = false;
	
	public Member() {
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Timestamp getRegisDate() {
		return regisDate;
	}

	public void setRegisDate(Timestamp regisDate) {
		this.regisDate = regisDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public byte[] getSticker() {
		return sticker;
	}
	
	public void setSticker(byte[] sticker) {
		this.sticker = sticker;
	}

	public Boolean getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Boolean accStatus) {
		this.accStatus = accStatus;
	}
	


	@Override
	public String toString() {
		    return "Member {" +
		           "memberId=" + memberId + 
		           ", regisDate=" + regisDate + 
		           ", name='" + name + '\'' + 
		           ", account='" + account + '\'' + 
		           ", password='" + password + '\'' + 
		           ", email='" + email + '\'' + 
		           ", gender=" + gender + 
		           ", mobileNo='" + mobileNo + '\'' + 
		           ", sticker=" + Arrays.toString(sticker) + 
		           ", accStatus=" + accStatus + 
		           '}';
		}

	
	}