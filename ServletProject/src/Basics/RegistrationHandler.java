package Basics;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class RegistrationHandler
 */
@WebServlet("/RegistrationHandler")
public class RegistrationHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		String role=request.getParameter("role");
		int id=0,count=0;
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			Statement stmt=(Statement)con.createStatement();
			int res=stmt.executeUpdate("Insert into user(name,username,password,role) values ('"+name+"','"+uname+"','"+pwd+"','"+role+"')");
				 if(res!=0)
				 {
					 String query1=("select id from user where name like'"+name+"'");
			            ResultSet rs1=(ResultSet)stmt.executeQuery(query1);			           
			            while(rs1.next())
	                    {
	       	             id=rs1.getInt("id");
	       	             count++;
	       	               }	
			            request.setAttribute("id", id);
			            RequestDispatcher view=request.getRequestDispatcher("/success.jsp");
			            view.forward(request, response);
			            
				 }
					else
						response.sendRedirect("failure.jsp"); 
			
            stmt.close();
            con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
