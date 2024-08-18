package com.tia102g4.admin.service;

import static com.tia102g4.util.Constants.PAGE_MAX_RESULT;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import com.tia102g4.admin.dao.AdminDAO;
import com.tia102g4.admin.dao.AdminDAOImp1;
import com.tia102g4.admin.model.Admin;

public class AdminServiceImp1 implements AdminService {
    
	private AdminDAO dao;
	
	public AdminServiceImp1() {
		dao = new AdminDAOImp1();
	}

	@Override
	public Admin addAdmin(Admin admin) {
		dao.insert(admin);
		return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		dao.update(admin);
		return admin;
	}

	@Override
	public void deleteAdmin(Long adminId) {
		dao.delete(adminId);	
	}

	@Override
	public Admin getAdminByAdminId(Long adminId) {
		return dao.getById(adminId);
	}

	@Override
	public List<Admin> getAllAdmins(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<Admin> getAdminsByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			if ("action".equals(key)) {
				continue;
			}
			String value = row.getValue()[0]; 
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println(query);
		
		return dao.getByCompositeQuery(query);
	}

	@Override
	public Admin login(String account, String password) {
		return dao.findByAccountAndPassword(account, password);
	}

	@Override
	public void updatePassword(String account, String newPassword) {
		Admin admin = new Admin();
		admin.setPassword(newPassword);
		admin.setAccount(account);
		dao.updatePassword(admin);
		
	}
	
	

}
