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

import com.tia102g4.cs.dao.MemberFeedbackDAO;
import com.tia102g4.cs.dao.MemberFeedbackDAOImpl;
import com.tia102g4.cs.mapper.CustomerServiceMapper;
import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.to.req.CSReqTO;
import com.tia102g4.cs.to.req.FeedbackReqTO;

public class MemberFeedbackServiceImpl implements FeedbackService {
	private MemberFeedbackDAO dao;
	private CustomerServiceMapper csMapper = new CustomerServiceMapper();
	private Validator validator;

	public MemberFeedbackServiceImpl() {
		dao = new MemberFeedbackDAOImpl();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void insert(FeedbackReqTO reqTO, Long id) {
		validateReqTO(reqTO);
		Integer feedbackType = reqTO.getFeedbackType().getFeedbackType();
		String feedbackContent = reqTO.getFeedbackContent();
		dao.insert(feedbackType, feedbackContent, id);
	}

	@Override
	public void delete(CSReqTO reqTO) {
		Long csId = reqTO.getCsId();
		Boolean deletedMember = reqTO.getDeletedMember();
		dao.delete(csId, deletedMember);
	}

	@Override
	public List<CSReqTO> getAllCS(int currentPage, Long id) {
		List<CustomerService> customerServices = dao.getAll(currentPage, id);
		List<CSReqTO> reqTOs = new ArrayList<>();
		for (CustomerService customerService : customerServices) {
			CSReqTO dto = csMapper.setCSReqTO(customerService);
			reqTOs.add(dto);
		}
		return reqTOs;
	}

	@Override
	public List<CSReqTO> getCSByCompositeQuery(Map<String, String[]> map, Long id) {
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
		List<CustomerService> customerServices = dao.getByCompositeQuery(query, id);
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
