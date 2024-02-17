package com.jsp.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Sendamount1")
public class Sendamount1 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ramount=req.getParameter("amount");
		int recamount=Integer.parseInt(ramount);
		HttpSession session=req.getSession();
		int dramount=(int)session.getAttribute("damount");
		String rmobile=(String)session.getAttribute("mobile");
		String password=(String)session.getAttribute("password");
		PrintWriter w=resp.getWriter();
		if(recamount>0) {
			if(dramount>=recamount)
			{
				int sub=dramount-recamount;
				String update="update bank set amount=? where mob_number=?";
				String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection =DriverManager.getConnection(url);
					PreparedStatement ps=connection.prepareStatement(update);
					ps.setInt(1, sub);
					ps.setString(2, rmobile);
					int result=ps.executeUpdate();
					
					if(result>=0)
					{
						RequestDispatcher dispatcher=req.getRequestDispatcher("Welcome.html");
						w.println("<center><h1>Transcation succesfull...!!</h1></center>");
					}
					else
					{
						RequestDispatcher dispatcher=req.getRequestDispatcher("Welcome.html");
						w.println("<center><h1>Invalid Details</h1></center>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			
		}
	}
}
