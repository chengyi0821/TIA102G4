package com.tia102g4.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tia102g4.admin.model.Admin;
import com.tia102g4.admin.service.AdminService;
import com.tia102g4.admin.service.AdminServiceImp1;

@WebServlet("/admin/admin.do")
public class AdminServlet extends HttpServlet {
	
	private AdminService adminService;
	
	@Override
	public void init() throws ServletException {
		adminService = new AdminServiceImp1();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "add":
			forwardPath = addAdmin(req);
			break;
		case "update":
			forwardPath = updateAdmin(req, res);
			break;
		case "delete":
			forwardPath = deleteAdmin(req, res);
			break;
		case "getByAdminId":
			forwardPath = getAdminByAdminId(req, res);
			break;
		case "getAll":
			forwardPath = getAllAdmin(req, res);
			break;
		case "compositeQuery":
			forwardPath = getAdminsByCompositeQuery(req, res);
			break;
		case "login":
			forwardPath = login(req,res);
			break;
		case "register":
			forwardPath = register(req, res);
			break;
		case "resetPassword":
			forwardPath = resetPassword(req, res);
			break;
		default:
			forwardPath = "/frontstage/backend/admin/adminindex.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

}

	private String addAdmin(HttpServletRequest req) {
		
		    Admin admin = new Admin();
		    admin.setName(req.getParameter("name"));
		    admin.setAccount(req.getParameter("account"));
		    admin.setPassword(req.getParameter("password"));
		    
		    String permissionStr = req.getParameter("permission");
		    Integer permission = Integer.valueOf(permissionStr);
		    admin.setPermission(permission);
		    
		    adminService.addAdmin(admin);
		    
		    HttpSession session = req.getSession();
		    session.setAttribute("loggedInAdmin", admin);
		    
		    req.setAttribute("successMessage", "會員新增成功");
		    return "/frontstage/backend/admin/adminlistallmembers.jsp";
		}


	private String updateAdmin(HttpServletRequest req, HttpServletResponse res) {
		    Long adminId = 10L;
		    String permissionStr = req.getParameter("permission");
		    int permission = Integer.valueOf(permissionStr);

		    Admin admin = new Admin();
		    admin.setAdminId(adminId);
		    admin.setName(req.getParameter("name"));
		    admin.setAccount(req.getParameter("account"));
		    admin.setPassword(req.getParameter("password"));
		    admin.setPermission(permission);

		    adminService.updateAdmin(admin);
		    HttpSession session = req.getSession();
		    session.setAttribute("loggedInAdmin", admin);
		    req.setAttribute("successMessage", "會員更新成功！");
		    return "/frontstage/backend/admin/adminindex.jsp";
		}


	private String deleteAdmin(HttpServletRequest req, HttpServletResponse res) {
		Long adminId = Long.parseLong(req.getParameter("adminId"));
		adminService.deleteAdmin(adminId);
		req.setAttribute("successMessage", "會員刪除成功!");
		return "/frontstage/backend/admin/adminindex.jsp";
	}

	private String getAdminByAdminId(HttpServletRequest req, HttpServletResponse res) {
	    Long adminId = Long.parseLong(req.getParameter("adminId"));
	    System.out.println("getAdminByAdminId: adminId = " + adminId);
	    
	    Admin admin = adminService.getAdminByAdminId(adminId);
	    if (admin != null) {
	        req.setAttribute("admin", admin);
	        System.out.println("getAdminByAdminId: Admin found: " + admin);
	        return "/frontstage/backend/admin/adminindex.jsp";
	    } else {
	        req.setAttribute("errorMessage", "會員未找到!");
	        System.out.println("getAdminByAdminId: Admin not found.");
	        return "/frontstage/backend/admin/adminindex.jsp";
	    }
	}

	private String getAllAdmin(HttpServletRequest req, HttpServletResponse res) {
	    String page = req.getParameter("page");
	    int currentPage = (page == null) ? 1 : Integer.parseInt(page);
	    System.out.println("getAllAdmin: currentPage = " + currentPage);
	    
	    List<Admin> adminList = adminService.getAllAdmins(currentPage);
	    System.out.println("getAllAdmin: Retrieved " + adminList.size() + " admins.");
	    
	    if (req.getSession().getAttribute("adminPageQty") == null) {
	        int adminPageQty = adminService.getPageTotal();
	        req.getSession().setAttribute("adminPageQty", adminPageQty);
	        System.out.println("getAllAdmin: adminPageQty = " + adminPageQty);
	    }
	    
	    req.setAttribute("adminList", adminList);
	    req.setAttribute("currentPage", currentPage);
	    return "/frontstage/backend/admin/adminlistallmembers.jsp";
	}

	private String getAdminsByCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
	    Map<String, String[]> map = req.getParameterMap();
	    System.out.println("getAdminsByCompositeQuery: Received parameters: " + map);
	    
	    if (map != null) {
	        List<Admin> adminList = adminService.getAdminsByCompositeQuery(map);
	        System.out.println("getAdminsByCompositeQuery: Retrieved " + adminList.size() + " admins.");
	        req.setAttribute("adminList", adminList);
	    } else {
	        System.out.println("getAdminsByCompositeQuery: No parameters found.");
	        return "/frontstage/backend/admin/adminindex.jsp";
	    }
	    return "/frontstage/backend/admin/adminlistcompositequery.jsp";
	}


	private String login(HttpServletRequest req, HttpServletResponse res) {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		Admin admin = adminService.login(account, password);
		
		if (admin != null) {
			HttpSession session = req.getSession();
			
			session.setAttribute("loggedInAdmin", admin);
			session.setAttribute("adminId", admin.getAdminId());
			return"/frontstage/backend/admin/adminindex.jsp";
		}else {
			req.setAttribute("errorMessage", "登入失敗,請檢查您的帳號和密碼 !");
			return "/frontstage/backend/admin/adminlogin.jsp";
		}
		
	}

	private String register(HttpServletRequest req, HttpServletResponse res) {
		    // 創建一個新的 Admin 對象
		    Admin admin = new Admin();
		    
		    // 從請求中獲取參數並設置 Admin 的屬性
		    admin.setName(req.getParameter("name"));
		    admin.setAccount(req.getParameter("account"));
		    admin.setPassword(req.getParameter("password"));
		    admin.setPermission(Integer.valueOf(req.getParameter("permission")));
		             
		    // 獲取 HttpSession 並保存 Admin 信息
		    HttpSession session = req.getSession();
		    
		    // 使用 adminService 進行新增，假設這裡已經有事務處理
		    adminService.addAdmin(admin);
		    
		    // 設置已登入的 Admin 到會話中
		    session.setAttribute("loggedInAdmin", admin);
		    session.setAttribute("adminId", admin.getAdminId());
		    
		    // 返回註冊成功後的 JSP 頁面
		    return "/frontstage/backend/admin/adminrestpassword.jsp";
		}



	private String resetPassword(HttpServletRequest req, HttpServletResponse res) {
		String newPassword = req.getParameter("newPassword");
		
		Admin admin = (Admin)req.getSession().getAttribute("loggedInAdmin");
		String account = admin.getAccount();
		if (account != null && newPassword != null && !newPassword.trim().isEmpty()) {
			adminService.updatePassword(account, newPassword);
			req.setAttribute("successMessage", "密碼變更成功，請重新登入 !");
			req.getSession().removeAttribute("resetAdmin");
			return "/frontstage/backend/admin/adminlogin.jsp";
		} else {
			req.setAttribute("errorMessage", "密碼不能為空，請重新輸入 !");
			return "/frontstage/backend/admin/adminrestpassword.jsp";
		}
		}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	}


