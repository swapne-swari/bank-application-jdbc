

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Balance
 */
@WebServlet("/Balance")
public class Balance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Balance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("Text/html");

		PrintWriter out =response.getWriter();

		long account_no = Long.parseLong(request.getParameter("accno"));

		String name= request.getParameter("uname");

		String password = request.getParameter("psw");

	

		

	   		

		try {

			out.print("<table border=3>");

			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bdb","bdb");

			

			 PreparedStatement ps=con.prepareStatement("select * from sdfcbank where account_no =? and name=? and password=? ");

	   		   ps.setLong(1, account_no );

	   		   ps.setString(2,name);

	   		   ps.setString(3, password);

	   		   

	   		   ResultSet rs=ps.executeQuery();

	   		   if(rs.next())

	   		   {

	   			   ResultSetMetaData rsmd=rs.getMetaData();

	   			   

	   			   int n=rsmd.getColumnCount();

	   			   for(int i=1;i<=n;i++)

	   			   {

	   				   out.print("<td><font color=black "+"<br>"+rsmd.getColumnName(i));

	   				}

	 			   

	   			   out.print("<tr>");

	   			ResultSet rs1=ps.executeQuery();

	               while(rs1.next())

	               {

	            	   for(int i=1;i<=n;i++)

	            	   {

	            		 out.print("<td>"+rs1.getString(i));

	            		

	            	   }

	            	   out.print("<tr>");

	            	   out.print("</table>");

	            	   

	               }					

				}

	   			   

	   		   else

	   		   {

	   			   out.print("Please Enter valid  accountnumber name and password");

	   		   }

	   	    	con.close();



		      }

		catch(Exception ex)

		{

			System.out.println(ex);

		}

			
	}

}
