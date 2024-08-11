package com.tia102g4.anno.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import com.tia102g4.anno.dao.AnnoDAO;
import com.tia102g4.anno.dao.AnnoDAOImpl;
import com.tia102g4.anno.mapper.AnnoMapper;
import com.tia102g4.anno.model.Anno;
import com.tia102g4.anno.to.req.AnnoDeleteReqTO;
import com.tia102g4.anno.to.req.AnnoReqTO;
import com.tia102g4.anno.to.req.AnnoUpdateReqTO;
import com.tia102g4.util.Base64Util;

import common.AnnoType;

public class AnnoServiceImpl implements AnnoService {
	
	private AnnoDAO dao;
	private AnnoMapper annoMapper = new AnnoMapper();
	
	public AnnoServiceImpl() {
		dao = new AnnoDAOImpl();
	}

	@Override
	public void create(AnnoReqTO reqTO) {
		Anno anno = annoMapper.setAnno(reqTO);
		dao.insert(anno);
	}

	@Override
	public void update(AnnoUpdateReqTO reqTO) {
		Anno anno = annoMapper.setAnno(reqTO);
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
	public List<AnnoReqTO> getAllAnnos(int currentPage) {
		List<Anno> annos = dao.getAll(currentPage);
		List<AnnoReqTO> reqTOs = new ArrayList<>();
		for (Anno anno : annos) {
			AnnoReqTO dto = annoMapper.setAnnoReqTO(anno);
			reqTOs.add(dto);
		}
		return reqTOs;
	}

	@Override
	public List<AnnoReqTO> getAnnosByCompositeQuery(Map<String, String[]> map) {
		
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
		List<Anno> annos = dao.getByCompositeQuery(query);
		List<AnnoReqTO> reqTOs = new ArrayList<>();
		for(Anno anno : annos) {
			AnnoReqTO dto = annoMapper.setAnnoReqTO(anno);
			reqTOs.add(dto);
		}
		System.out.println(query);
		
		return reqTOs;
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
}
