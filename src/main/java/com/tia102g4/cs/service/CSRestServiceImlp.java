package com.tia102g4.cs.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Map;

import com.tia102g4.cs.dao.CSDAO;
import com.tia102g4.cs.dao.CSRestDAOImpl;
import com.tia102g4.cs.model.CustomerService;
import com.tia102g4.cs.to.req.CSReqTo;

public class CSRestServiceImlp implements CSService {

	private CSDAO dao;

	public CSRestServiceImlp() {
		dao = new CSRestDAOImpl();
	}

	@Override
	public void create(CSReqTo reqTO) {
	}

	@Override
	public void update(CSReqTo reqTO) {

	}

	@Override
	public void delete(CSReqTo reqTO) {

	}

	@Override
	public List<CustomerService> getAllCS(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<CustomerService> getCSByCompositeQuery(Map<String, String[]> map) {
		return null;
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
}
