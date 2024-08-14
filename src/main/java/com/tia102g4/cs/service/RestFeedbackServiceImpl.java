package com.tia102g4.cs.service;

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

import com.tia102g4.cs.dao.RestFeedbackDAO;
import com.tia102g4.cs.dao.RestFeedbackDAOImpl;
import com.tia102g4.cs.mapper.CustomerServiceMapper;
import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.to.req.CSReqTO;
import com.tia102g4.cs.to.req.FeedbackReqTO;

public class RestFeedbackServiceImpl implements FeedbackService {

	private RestFeedbackDAO dao;
	private CustomerServiceMapper csMapper = new CustomerServiceMapper();
	private Validator validator;

	public RestFeedbackServiceImpl() {
		dao = new RestFeedbackDAOImpl();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}

	@Override
	public void insert(FeedbackReqTO reqTO) {
		validateReqTO(reqTO);
		Integer feedbackType = reqTO.getFeedbackType().getFeedbackType();
		String feedbackContent = reqTO.getFeedbackContent();
		dao.insert(feedbackType, feedbackContent);
	}

	@Override
	public void delete(CSReqTO reqTO) {
		Long csId = reqTO.getCsId();
		Boolean deletedRest = reqTO.getDeletedRest();
		dao.delete(csId, deletedRest);
	}

	@Override
	public List<CSReqTO> getAllCS(int currentPage) {
		List<CustomerService> customerServices = dao.getAll(currentPage);
		List<CSReqTO> reqTOs = new ArrayList<>();
		for (CustomerService customerService : customerServices) {
			CSReqTO dto = csMapper.setCSReqTO(customerService);
			reqTOs.add(dto);
		}
		return reqTOs;
	}

	@Override
	public List<CSReqTO> getCSByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			if ("action".equals(key)) {
				continue;
			}
			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		List<CustomerService> customerServices = dao.getByCompositeQuery(query);
		List<CSReqTO> reqTOs = new ArrayList<>();
		for (CustomerService cs : customerServices) {
			CSReqTO dto = csMapper.setCSReqTO(cs);
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

	private void validateReqTO(FeedbackReqTO reqTO) {
		Set<ConstraintViolation<FeedbackReqTO>> violations = validator.validate(reqTO);
		if (!violations.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<FeedbackReqTO> violation : violations) {
				sb.append(violation.getMessage());
			}
			throw new ConstraintViolationException(violations);
		}
	}
}
