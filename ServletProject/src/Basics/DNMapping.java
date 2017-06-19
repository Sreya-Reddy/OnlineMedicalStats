package Basics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class DNMapping
 */
@WebServlet("/DNMapping")
public class DNMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DNMapping() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String d_id=request.getParameter("d_id");
		String n_id=request.getParameter("n_id");
		int count=0;
		String roled=null,rolen=null;
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			Statement stmt=(Statement)con.createStatement();
			String query=("SELECT * FROM user where id="+d_id);
			ResultSet rs=(ResultSet)stmt.executeQuery(query);
			 while(rs.next())
	            {
				 roled = rs.getString("role");
			    count++;
	            }	
			 if(roled.compareTo("doctor")==0)
			 {
				 String query1=("SELECT * FROM user where id="+n_id);
					ResultSet rs1=(ResultSet)stmt.executeQuery(query1);
					 while(rs1.next())
			            {
						 rolen = rs1.getString("role");
					    count++;
			            }	
					 if(rolen.compareTo("nurse")==0)
					 {
						 int res2=stmt.executeUpdate("Insert into map values ("+n_id+","+d_id+")");
						 if(res2!=0)
						 {
							 response.sendRedirect("success.html");
						 }
							else
								 response.sendRedirect("failure.html");
							 
					 }
					 else 
					 {
						 response.sendRedirect("failure.html");
					 }
			 }
			 else {
				 response.sendRedirect("failure.html");
			 }
				
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
