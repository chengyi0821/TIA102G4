package com.tia102g4.myorder.service;

import java.util.List;
import java.util.Map;

import com.tia102g4.myorder.model.MyOrder;
import com.tia102g4.myorder.model.PageInfo;

public interface MyOrderService {
	
	    MyOrder addMyOrder(MyOrder myorder);
	    MyOrder updateMyOrder(MyOrder myorder);
	    void deleteMyOrder(Integer orderId);
	    MyOrder getMyOrderByOrderId(Long orderId);
	    MyOrder getMyOrderByOrderId1(Long orderId, Long restId);
	    PageInfo<MyOrder> getAllMyOrders(int currentPage, int recordsPerPage);
	    List<MyOrder> getMyOrdersByCompositeQuery(Map<String, String[]> map);
	    PageInfo<MyOrder> getOrderStatus1(int currentPage, int recordsPerPage);
	    PageInfo<MyOrder> getOrderStatus2(int currentPage, int recordsPerPage);
	    PageInfo<MyOrder> getOrderStatus3(int currentPage, int recordsPerPage);
	    void updateOrderStatus(Long orderId, String status);
	    void updateOrderStatus3(Long orderId, String status);
	    
	    
	    
	    PageInfo<MyOrder> getAllMyOrdersByRest(Long restId, int currentPage, int recordsPerPage);
	    List<MyOrder> getCompositeOrdersQueryByRestId(Map<String, String[]> map, Long restId);
	    PageInfo<MyOrder> getOrderStatus1Rest(Long restId, int currentPage, int recordsPerPage);
	    PageInfo<MyOrder> getOrderStatus2Rest(Long restId, int currentPage, int recordsPerPage);
	    PageInfo<MyOrder> getOrderStatus3Rest(Long restId, int currentPage, int recordsPerPage);
	    void updateOrderStatus2Rest(Long orderId, String status, Long restId);
	    void updateOrderStatus3Rest(Long orderId, String status, Long restId);
	   
	    
	    
	    void updateOrderStatus2Mem(Long orderId, String status, Long memberId);
	    List<MyOrder> getOrderStatus1Member(Long memberId);
	    List<MyOrder> getOrderStatus2Member(Long memberId);
	    List<MyOrder> getOrderStatus3Member(Long memberId);
	    void addOrder(MyOrder order);
	    void updateOrder(MyOrder order);
	    
	    
	}


