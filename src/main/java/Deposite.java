

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deposite
 */
@WebServlet("/Deposite")
public class Deposite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposite() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		long ACCOUNT_NO =Long.parseLong(request.getParameter("accno"));
		String name=request.getParameter("uname");
		String password=request.getParameter("psw");

		double d_amount=Double.parseDouble(request.getParameter("amt"));


		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bdb","bdb");


		PreparedStatement ps=con.prepareStatement("select * from sdfcbank where ACCOUNT_NO=? and name=? and password=?");
		ps.setLong(1,ACCOUNT_NO);
		ps.setString(2,name);
		ps.setString(3,password);

		ResultSet rs=ps.executeQuery();
		double amount=0.0;
		if(rs.next())
		{
		amount=rs.getDouble(4);
		}
		  out.print("Before deposite balance is "+amount);
		  out.print("<br>");
		  out.print(" deposit Amount is "+d_amount);
		  out.print("<br>");
		 amount=d_amount+amount;

		    PreparedStatement ps1=con.prepareStatement("update sdfcbank set amount =? where ACCOUNT_NO=?");

		          ps1.setDouble(1,amount);
		        ps1.setLong(2,ACCOUNT_NO);
		       int i=ps1.executeUpdate();

		            out.print(i+"Account is updated");
		       out.print("After deposit amount is "+amount);

		con.close();
		}
		catch(Exception ex)
		{
		out.print(ex);
		}

	}

}
