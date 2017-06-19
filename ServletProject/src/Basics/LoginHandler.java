package Basics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class LoginHandler
 */
@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName=request.getParameter("uname");
		String password=request.getParameter("pwd");
		 String role=null,name=null,name1=null;
		 int count=0,id=0,count1=0,id1=0;
		 		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			Statement stmt=(Statement)con.createStatement();
			Statement stmt1=(Statement)con.createStatement();
			String query=("SELECT * FROM user where username = '"+userName+"'AND password='"+password+"'");
			ResultSet rs=(ResultSet)stmt.executeQuery(query);
			 while(rs.next())
	            {
				 name = rs.getString("name");
		             role = rs.getString("role");
		             id=rs.getInt("id");
		             count++;
	            }
			 
			 if(count!=0)
			 {
				 if(role.compareTo("admin")==0)
						 {
					 response.sendRedirect("Admin.html");
						 }
				 else if(role.compareTo("doctor")==0)
				 {
					 /*select m.nid from map m , user u where m.d_id=u.id
					  * select p.p_id from patient p ,map m where p.n_id=m.nid
					  * SELECT * FROM patient p ,map m where p.n_id in */ 
					 String query1=("select * from patient where n_id in(select nid from map where d_id="+id+")");
					 ResultSet rs1=(ResultSet)stmt1.executeQuery(query1);
					 ArrayList Rows = new ArrayList();
					 while(rs1.next())
			            {
						 name1= rs1.getString("name");
				             count1++;
				             
				           /*  ArrayList row = new ArrayList();
				             for (int i = 1; i <= 7 ; i++){
				                 row.add(rs1.getString(i));
				             }
				             Rows.add(row);
				             System.out.println("Patient names"+name1);*/
			            }
					 request.setAttribute("name",name1);
			            RequestDispatcher view=request.getRequestDispatcher("/Doc.jsp");
			            view.forward(request, response);
					// request.setAttribute("propertyList", Rows);
					// RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Doc.jsp");
					 //requestDispatcher.forward(request,response);
					 
					 
					 if(count1==0)
					 {
						 System.out.println("No patients assigned");
					 }
					 // response.sendRedirect("Doc.jsp");
					 
				 }
				 else if(role.compareTo("nurse")==0)
				 {
					 response.sendRedirect("Nurse.html");
				 }
				 
			 }
			 else
			 {
				 response.sendRedirect("failure.jsp"); 
			 }
			 stmt1.close();
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
