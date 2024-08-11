package com.tia102g4.anno.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.anno.model.Anno;

public interface AnnoDAO {
	
	Long insert(Anno entity);
	
	Long update(Anno entity);
	
	void delete(Anno entity);
	
	List<Anno> getByCompositeQuery(Map<String, String> map);
	
	List<Anno> getAll(int currentPage);
	
	long getTotal();
}


