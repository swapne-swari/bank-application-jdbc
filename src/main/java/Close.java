

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
 * Servlet implementation class Close
 */
@WebServlet("/Close")
public class Close extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Close() {
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
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bdb","bdb");
			PreparedStatement ps = con.prepareStatement("Delete from sdfcbank where account_no=? and name=?and password=?");
			ps.setLong(1, account_no);
			ps.setString(2, name);
			ps.setString(3, password);

			int i=ps.executeUpdate();
			out.print(i + "One User Record Succesfully Deleted");
			con.close();

		}
		catch(Exception ex)
		{
			out.print(ex);
			
		}
	}
	}

