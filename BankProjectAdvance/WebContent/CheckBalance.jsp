<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table{
border: 2px solid black;
border-collapse: collapse;

}
td{
border:2px solid black;
border-collapse: collapse;
}
</style>
</head>
<body>
<center>
<form action="CheckBalance.jsp">
<input placeholder="Enter Your password" name="pass"><br><br>
<input type="submit">
</form>
</center>
<%
String password=request.getParameter("pass");

String url="jdbc:mysql://localhost:3306/teca44?user=root&password=12345";

String select="select * from bank where paassword=?";
String name="";
String mobile="";

int amount=0;
try{
	Class.forName("com.mysql.jdbc.Driver");
    Connection connection=DriverManager.getConnection(url);
	PreparedStatement ps=connection.prepareStatement(select);
	ps.setString(1, password);
	ResultSet rs=ps.executeQuery();
	PrintWriter w=response.getWriter();
	if(rs.next())
	{
	   name=rs.getString(2);
	    mobile=rs.getString(3);
	    amount=rs.getInt(5);
	}
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
<table>
<tr>
<td><%="name" %></td>
<td><%="Mobile" %></td>
<<td><%="Amount" %></td>
</tr>
<tr>
<td><%=name %></td>
<br>
<td><%=mobile %></td>
<br>
<td><%=amount %></td>
</tr>
</table>
</center>
</body>
</html>