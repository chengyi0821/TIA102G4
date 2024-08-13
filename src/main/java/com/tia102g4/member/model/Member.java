package com.tia102g4.member.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "member")
public class Member implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId; // 會員ID

	@Column(name = "regis_date")
	private Timestamp regisDate; // 註冊時間

	@Expose
	@Column(name = "name")
	private String name; // 會員姓名

	@Column(name = "account", unique = true)
	private String account; // 帳號

	@Column(name = "password")
	private String password; // 密碼

	@Column(name = "email", unique = true)
	private String email; // 電子信箱

	@Column(name = "gender", nullable = false)
	private Boolean gender; // 性別: True: 男 False: 女

	@Column(name = "mobile_no", unique = true)
	private String mobileNo; // 手機號碼

	@Column(name = "sticker", columnDefinition = "LONGBLOB")
	private byte[] sticker; // 頭貼

	@Column(name = "acc_status", nullable = false)
	private Boolean accStatus; // 帳號啟動狀態: 0.未啟用 1.已啟用

	@Column(name = "reset_token")
	private String resetToken;

	@Column(name = "token_created_at")
	private Timestamp tokenCreatedAt;

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

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Timestamp getTokenCreatedAt() {
		return tokenCreatedAt;
	}

	public void setTokenCreatedAt(Timestamp tokenCreatedAt) {
		this.tokenCreatedAt = tokenCreatedAt;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", regisDate=" + regisDate + ", name=" + name + ", account=" + account
				+ ", password=" + password + ", email=" + email + ", gender=" + gender + ", mobileNo=" + mobileNo
				+ ", sticker=" + Arrays.toString(sticker) + ", accStatus=" + accStatus + ", resetToken=" + resetToken
				+ ", tokenCreatedAt=" + tokenCreatedAt + "]";
	}

//    // 一對多關係：一個會員可以有多個訂單
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<MyOrder> myOrders;
//
//    // 一對多關係：一個會員可以被列為多個黑名單
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<BlackList> blackLists;
//
//    // 一對多關係：一個會員可以產生多個評論
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<Comment> comments;
//
//    // 一對多關係：一個會員可以參加多個活動
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<Event> events;
//
//    // 一對多關係：一個會員可以加入多個等候室
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<Room> rooms;
//    
//    // 一對多關係：一個會員可以使用多次客服
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<CustomerService> customerServices;
//    
//    // 一對多關係：一個會員可以有多個收藏餐廳
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<FavoriteRestaurant> favoriteRestaurants;

//		public Set<MyOrder> getMyOrders() {
//			return myOrders;
//		}
//
//		public void setMyOrders(Set<MyOrder> myOrders) {
//			this.myOrders = myOrders;
//		}
//
//		public Set<BlackList> getBlackLists() {
//			return blackLists;
//		}
//
//		public void setBlackLists(Set<BlackList> blackLists) {
//			this.blackLists = blackLists;
//		}
//
//		public Set<Comment> getComments() {
//			return comments;
//		}
//
//		public void setComments(Set<Comment> comments) {
//			this.comments = comments;
//		}
//
//		public Set<Event> getEvents() {
//			return events;
//		}
//
//		public void setEvents(Set<Event> events) {
//			this.events = events;
//		}
//
//		public Set<Room> getRooms() {
//			return rooms;
//		}
//
//		public void setRooms(Set<Room> rooms) {
//			this.rooms = rooms;
//		}
//
//		public Set<CustomerService> getCustomerServices() {
//			return customerServices;
//		}
//
//		public void setCustomerServices(Set<CustomerService> customerServices) {
//			this.customerServices = customerServices;
//		}
//
//		public Set<FavoriteRestaurant> getFavoriteRestaurants() {
//			return favoriteRestaurants;
//		}
//
//		public void setFavoriteRestaurants(Set<FavoriteRestaurant> favoriteRestaurants) {
//			this.favoriteRestaurants = favoriteRestaurants;
//		}

}