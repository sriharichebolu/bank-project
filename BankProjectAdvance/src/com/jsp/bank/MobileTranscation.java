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
@WebServlet("/MobileTranscation")
public class MobileTranscation extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sendermobile=req.getParameter("mobile");
		String password=req.getParameter("password");
		
		String query="select * from bank where mob_number=? and paassword=?";
		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
            PreparedStatement ps=connection.prepareStatement(query);
			
			ps.setString(1, sendermobile);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			PrintWriter w=resp.getWriter();
			if(rs.next())
			{
				int sdamount=rs.getInt(5);
				HttpSession session=req.getSession();
				session.setAttribute("sdamount", sdamount);
				session.setAttribute("sendermobile",sendermobile );
				RequestDispatcher dispatcher=req.getRequestDispatcher("MobileTranscation1.html");
				dispatcher.include(req, resp);
				
			}
			else
			{
				RequestDispatcher dispatcher=req.getRequestDispatcher("MobileTranscation.html");
				dispatcher.include(req, resp);
				w.println("<center><h1>Invalid Details</h1></center>");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
