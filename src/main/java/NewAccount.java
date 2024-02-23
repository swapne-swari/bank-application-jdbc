

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewAccount
 */
@WebServlet("/NewAccount")
public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccount() {
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
		PrintWriter out=response.getWriter();
		
		long account_no=Long.parseLong(request.getParameter("accno"));
		String name=request.getParameter("uname");
		String password=request.getParameter("psw");
		String confirm_password=request.getParameter("cpsw");
		double amount=Double.parseDouble(request.getParameter("amt"));
		String address=request.getParameter("add");
		long mobile_no=Long.parseLong(request.getParameter("mno"));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bdb","bdb"); 
			PreparedStatement ps=con.prepareStatement("insert into sdfcbank values(?,?,?,?,?,?)");
			if(password.equals(confirm_password))
			{
				ps.setLong(1, account_no);
			ps.setString(2,name);
			ps.setString(3,password);
			ps.setDouble(4,amount);
			ps.setString(5,address);
			ps.setLong(6,mobile_no);
			int i=ps.executeUpdate();
			
			out.println(i+"new user successfully registered.....");
			
			
			con.close();
			}
		}catch(Exception ex)
		{
			out.println(ex);
		}





		
	}

}
