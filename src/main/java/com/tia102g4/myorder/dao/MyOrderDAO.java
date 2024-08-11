package com.tia102g4.myorder.dao;

import java.util.List;
import java.util.Map;

import com.tia102g4.myorder.model.MyOrder;

public interface MyOrderDAO {
	  	int insert(MyOrder entity);
	    int update(MyOrder entity);
	    void updateOrderStatus(Long orderId, String status);
	    void updateOrderStatus3(Long orderId, String status);
	    int delete(Integer orderId);
	    MyOrder getById(Long orderId);
	    List<MyOrder> getAll();
	    List<MyOrder> getByCompositeQuery(Map<String, String> map);
	    List<MyOrder> getAll(int currentPage, int recordsPerPage);
	    long getTotal();
	    List<MyOrder> getOrderStatus1(int currentPage, int recordsPerPage);
	    long getOrderStatus1Total();
	    List<MyOrder> getOrderStatus2(int currentPage, int recordsPerPage);
	    long getOrderStatus2Total();
	    List<MyOrder> getOrderStatus3(int currentPage, int recordsPerPage);
	    long getOrderStatus3Total();
	    
	    
	    List<MyOrder> getAllByRestaurantId(Long restId, int currentPage, int recordsPerPage);
	    long getTotalByRestaurantId(Long restId);
	    List<MyOrder> getByCompositeQueryByRestId(Map<String, String> map, Long restId);
	    List<MyOrder> getOrderStatus1Rest(Long restId, int currentPage, int recordsPerPage);
	    long getOrderStatus1Total(Long restId);
	    List<MyOrder> getOrderStatus2Rest(Long restId, int currentPage, int recordsPerPage);
	    long getOrderStatus2Total(Long restId);
	    List<MyOrder> getOrderStatus3Rest(Long restId, int currentPage, int recordsPerPage);
	    long getOrderStatus3Total(Long restId);
	    void updateOrderStatus2Rest(Long orderId, String status, Long restId);
	    void updateOrderStatus3Rest(Long orderId, String status, Long restId);
	    
	    
	    
	    void updateOrderStatus2Mem(Long orderId, String status, Long memberId);
	    List<MyOrder> getOrderStatus1Member(Long memberId);
	    List<MyOrder> getOrderStatus2Member(Long memberId);
	    List<MyOrder> getOrderStatus3Member(Long memberId);
	    void addOrder(MyOrder order);
	    void updateOrder(MyOrder order);
    
    
}
