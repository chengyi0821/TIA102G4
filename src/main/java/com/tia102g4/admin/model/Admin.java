package com.tia102g4.admin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "admin")
public class Admin {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId; // 後台人員ID

    @Column(name = "name", nullable = false)
    private String name; // 名稱

    @Column(name = "account", nullable = false, unique = true)
    private String account; // 帳號

    @Column(name = "password", nullable = false, columnDefinition = "default 'chugether'")
    private String password; // 密碼

    @Column(name = "permission", nullable = false)
    private Integer permission; // 權限: 1.管理員 2.一般

    @Column(name = "regis_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regisDate; // 註冊日期

//    // 一對多關係：一個後台人員可以處理多個客服
//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
//    private Set<CustomerService> customerServices;

    
    
	public Admin() {
		super();
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Date getRegisDate() {
		return regisDate;
	}

	public void setRegisDate(Date regisDate) {
		this.regisDate = regisDate;
	}

//	public Set<CustomerService> getCustomerServices() {
//		return customerServices;
//	}
//
//	public void setCustomerServices(Set<CustomerService> customerServices) {
//		this.customerServices = customerServices;
//	}

	
	
	@Override
	public String toString() {
	    return "Admin {" +
	           "adminId=" + adminId + 
	           ", name='" + name + '\'' + 
	           ", account='" + account + '\'' + 
	           ", password='" + password + '\'' + 
	           ", permission=" + permission + 
	           ", regisDate=" + regisDate + 
	           '}';
	}

}

