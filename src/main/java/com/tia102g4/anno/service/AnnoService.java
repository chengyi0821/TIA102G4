package com.tia102g4.anno.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.anno.model.Anno;
import com.tia102g4.anno.to.req.AnnoDeleteReqTO;
import com.tia102g4.anno.to.req.AnnoReqTO;
import com.tia102g4.anno.to.req.AnnoUpdateReqTO;

public interface AnnoService {

	void create(AnnoReqTO reqTO);

	void update(AnnoUpdateReqTO reqTO);

	void delete(AnnoDeleteReqTO reqTO);

	List<Anno> getAllAnnos(int currentPage);

	int getPageTotal();

	List<Anno> getAnnosByCompositeQuery(Map<String, String[]> map);
}
