

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signupnow
 */
public class signupnow extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
		
		String p,q;
		p=request.getParameter("email");
		q=request.getParameter("psw");
		pw.println(p);
		pw.println(q);
		//p=request.getParameter("mail");
		Connection connection = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/foodreciepe",
				"root", "123456");

			

			Statement st= connection.createStatement();
			String ps="insert into user values('"+p+"','"+q+"');";
			//pw.println(ps);
			try {
				st.executeUpdate(ps);
				pw.println("registered sucess fully");
			    }
			catch(Exception e)
			{
				pw.println("already exists in catch");
			}
			
			
			
			st.close();
			connection.close();
		}
		catch (Exception exception) {
			System.out.println(exception);
			String d=exception.getMessage();
			if(d.charAt(0)=='D') {
				pw.println("username already exists..");
			}
			
		}
	
	}

}
