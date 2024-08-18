package com.tia102g4.rest.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.LoginException;

import com.tia102g4.rest.dao.RestaurantDAO;
import com.tia102g4.rest.dao.RestaurantDAOImpl;
import com.tia102g4.rest.model.Restaurant;
import com.tia102g4.rest.to.RestaurantReqTO;
import com.tia102g4.rest.to.RestaurantResetPasswordReqTO;
import com.tia102g4.util.Base64Util;
import javax.mail.*;

public class RestaurantServiceImpl implements RestaurantService {

	private RestaurantDAO dao;
	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	private Base64Util base64Util = new Base64Util();
	private final String username = "andy0956808389@gmail.com";
	private final String password = "sggz rdmg pbqe wiqs";

	public RestaurantServiceImpl() {
		dao = new RestaurantDAOImpl();
	}

	@Override
	public void create(RestaurantReqTO reqTO) {
		Restaurant rest = setRest(reqTO);
		Long restType = reqTO.getRestType();
		dao.insert(rest, restType);
	}

	@Override
	public void update(Restaurant entity) {
		dao.update(entity);
	}

	public void updateForRest(RestaurantReqTO reqTO, Long restId) {
		Restaurant rest = setRest(reqTO);
		rest.setRestId(restId);
		Long restType = reqTO.getRestType();
		dao.updateForRest(rest, restType);
	}

	@Override
	public void delete(Restaurant entity) {
		dao.delete(entity);
	}

	@Override
	public List<Restaurant> getAll() {
		return null;
	}

	@Override
	public List<Restaurant> getAll(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<Restaurant> getByCompositeQuery(Map<String, String[]> map) {
		// 此方法用於wash(清洗資料)
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}

		System.out.println(query);

		return dao.getByCompositeQuery(query);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public Restaurant findAccountByUser(Restaurant entity) throws LoginException {
		Restaurant rest = dao.findAccountByUser(entity);
		if (rest == null) {
			throw new LoginException("帳號密碼輸入錯誤,請重新輸入");
		}
		return rest;
	}

	@Override
	public RestaurantReqTO findIdByUser(Long restId) {
		Restaurant rest = dao.findIdByUser(restId);
		RestaurantReqTO reqTO = new RestaurantReqTO();
		reqTO.setRestName(rest.getRestName());
		reqTO.setDescription(rest.getDescription());
		reqTO.setLocation(rest.getLocation());
		reqTO.setPhone(rest.getPhone());
		reqTO.setEmail(rest.getEmail());
		reqTO.setOpenDay(rest.getOpenDay());
		reqTO.setOpenTime1(TIME_FORMAT.format(rest.getOpenTime1()));
		reqTO.setCloseTime1(TIME_FORMAT.format(rest.getCloseTime1()));
		reqTO.setOpenTime2(TIME_FORMAT.format(rest.getOpenTime2()));
		reqTO.setCloseTime2(TIME_FORMAT.format(rest.getCloseTime2()));
		reqTO.setPassword(rest.getPassword());
		reqTO.setImage(base64Util.byteArrayTobase64(rest.getImage()));
		reqTO.setRestType(rest.getRestType().getTypeId());
		return reqTO;
	}

	public void setRestaurantTimes(Restaurant rest, String openTime1Str, String closeTime1Str, String openTime2Str,
			String closeTime2Str) {
		try {
			Time openTime1 = new Time(TIME_FORMAT.parse(openTime1Str).getTime());
			Time closeTime1 = new Time(TIME_FORMAT.parse(closeTime1Str).getTime());
			Time openTime2 = new Time(TIME_FORMAT.parse(openTime2Str).getTime());
			Time closeTime2 = new Time(TIME_FORMAT.parse(closeTime2Str).getTime());

			rest.setOpenTime1(openTime1);
			rest.setCloseTime1(closeTime1);
			rest.setOpenTime2(openTime2);
			rest.setCloseTime2(closeTime2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Restaurant setRest(RestaurantReqTO reqTO) {
		Restaurant restaurant = new Restaurant();
		restaurant.setRestName(reqTO.getRestName());
		restaurant.setDescription(reqTO.getDescription());
		restaurant.setLocation(reqTO.getLocation());
		restaurant.setPhone(reqTO.getPhone());
		restaurant.setEmail(reqTO.getEmail());
		restaurant.setOpenDay(reqTO.getOpenDay());
		setRestaurantTimes(restaurant, reqTO.getOpenTime1(), reqTO.getCloseTime1(), reqTO.getOpenTime2(),
				reqTO.getCloseTime2());
		restaurant.setPassword(reqTO.getPassword());
		restaurant.setImage(base64Util.base64ToByteArray(reqTO.getImage()));
		return restaurant;
	}

	@Override
	public void resetPassword(RestaurantResetPasswordReqTO reqTO) {
        // 生成新的隨機密碼
        int englishUpper = 65;
        int englishLower = 97;
        int[] random = new int[62];
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i <= 61; i++) {
            if (i <= 9) {
                random[i] = i; // 數字 0~9 INDEX:0~9
            } else if (i <= 35) {
                random[i] = englishUpper; // 英文大寫 A~Z INDEX:10~35
                englishUpper++;
            } else if (i <= 61) {
                random[i] = englishLower; // 英文小寫 a~z INDEX:36~61
                englishLower++;
            }
        }
        for (int i = 1; i <= 12; i++) {
            int ran = (int) (Math.random() * 62); // 修正亂數範圍
            if (ran <= 9) {
                randomString.append((char) (random[ran] + '0')); // 轉換為字符
            } else if (ran <= 35) {
                randomString.append((char) random[ran]);
            } else if (ran <= 61) {
                randomString.append((char) random[ran]);
            }
        }
        String password = randomString.toString();
        String email = reqTO.getEmail();
        Restaurant restaurant = dao.resetPassword(email, password);

        if (restaurant == null) {
            throw new IllegalArgumentException("查無此信箱，請重新輸入。");
        }

        // 發送郵件
        String subject = "您的新密碼";
        String body = "您的新密碼是: " + password;
        try {
            sendEmail(email, subject, body);
        } catch (MessagingException e) {
            // 處理郵件發送錯誤
            e.printStackTrace();
            throw new RuntimeException("郵件發送失敗。");
        }
    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        // 設置郵件服務器屬性
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // 創建 Session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // 創建郵件對象
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // 發送郵件
        Transport.send(message);
        System.out.println("郵件發送成功到 " + to);
    }
}
