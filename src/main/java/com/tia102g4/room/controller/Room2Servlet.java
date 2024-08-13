package com.tia102g4.room.controller;
import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.member.model.Member;
import com.tia102g4.room.model.Room;
import com.tia102g4.room.service.Room2Service;
import com.tia102g4.room.service.Room2ServiceImp1;

@WebServlet("/room/room2.do")
public class Room2Servlet extends HttpServlet {

	private Room2Service room2Service;
	
	public void init()throws ServletException{
		room2Service = new Room2ServiceImp1();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "createRoom":
			forwardPath = createRoom(req, res);
			break;
		case "updateRoomStatus":
			forwardPath = updateRoomStatus(req, res);
			break;
		case "getRoomByQrCode":
			forwardPath = getRoomByQrCode(req, res);
			break;
		case "checkInviteCode":
			forwardPath = checkInviteCode(req, res);
			break;
		case "getMembersByRoomId":
			forwardPath = updateMemberStatus(req, res);
			break;
		default:
			forwardPath = "/index.jsp";
	}
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	private String createRoom(HttpServletRequest req, HttpServletResponse res) {
		Room room = new Room();
		Long roomId = room2Service.createRoom(room);
		req.setAttribute("roomId", roomId);
		return "/createRoomResult.jsp";
	}
	private String updateRoomStatus(HttpServletRequest req, HttpServletResponse res) {
		Long roomId = Long.parseLong(req.getParameter("roomId"));
		boolean status = Boolean.parseBoolean(req.getParameter("status"));
		room2Service.updateRoomStatus(roomId, status);
		return "/updateRoomStatusResult.jsp";
	}
	private String getRoomByQrCode(HttpServletRequest req, HttpServletResponse res) {
		String inviteCode = req.getParameter("inviteCode");
		Room room = room2Service.getRoomByQrCode(inviteCode);
		req.setAttribute("room", room);
		return "/getRoomResult";
	}
	private String checkInviteCode(HttpServletRequest req, HttpServletResponse res) {
		String inviteCode = req.getParameter("inviteCode");
		boolean isValid = room2Service.InviteCodeValid(inviteCode);
		req.setAttribute("isValid", isValid);
		return "/Room1.jsp";
	}
	private String updateMemberStatus(HttpServletRequest req, HttpServletResponse res) {
		Long roomId = Long.parseLong(req.getParameter("roomId"));
		List<Member> members = room2Service.getMembersByJoinTime(roomId);
		req.setAttribute("members", members);
		return "/getMmebersByRoomIdResult.jsp";
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
	
	

