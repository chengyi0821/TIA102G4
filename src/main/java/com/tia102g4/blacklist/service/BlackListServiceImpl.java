package com.tia102g4.blacklist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.tia102g4.blacklist.dao.BlackListDAO;
import com.tia102g4.blacklist.dao.BlackListDAOImpl;
import com.tia102g4.blacklist.model.BlackList;
import com.tia102g4.util.HibernateUtil;

public class BlackListServiceImpl implements BlackListService {
	private BlackListDAO dao;
	
	
	public BlackListServiceImpl() {
		dao = new BlackListDAOImpl();
	}
	
	 @Override
	    public BlackList addBlackList(BlackList blacklist) {
	       
	        try {
	          
	            dao.insert(blacklist);
	          
	            System.out.println("成功");
	            return blacklist;
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	            return null;
	        }
	    }

	 @Override
	    public boolean isMemberInBlackList(Long memberId, Long restId) {
	        return dao.isMemberInBlackList(memberId, restId);
	    }
	 
	 @Override
	    public void deleteBlackList(Long blackListId) {
	       
	        try {
	           
	            dao.delete(blackListId);
	           
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	        }
	    }

	 @Override
	    public List<BlackList> getAllBlackList(Long restId) {
	            List<BlackList> list = dao.getAll(restId);
	            return list;
	    
	    }
	 
	 
	 @Override
	 public List<BlackList> getBlackListByCompositeQuery(Map<String, String[]> map, Long restId) {
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

	     try {
	         List<BlackList> list = dao.getByCompositeQuery(query, restId);
	         return list;
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	 }

	
	}
