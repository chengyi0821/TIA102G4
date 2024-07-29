package com.tia102g4.anno.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;


import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tia102g4.anno.dao.AnnoDAO;
import com.tia102g4.anno.dao.AnnoDAOImpl;
import com.tia102g4.anno.model.Anno;
import com.tia102g4.anno.to.req.AnnoDeleteReqTO;
import com.tia102g4.anno.to.req.AnnoReqTO;
import com.tia102g4.anno.to.req.AnnoUpdateReqTO;

public class AnnoServiceImpl implements AnnoService {
	private AnnoDAO dao;

	public AnnoServiceImpl() {
		dao = new AnnoDAOImpl();
	}

	@Override
	public void create(AnnoReqTO reqTO) {
		Anno anno = setAnno(reqTO);
		dao.insert(anno);
	}

	@Override
	public void update(AnnoUpdateReqTO reqTO) {
		Anno anno = setAnno(reqTO);
		anno.setAnnoId(reqTO.getId());
		dao.update(anno);
	}

	@Override
	public void delete(AnnoDeleteReqTO reqTO) {
		Anno anno = new Anno();
		anno.setAnnoId(reqTO.getId());
		anno.setDeleted(reqTO.getDeleted());
		dao.delete(anno);
	}

	@Override
	public List<Anno> getAllAnnos(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<Anno> getAnnosByCompositeQuery(Map<String, String[]> map) {

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
			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
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

	private Anno setAnno(AnnoReqTO reqTO) {
		Anno anno = new Anno();
		anno.setStartDate(new Date(reqTO.getStartDate().getTime()));
		anno.setEndDate(new Date(reqTO.getEndDate().getTime()));
		anno.setHeading(reqTO.getHeading());
		anno.setContent(reqTO.getContent());
		anno.setType(reqTO.getType().getTypeId());
		anno.setImage(base64ToByteArray(reqTO.getImage()));
		anno.setDeleted(reqTO.getDeleted());
		return anno;
	}

	private Byte[] base64ToByteArray(String base64String) {

		byte[] byteArray = Base64.getDecoder().decode(base64String);

		Byte[] byteObjectArray = new Byte[byteArray.length];

		for (int i = 0; i < byteArray.length; i++) {
			byteObjectArray[i] = byteArray[i];
		}

		return byteObjectArray;

	}
}
