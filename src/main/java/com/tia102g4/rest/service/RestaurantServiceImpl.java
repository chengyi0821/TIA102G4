package com.tia102g4.rest.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

import com.tia102g4.rest.dao.RestaurantDAO;
import com.tia102g4.rest.dao.RestaurantDAOImpl;
import com.tia102g4.rest.model.Restaurant;

public class RestaurantServiceImpl implements RestaurantService {

	private RestaurantDAO dao;

	public RestaurantServiceImpl() {
		dao = new RestaurantDAOImpl();
	}

	@Override
	public void create(Restaurant entity) {

	}

	@Override
	public void update(Restaurant entity) {
		dao.update(entity);
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
		if(rest == null) {
			throw new LoginException("帳號密碼輸入錯誤,請重新輸入");
		}
		return rest;
	}

}
