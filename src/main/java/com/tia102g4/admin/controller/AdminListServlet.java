package com.tia102g4.admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.admin.model.Admin;
import com.tia102g4.admin.service.AdminService;
import com.tia102g4.admin.service.AdminServiceImpl;

@WebServlet("/adminList")
public class AdminListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("toggleStatus".equals(action)) {
                toggleAdminStatus(request, response);
            } else if (action == null || action.isEmpty() || "list".equals(action)) {
                String permissionFilter = request.getParameter("permissionFilter");
//                Boolean statusFilter = request.getParameter("statusFilter") != null ? Boolean.parseBoolean(request.getParameter("statusFilter")) : null; // 获取状态筛选参数
                String statusFilterParam = request.getParameter("statusFilter"); // 获取状态筛选参数
                Boolean statusFilter = (statusFilterParam != null && !statusFilterParam.isEmpty()) 
                                        ? Boolean.parseBoolean(statusFilterParam) 
                                        : null; // 如果为空值则为 null
                
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                String search = request.getParameter("search");
                String sortField = request.getParameter("sortField");
                String sortOrder = request.getParameter("sortOrder");
                int itemsPerPage = request.getParameter("itemsPerPage") != null
                        ? Integer.parseInt(request.getParameter("itemsPerPage"))
                        : 10;
                int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

                listAdmins(request, response, permissionFilter, statusFilter, startDate, endDate, search, sortField, sortOrder,
                        itemsPerPage, currentPage);
            } else if ("edit".equals(action)) {
                editAdmin(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void toggleAdminStatus(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long adminId = Long.parseLong(request.getParameter("adminId"));
        Admin admin = adminService.findAdminById(adminId);
        
        if (admin != null) {
            admin.setAccStatus(!admin.getAccStatus()); // 切換 accStatus
            adminService.updateAdmin(admin);

            // 返回JSON響應
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": true, \"newStatus\": " + admin.getAccStatus() + "}");
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"管理員未找到\"}");
        }
    }


    private void listAdmins(HttpServletRequest request, HttpServletResponse response, String permissionFilter,
                            Boolean statusFilter, String startDate, String endDate, String search, String sortField, String sortOrder,
                            int itemsPerPage, int currentPage)
            throws SQLException, ServletException, IOException {

        int totalAdmins = adminService.countFilteredAdmins(permissionFilter, statusFilter, startDate, endDate, search);
        int totalPages = (int) Math.ceil((double) totalAdmins / itemsPerPage);

        List<Admin> admins = adminService.findFilteredAdmins(permissionFilter, statusFilter, startDate, endDate, search, sortField,
                sortOrder, itemsPerPage, currentPage);

        request.setAttribute("totalAdmins", totalAdmins);
        request.setAttribute("admins", admins);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("permissionFilter", permissionFilter);
        request.setAttribute("statusFilter", statusFilter);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("search", search);
        request.setAttribute("sortField", sortField);
        request.setAttribute("sortOrder", sortOrder);

        request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
    }

    private void editAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long adminId = Long.parseLong(request.getParameter("adminId"));
        Admin admin = adminService.findAdminById(adminId);
        if (admin != null) {
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/editAdmin.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "管理員未找到");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.isEmpty()) {
                response.sendRedirect("adminList");
            } else if (action.equals("update")) {
                updateAdmin(request, response);
            } else if (action.equals("add")) {
                addAdmin(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long adminId = Long.parseLong(request.getParameter("adminId"));
        String name = request.getParameter("name");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Integer permission = Integer.parseInt(request.getParameter("permission"));

        Admin existingAdmin = adminService.findAdminByAccount(account);
        if (existingAdmin != null && !existingAdmin.getAdminId().equals(adminId)) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "該帳號已被使用");
            return;
        }

        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setName(name);
        admin.setAccount(account);
        admin.setPassword(password);
        admin.setPermission(permission);

        adminService.updateAdmin(admin);
        response.sendRedirect("adminList");
    }

    private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String account = request.getParameter("account");
        String password = "Chugether"; // 強制使用預設密碼
        Integer permission = Integer.parseInt(request.getParameter("permission"));
        Boolean accStatus =  Boolean.parseBoolean(request.getParameter("accStatus"));

        Admin admin = new Admin();
        admin.setName(name);
        admin.setAccount(account);
        admin.setPassword(password);
        admin.setPermission(permission);
        admin.setRegisDate(new java.sql.Timestamp(System.currentTimeMillis()));
        admin.setAccStatus(accStatus != null ? accStatus : true);

        adminService.addAdmin(admin);
        response.sendRedirect("adminList");
    }
}
