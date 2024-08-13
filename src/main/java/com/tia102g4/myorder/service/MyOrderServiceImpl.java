package com.tia102g4.myorder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.tia102g4.myorder.dao.MyOrderDAO;
import com.tia102g4.myorder.dao.MyOrderDAOImpl;
import com.tia102g4.myorder.model.MyOrder;
import com.tia102g4.myorder.model.PageInfo;

public class MyOrderServiceImpl implements MyOrderService {
    private MyOrderDAO dao;

    public MyOrderServiceImpl() {
        dao = new MyOrderDAOImpl();
    }

    @Override
    public MyOrder addMyOrder(MyOrder myorder) {
        dao.insert(myorder);
        return myorder;
    }

    @Override
    public MyOrder updateMyOrder(MyOrder myorder) {
        dao.update(myorder);
        return myorder;
    }

    @Override
    public void deleteMyOrder(Integer orderId) {
    }

    @Override
    public MyOrder getMyOrderByOrderId(Long orderId) {
        try {
            MyOrder myOrder = dao.getById(orderId);
            return myOrder; 
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
    
    @Override
    public MyOrder getMyOrderByOrderId1(Long orderId, Long restId) {
        try {
            MyOrder myOrder = dao.getById1(orderId, restId);
            return myOrder; 
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
    
    @Override
    public PageInfo<MyOrder> getAllMyOrders(int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getAll(currentPage, recordsPerPage);
            long totalRecords = dao.getTotal();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo; 
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }

    @Override
    public PageInfo<MyOrder> getOrderStatus1(int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getOrderStatus1(currentPage, recordsPerPage);
            long totalRecords = dao.getOrderStatus1Total();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    @Override
    public PageInfo<MyOrder> getOrderStatus2(int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getOrderStatus2(currentPage, recordsPerPage);
            long totalRecords = dao.getOrderStatus2Total();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public PageInfo<MyOrder> getOrderStatus3(int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getOrderStatus3(currentPage, recordsPerPage);
            long totalRecords = dao.getOrderStatus3Total();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MyOrder> getMyOrdersByCompositeQuery(Map<String, String[]> map) {
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

        System.out.println(query);

        try {
            List<MyOrder> list = dao.getByCompositeQuery(query);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


  
    @Override
    public void updateOrderStatus(Long orderId, String status) {
        dao.updateOrderStatus(orderId, status);
    }
    
    @Override
    public void updateOrderStatus3(Long orderId, String status) {
        dao.updateOrderStatus3(orderId, status);
    }
    
    
//    ===================================restaurant================================================
    @Override
    public PageInfo<MyOrder> getAllMyOrdersByRest(Long restId, int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getAllByRestaurantId(restId, currentPage, recordsPerPage);
            long totalRecords = dao.getTotalByRestaurantId(restId);
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw e; 
        }
    }

    
    @Override
    public List<MyOrder> getCompositeOrdersQueryByRestId(Map<String, String[]> map, Long restId) {
        Map<String, String> query = new HashMap<>();
        Set<Map.Entry<String, String[]>> entry = map.entrySet();

        for (Map.Entry<String, String[]> row : entry) {
            String key = row.getKey();
            if ("action".equals(key) || "restId".equals(key)) {
                continue;
            }
            String value = row.getValue()[0];
            if (value.isEmpty() || value == null) {
                continue;
            }
            query.put(key, value);
        }

        try {
            List<MyOrder> orders = dao.getByCompositeQueryByRestId(query, restId);
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    @Override
    public PageInfo<MyOrder> getOrderStatus1Rest(Long restId, int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getOrderStatus1Rest(restId, currentPage, recordsPerPage);
            long totalRecords = dao.getOrderStatus1Total(restId);
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    	
    @Override
    public PageInfo<MyOrder> getOrderStatus2Rest(Long restId, int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getOrderStatus2Rest(restId, currentPage, recordsPerPage);
            long totalRecords = dao.getOrderStatus2Total(restId);
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public PageInfo<MyOrder> getOrderStatus3Rest(Long restId, int currentPage, int recordsPerPage) {
        try {
            List<MyOrder> orders = dao.getOrderStatus3Rest(restId, currentPage, recordsPerPage);
            long totalRecords = dao.getOrderStatus3Total(restId);
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            PageInfo<MyOrder> pageInfo = new PageInfo<>();
            pageInfo.setItems(orders);
            pageInfo.setTotalRecords(totalRecords);
            pageInfo.setTotalPages(totalPages);
            pageInfo.setCurrentPage(currentPage);

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void updateOrderStatus2Rest(Long orderId, String status, Long restId) {
        dao.updateOrderStatus2Rest(orderId, status, restId);
    }

    
    @Override
    public void updateOrderStatus3Rest(Long orderId, String status, Long restId) {
        dao.updateOrderStatus3Rest(orderId, status, restId);
    }
    
////    ======================================member====================================
   
    @Override
    public List<MyOrder> getOrderStatus1Member(Long memberId) {
        return dao.getOrderStatus1Member(memberId);
    }
    
    @Override
    public List<MyOrder> getOrderStatus2Member(Long memberId) {
        return dao.getOrderStatus2Member(memberId);
    }
    
    @Override
    public List<MyOrder> getOrderStatus3Member(Long memberId) {
        return dao.getOrderStatus3Member(memberId);
    }
    	
    @Override
    public void updateOrderStatus2Mem(Long orderId, String status, Long memberId) {
        dao.updateOrderStatus2Mem(orderId, status, memberId);
    }

    @Override
    public void addOrder(MyOrder order) {
    	dao.addOrder(order);
    }

    @Override
    public void updateOrder(MyOrder order) {
        try {
         dao.updateOrder(order);
//            RestaurantWebSocket.sendMessageToRestaurant(order.getRestaurant().getRestId(), 
//                "订单已更新，订单ID: " + order.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

