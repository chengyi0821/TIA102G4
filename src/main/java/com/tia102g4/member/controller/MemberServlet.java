package com.tia102g4.member.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.google.protobuf.TimestampProto;
import com.tia102g4.member.model.Member;
import com.tia102g4.member.service.MemberService;
import com.tia102g4.member.service.MemberServiceImp1;

@WebServlet("/member/member.do")
public class MemberServlet extends HttpServlet {

	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImp1();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "add":
			forwardPath = addMember(req);
			break;
		case "update":
			forwardPath = updateMember(req);
			break;
		case "delete":
			forwardPath = deleteMember(req, res);
			break;
		case "getByMemberId":
			forwardPath = getMemberByMemberId(req, res);
			break;
		case "getAll":
			forwardPath = getAllMembers(req, res);
			break;
		case "compositeQuery":
			forwardPath = getMembersByCompositeQuery(req, res);
			break;
		case "login":
			forwardPath = login(req, res);
			break;
		case "forgotPassword":
			forwardPath = forgotPassword(req, res);
			break;
		case "register":
			forwardPath = register(req, res);
			break;
		case "resetPassword":
			forwardPath = resetPassword(req, res);
			break;
		default:
			forwardPath = "/frontstage/memberFrontend/member/memberindex.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String addMember(HttpServletRequest req) {

		String genderStr = req.getParameter("gender");
		boolean gender = Boolean.parseBoolean(genderStr);
		Member member = new Member();
		member.setName(req.getParameter("name"));
		member.setAccount(req.getParameter("account"));
		member.setPassword(req.getParameter("password"));
		member.setEmail(req.getParameter("email"));
		member.setMobileNo(req.getParameter("mobileNo"));
		member.setGender(gender);

		memberService.addMember(member);

		HttpSession session = req.getSession();
		session.setAttribute("loggedInMember", member);

		req.setAttribute("successMessage", "會員新增成功");
		return "/frontstage/memberFrontend/member/memberlistallmembers.jsp";
	}

	private String updateMember(HttpServletRequest req) {
		HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
		String genderStr = req.getParameter("gender");
		boolean gender = Boolean.parseBoolean(genderStr);

		Member member = new Member();
		member.setMemberId(memberId);
		member.setName(req.getParameter("name"));
		member.setAccount(req.getParameter("account"));
		member.setPassword(req.getParameter("password"));
		member.setEmail(req.getParameter("email"));
		member.setGender(gender);
		member.setMobileNo(req.getParameter("mobileNo"));
		member.setAccStatus(true);

		memberService.updateMember(member);
		session.setAttribute("loggedInMember", member);
		req.setAttribute("successMessage", "會員更新成功！");
		return "/frontstage/memberFrontend/member/memberindex.jsp"; // 返回到會員列表頁面
	}

	private String deleteMember(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = Long.parseLong(req.getParameter("memberId"));
		memberService.deleteMember(memberId);
		req.setAttribute("successMessage", "會員刪除成功!");
		return "/frontstage/memberFrontend/member/memberindex.jsp";

	}

	private String getMemberByMemberId(HttpServletRequest req, HttpServletResponse res) {
		Long memberId = Long.parseLong(req.getParameter("memberId"));
		Member member = memberService.getMemberByMemberId(memberId);
		if (member != null) {
			req.setAttribute("member", member);
			return "/frontstage/memberFrontend/member/memberindex.jsp";
		} else {
			req.setAttribute("errorMessage", "會員未找到!");
			return "/frontstage/memberFrontend/member/memberindex.jsp";
		}
	}

	private String getAllMembers(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<Member> memberList = memberService.getAllMembers(currentPage);

		if (req.getSession().getAttribute("memberPageQty") == null) {
			int memberPageQty = memberService.getPageTotal();
			req.getSession().setAttribute("memberPageQty", memberPageQty);
		}

		req.setAttribute("memberList", memberList);
		req.setAttribute("currentPage", currentPage);

		return "/frontstage/memberFrontend/member/memberlistallmembers.jsp";
	}

	private String getMembersByCompositeQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			List<Member> memberList = memberService.getMembersByCompositeQuery(map);
			req.setAttribute("memberList", memberList);
		} else {
			return "/frontstage/memberFrontend/member/memberindex.jsp";
		}
		return "/frontstage/memberFrontend/member/memberlistcompositequery.jsp";
	}

	private String login(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// 使用 email 和 password 登入取得 Member 
		Member member = memberService.login(email, password);

		if (member != null) {
			HttpSession session = req.getSession();

			// 將 Member 存入 session
			session.setAttribute("loggedInMember", member);

			// 將 memberId 存入 session
			session.setAttribute("memberId", member.getMemberId());
			return "/frontstage/memberFrontend/member/memberindex.jsp";
		} else {
			req.setAttribute("errorMessage", "登入失敗,請檢查您的帳號和密碼 !");
			return "/frontstage/memberFrontend/member/memberlogin.jsp";
		}
	}

	private String forgotPassword(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter("email");

		if (memberService.validateForPasswordReset(email)) {
			req.getSession().setAttribute("resetMember", email);
			return "/frontstage/memberFrontend/member/memberrestpassword.jsp";
		} else {
			req.setAttribute("errorMessage", "驗證失敗,請確認您的資料 !");
			return "/frontstage/memberFrontend/member/memberforgotpassword.jsp";
		}
	}

	private String resetPassword(HttpServletRequest req, HttpServletResponse res) {
		String newPassword = req.getParameter("newPassword");
		String email = (String) req.getSession().getAttribute("resetMember");
		if (email != null && newPassword != null && !newPassword.trim().isEmpty()) {
			memberService.updatePassword(email, newPassword);
			req.setAttribute("successMessage", "密碼變更成功，請重新登入 !");
			req.getSession().removeAttribute("resetMember");
			return "/frontstage/memberFrontend/member/memberindex.jsp";
		} else {
			req.setAttribute("errorMessage", "密碼不能為空，請重新輸入 !");
			return "/frontstage/memberFrontend/member/memberrestpassword.jsp";
		}
	}

	private String register(HttpServletRequest req, HttpServletResponse res) {
		String genderStr = req.getParameter("gender");
		boolean gender = "1".equals(genderStr);

		Member member = new Member();
		member.setName(req.getParameter("name"));
		member.setAccount(req.getParameter("account"));
		member.setPassword(req.getParameter("password"));
		member.setEmail(req.getParameter("email"));
		member.setGender(gender);
		member.setMobileNo(req.getParameter("mobileNo"));
		if (memberService.isDuplicate(member)) {
			req.setAttribute("errorMessage", "帳號、信箱或手機號碼已經存在，請重新輸入。");
			return "/frontstage/memberFrontend/member/memberregister.jsp";
		} else {
			memberService.addMember(member);
			req.getSession().setAttribute("loggedInMember", member);
			return "/frontstage/memberFrontend/member/memberindex.jsp";
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}



