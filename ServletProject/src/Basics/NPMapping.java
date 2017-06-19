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
 * Servlet implementation class NPMapping
 */
@WebServlet("/NPMapping")
public class NPMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NPMapping() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String n_id=request.getParameter("n_id");
		String p_id=request.getParameter("p_id");
		int id=0,count=0;
		String rolen=null;
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			Statement stmt=(Statement)con.createStatement();
			String query=("SELECT * FROM user where id="+n_id);
			ResultSet rs=(ResultSet)stmt.executeQuery(query);
			 while(rs.next())
	            {
				 rolen = rs.getString("role");
	            }	
			 if(rolen.compareTo("nurse")==0)
			 {
				 String query1=("SELECT * FROM patient where p_id="+p_id);
					ResultSet rs1=(ResultSet)stmt.executeQuery(query1);
					 while(rs1.next())
			            {
						 id = rs1.getInt("p_id");
					    count++;
			            }
					 System.out.println("Checked patient");
					 if(count!=0)
					 {
						 int res2=stmt.executeUpdate("update patient set n_id="+n_id+" where p_id="+p_id);
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
