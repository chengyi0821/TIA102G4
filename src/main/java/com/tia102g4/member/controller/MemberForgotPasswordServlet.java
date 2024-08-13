package com.tia102g4.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g4.member.model.Member;
import com.tia102g4.member.service.MemberService;
import com.tia102g4.member.service.MemberServiceImpl;
import com.tia102g4.util.EmailUtil;

@WebServlet("/memberForgotPassword")
public class MemberForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int TOKEN_EXPIRATION_TIME = 30; // Token 有效期（分鐘）

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/memberForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        try {
            // 查找用戶
            Member member = memberService.findMemberByAccount(email);
            if (member != null) {
                // 生成重設密碼的token
                String resetToken = UUID.randomUUID().toString();
                Calendar calendar = Calendar.getInstance();
                Timestamp tokenCreatedAt = new Timestamp(calendar.getTimeInMillis());
                memberService.savePasswordResetToken(member.getMemberId(), resetToken, tokenCreatedAt);

                // 發送重設密碼郵件
                String encodedToken = URLEncoder.encode(resetToken, StandardCharsets.UTF_8);
                String resetLink = "http://chugether.ddns.net/TIA102G4/src/main/webapp/frontstage/memberFrontend/memberResetPassword.jsp" + encodedToken;
                String subject = "Password Reset Request";
                String content = "<html><body>"
                        + "<p>您好，</p>"
                        + "<p>請求重設密碼。請點擊以下連結來重設您的密碼：</p>"
                        + "<p><a href=\"" + resetLink + "\">" + resetLink + "</a></p>"
                        + "<p>請注意，該連結在 " + TOKEN_EXPIRATION_TIME + " 分鐘內有效。</p>"
                        + "<p>如果這不是您本人操作，請忽略此郵件。</p>"
                        + "</body></html>";

                EmailUtil.sendEmail(email, subject, content);

                // 將email存入session
                request.getSession().setAttribute("email", email);

                out.write("{\"success\": true}");
            } else {
                request.getSession().removeAttribute("email"); // 移除session中的email
                out.write("{\"success\": false, \"message\": \"電子郵件未註冊\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("{\"success\": false, \"message\": \"資料庫錯誤\"}");
        }
    }
}