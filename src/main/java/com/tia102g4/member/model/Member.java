package com.tia102g4.member.model;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.tia102g4.blacklist.model.BlackList;
import com.tia102g4.favorite.model.Favorite;
import com.tia102g4.myorder.model.MyOrder;



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

	    @Column(name = "name")
	    @Expose
//	    @NotEmpty(message="會員姓名: 請勿空白")
//	    @Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間")
	    private String name; // 會員姓名

	    @Column(name = "account", unique = true)
//	    @NotEmpty(message="會員帳號: 請勿空白")
//	    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "會員帳號: 請輸入符合電子信箱的格式")
	    private String account; // 帳號

	    @Column(name = "password")
//	    @NotEmpty(message="會員密碼: 請勿空白")
//	    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,15}$", message = "會員密碼: 只能是中、英文字母的組合 , 至少必須有1個中文與1個英文字母 , 且長度必需在6到15之間")
	    private String password; // 密碼

	    @Column(name = "email", unique = true)
//	    @NotEmpty(message="電子信箱: 請勿空白")
//	    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "電子信箱: 請輸入符合電子信箱的格式")
	    private String email; // 電子信箱

	    @Column(name = "gender", nullable = false)
//	    @NotEmpty(message="會員性別: 請勿空白")
	    private Boolean gender; // 性別: True: 男  False: 女

	    @Column(name = "mobile_no", unique = true)
//	    @NotEmpty(message="手機號碼: 請勿空白")
//	    @Pattern(regexp = "^09\\d{8}$", message = "手機號碼: 請輸入09開頭且不包含任何符號的格式")
	    private String mobileNo; // 手機號碼

	    @Column(name = "sticker", columnDefinition = "LONGBLOB")
//		@NotEmpty(message="會員照片: 請上傳照片")
	    @Lob
	    private byte[] sticker; // 頭貼

	    @Column(name = "acc_status", nullable = false)
	    private Boolean accStatus; // 帳號啟動狀態: 0.未啟用 1.已啟用 
	    

	    
	    // 一對多關係：一個會員可以有多個訂單
	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	    private Set<MyOrder> myOrders;

	    // 一對多關係：一個會員可以被列為多個黑名單
	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	    private Set<BlackList> blackLists;
//
//	    // 一對多關係：一個會員可以產生多個評論
//	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//	    private Set<Comment> comments;
//
//	    // 一對多關係：一個會員可以參加多個活動
//	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//	    private Set<Event> events;
//
//	    // 一對多關係：一個會員可以加入多個等候室
//	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//	    private Set<Room> rooms;
//	    
//	    // 一對多關係：一個會員可以使用多次客服
//	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//	    private Set<CustomerService> customerServices;
//	    
	    // 一對多關係：一個會員可以有多個收藏餐廳
	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	    private Set<Favorite> favorite;

	    
	    
		public Member() {
		}

		public Long getMemberId() {
			return memberId;
		}

		public void setMemberId(Long memberId) {
			this.memberId = memberId;
		}

		public Date getRegisDate() {
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

