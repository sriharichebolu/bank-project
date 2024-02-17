package com.jsp.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Amount")
public class Amount extends HttpServlet {
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String amount=req.getParameter("amount");
		
		int tamount=Integer.parseInt(amount);
		HttpSession session=req.getSession();
		int damount=(int)session.getAttribute("damount");
		String mobilenumber=(String)session.getAttribute("mobile");
		String password=(String)session.getAttribute("password");
		PrintWriter w=resp.getWriter();
		if(tamount>0)
		{
			if(damount>=tamount) {
				int sub=damount-tamount;
				
				String update="update bank set amount=? where mob_number=?";
				
				String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection connection=DriverManager.getConnection(url);
					PreparedStatement ps=connection.prepareStatement(update);
					ps.setInt(1, sub);
					ps.setString(2, mobilenumber);
					
					
					int result=ps.executeUpdate();
					if (result>0) {
						RequestDispatcher dispatcher=req.getRequestDispatcher("Welcome.html");
						dispatcher.include(req, resp);
						w.println("<center><h1>WithDraw Succesfull.....!</h1></center>");
					} else {
						RequestDispatcher dispatcher=req.getRequestDispatcher("Welcome.html");
						dispatcher.include(req, resp);
						w.println("<center><h1>Server Busy</h1></center>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				RequestDispatcher dispatcher=req.getRequestDispatcher("Amount.html");
				dispatcher.include(req, resp);
				w.println("<center><h1>Insufficent Balance</h1></center>");
			}
		 
	}  else
		{
			RequestDispatcher dispatcher=req.getRequestDispatcher("Amount.html");
			dispatcher.include(req, resp);
			w.println("<center><h1>Invalid Amount</h1></center>");
		}
	}
}
