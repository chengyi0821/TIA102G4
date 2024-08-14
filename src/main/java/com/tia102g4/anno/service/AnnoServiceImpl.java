package com.tia102g4.anno.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.tia102g4.anno.dao.AnnoDAO;
import com.tia102g4.anno.dao.AnnoDAOImpl;
import com.tia102g4.anno.mapper.AnnoMapper;
import com.tia102g4.anno.model.Anno;
import com.tia102g4.anno.to.req.AnnoDeleteReqTO;
import com.tia102g4.anno.to.req.AnnoReqTO;
import com.tia102g4.anno.to.req.AnnoUpdateReqTO;
import com.tia102g4.restNews.model.RestaurantNews;
import com.tia102g4.restNews.to.req.RestNewsReqTO;

public class AnnoServiceImpl implements AnnoService {
	
	private AnnoDAO dao;
	private AnnoMapper annoMapper = new AnnoMapper();
	private Validator validator;
	
	public AnnoServiceImpl() {
		dao = new AnnoDAOImpl();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}

	@Override
	public void create(AnnoReqTO reqTO) {
		Anno anno = annoMapper.setAnno(reqTO);
		validateAnno(anno); // 驗證
		dao.insert(anno);
	}

	@Override
	public void update(AnnoUpdateReqTO reqTO) {
		Anno anno = annoMapper.setAnno(reqTO);
		anno.setAnnoId(reqTO.getId());
		validateAnno(anno); // 驗證
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
	public List<AnnoReqTO> getAllAnnos() {
		List<Anno> annos = dao.getAll();
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
		return reqTOs;
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
	private void validateAnno(Anno anno) {
        Set<ConstraintViolation<Anno>> violations = validator.validate(anno);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Anno> violation : violations) {
                sb.append(violation.getMessage());
            }
            throw new ConstraintViolationException(violations);
        }
    }

}
