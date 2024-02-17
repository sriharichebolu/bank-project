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
@WebServlet("/SendAmount")
public class SendAmount extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String amount=req.getParameter("amount");
		
		int samount=Integer.parseInt(amount);
		HttpSession session=req.getSession();
		int damount=(int)session.getAttribute("damount");
		String mobile=(String)session.getAttribute("mobile");
		String smobile=(String)session.getAttribute("sendermobile");
		String password=(String)session.getAttribute("password");
		String name = (String) session.getAttribute("sname");
		int sdamount = (int) session.getAttribute("sdamount");
		PrintWriter w=resp.getWriter();
		if(samount>0)
		{
			if(sdamount>=samount)
			{
				
				int sub=sdamount-samount;
			    String update="update bank set amount=? where mob_number=?";
			    String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
			    
			    try {
					Connection connection=DriverManager.getConnection(url);
					PreparedStatement ps=connection.prepareStatement(update);
					ps.setInt(1, sub);
					ps.setString(2, smobile);
					int result=ps.executeUpdate();
					PrintWriter w1=resp.getWriter();
					if(result!=0)
					{
						int add=damount+samount;
						String update1="update bank set amount=? where mob_number=?";
						String url1="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
						
						PreparedStatement ps1=connection.prepareStatement(update1);
						ps1.setInt(1, add);
						ps1.setString(2, mobile);
						int result1=ps1.executeUpdate();
						if(result1!=0)
						{
							RequestDispatcher dispatcher=req.getRequestDispatcher("Welcome.html");
							dispatcher.include(req, resp);
							String snumber=mobile.substring(0, 3);
							String rnumber=mobile.substring(7, 10);
							String tot=snumber+"****"+rnumber;
							w1.println("<center><h1>Transcation Successfull...!!</h1></center>");
							w1.println("<center><h1>name:"+name+"</h1></center>");
							w1.println("<center><h1>mobile:"+tot+"</h1></center>");
							w1.println("<center><h1>Amount:"+samount+"</h1></center>"); 
							
						}
						else
						{
							RequestDispatcher dispatcher=req.getRequestDispatcher("MobileTranscation1.html");
							dispatcher.include(req, resp);
							w1.println("<center><h1>Server Busy</h1></center>");
						}
					}
					else {
						RequestDispatcher dispatcher=req.getRequestDispatcher("MobileTranscation1.html");
						dispatcher.include(req, resp);
						w1.println("<center><h1>Invalid Details</h1></center>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
