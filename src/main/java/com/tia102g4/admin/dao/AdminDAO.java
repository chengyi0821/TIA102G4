package com.tia102g4.admin.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.admin.model.Admin;

public interface AdminDAO {
	
	long insert(Admin entity);
	
	void updatePassword(Admin entity);
	
	void update(Admin entity);
	
    long delete(Long id);
    
    Admin getById(Long id);
    
    List<Admin> getByCompositeQuery(Map<String, String> map);
    
    List<Admin> getAll(int currentPage);
    
    List<Admin> getAll();
    
    long getTotal();
    
    Admin findByAccountAndPassword(String account, String password);
    
    boolean findForPasswordReset(String account);
}
