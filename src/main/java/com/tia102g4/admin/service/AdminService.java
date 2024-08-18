package com.tia102g4.admin.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.admin.model.Admin;

public interface AdminService {
	
	Admin addAdmin(Admin admin);
	
	Admin updateAdmin(Admin admin);
	
	void deleteAdmin(Long adminId);
	
	Admin getAdminByAdminId(Long adminId);
	
	List<Admin> getAllAdmins(int currentPage);
	
	int getPageTotal();
	
	List<Admin> getAdminsByCompositeQuery(Map<String, String[]> map);
	
	Admin login(String account, String password);
	
	void updatePassword(String account, String newPassword);

}
