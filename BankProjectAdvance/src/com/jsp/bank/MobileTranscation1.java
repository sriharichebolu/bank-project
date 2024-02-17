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

import com.mysql.jdbc.Driver;
@WebServlet("/MobileTranscation1")
public class MobileTranscation1 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String rmobile=req.getParameter("mobile");
		
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		
		String query="select * from bank where mob_number=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			
			ps.setString(1, rmobile);
			ResultSet rs=ps.executeQuery();
			HttpSession session=req.getSession();
			PrintWriter w=resp.getWriter();
			if(rs.next())
			{
		
				int damount=rs.getInt(5);
				String sname=rs.getString(2);
				session.setAttribute("damount",damount);
				session.setAttribute("mobile",rmobile);
			
				RequestDispatcher dispatcher=req.getRequestDispatcher("sendamount.html");
				dispatcher.include(req, resp);
				
			}
			else
			{
				RequestDispatcher dispatcher=req.getRequestDispatcher("MobileTranscation1.html");
				dispatcher.include(req, resp);
				w.println("Invalid mobile number");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
