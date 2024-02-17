package com.jsp.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Credit")
public class Credit extends HttpServlet {
          @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        	  String mobile=req.getParameter("mobile");
      		String password=req.getParameter("password");
      		String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";
      		
      		String select="select * from bank where mob_number=?and paassword=?";
      		
      		Connection connection;
      		try {
      			Class.forName("com.mysql.jdbc.Driver");
      			connection = DriverManager.getConnection(url);
      			PreparedStatement ps=connection.prepareStatement(select);
      			
      			ps.setString(1, mobile);
      			ps.setString(2, password);
      			ResultSet rs=ps.executeQuery();
      			if(rs.next())
      			{
      				RequestDispatcher dispatcher=req.getRequestDispatcher("creditamount.html");
      				dispatcher.include(req, resp);
      				
      			}
      		}catch (Exception e) {
				// TODO: handle exception
			}
        }
}
