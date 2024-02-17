package com.jsp.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/WithdrawValidation")
public class WithdrawValidation extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mobile=req.getParameter("mobile");
		String password=req.getParameter("password");
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		
		String select="select * from bank where mob_number=?and paassword=?";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			
			ps.setString(1, mobile);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			PrintWriter w=resp.getWriter();
			HttpSession session=req.getSession();
			if(rs.next())
			{
				int damount=rs.getInt(5);
				session.setAttribute("damount", damount);
				session.setAttribute("mobile", mobile);
				session.setAttribute("password", password);
				
				RequestDispatcher dispatcher=req.getRequestDispatcher("Amount.html");
				dispatcher.include(req, resp);
			}
			else
			{
				RequestDispatcher dispatcher=req.getRequestDispatcher("WithDraw.html");
				dispatcher.include(req, resp);
				w.println("<center><h1>Invalid Details</h1></center>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
