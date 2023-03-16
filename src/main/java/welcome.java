

import jakarta.servlet.ServletException;
import java.sql.*;
import java.lang.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class welcome
 */
public class welcome extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		
		String m=request.getParameter("name");
		String pwd=request.getParameter("psw");
		//pw.println(m);	
		
		//void createConnection() throws SQLException, ClassNotFoundException {
		    //Connection connection=null;
				Connection connection = null;
				try {
					// below two lines are used for connectivity.
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/foodreciepe",
						"root", "123456");
					Statement statement;
					statement = connection.createStatement();
					PreparedStatement ps=connection.prepareStatement("select password from user where email=?");
					ps.setString(1,m);
					
					ResultSet resultSet=ps.executeQuery();
					//resultSet = statement.executeQuery(
						//"select * from student ");
					String email,pass="-1";
					/*
					while (resultSet.next()) {
						//email = resultSet.getString("email").trim();
						pass=resultSet.getString("password").trim();
						pw.println("username: " + m+"\n password:"+pass);	
					}*/
					if(resultSet.next()) {
					pass=resultSet.getString("password").trim();
					pw.println("user name: " + m+"\n original password:"+pass+"\nentered :"+pwd);
					if(pass.equals(pwd))
					{
					//Thread.sleep(1);
					pw.println("welcome:"+m);
					response.sendRedirect("baseapp.html");
					}
					else
					{response.sendRedirect("messege_fail_login.html");
						//pw.println("login fail");
						
					}
					}
					else {
						
						response.sendRedirect("usernotfound.html");
					}
					
					
					
					resultSet.close();
					statement.close();
					connection.close();
				}
				catch (Exception exception) {
					//response.sendRedirect("login.html");
					pw.println("invalid username or password");
					
					System.out.println("error");
					System.out.println(exception);
					
				}
			//}	
		
	}

}
