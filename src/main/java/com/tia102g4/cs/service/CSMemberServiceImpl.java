package com.tia102g4.cs.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.tia102g4.cs.dao.CSDAO;
import com.tia102g4.cs.dao.CSMemberDAOImpl;
import com.tia102g4.cs.mapper.CustomerServiceMapper;
import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.to.req.CSReqTO;

public class CSMemberServiceImpl implements CSService {

	private CSDAO dao;
	private CustomerServiceMapper csMapper = new CustomerServiceMapper();

	public CSMemberServiceImpl() {
		dao = new CSMemberDAOImpl();
	}

	@Override
	public void insert(CSReqTO reqTO) {
		Long csId = reqTO.getCsId();
		Integer replyHeading = reqTO.getReplyHeading().getReplyHeading();
		String replyContent = reqTO.getReplyContent();
		Timestamp replyTime = Timestamp.from(Instant.now());
		Boolean replyStatus = true;
		Long adminId = reqTO.getAdminId();
		dao.update(csId, replyHeading, replyContent, replyTime, replyStatus, adminId);
	}

	@Override
	public void delete(CSReqTO reqTO) {
		Long csId = reqTO.getCsId();
		Boolean deletedAdmin = reqTO.getDeletedAdmin();
		dao.delete(csId, deletedAdmin);
	}

	@Override
	public List<CSReqTO> getAllCS(int currentPage) {
		List<CSReqTO> reqTOs = new ArrayList<>();
		dao.getAll(currentPage).forEach(c -> reqTOs.add(csMapper.setCSReqTO(c)));
		return reqTOs;
	}

	@Override
	public List<CSReqTO> getCSByCompositeQuery(Map<String, String[]> map) {

		Map<String, String> query = map.entrySet().stream()
					  				   			  .filter(row -> !"action".equals(row.getKey()))
					  				   			  .filter(row -> !row.getValue()[0].isEmpty() && row.getValue()[0] != null)
					  				   			  .collect(Collectors.toMap(Map.Entry::getKey, row -> row.getValue()[0]));

		List<CustomerService> customerServices = dao.getByCompositeQuery(query);
		List<CSReqTO> reqTOs = new ArrayList<>();
		for (CustomerService cs : customerServices) {
			CSReqTO dto = csMapper.setCSReqTO(cs);
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
