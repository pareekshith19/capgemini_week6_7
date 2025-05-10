package Tomcat2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public LoginServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    String n = request.getParameter("txtName");
	    String p = request.getParameter("txtpwd");

	    // Name validation: starts with capital and at least 3 characters
	    if (n == null || !n.matches("[A-Z][a-zA-Z]{2,}")) {
	        out.println("<font color='red' size='5'>Invalid Name!<br>");
	        out.println("Name must start with a capital letter and be at least 3 characters long.</font><br>");
	        out.println("<a href='login.jsp'>Try Again</a>");
	        return;
	    }
	 // Password validation: min 8 chars, 1 uppercase, 1 digit, exactly 1 special character
	    if (p == null || !p.matches("^(?=.*[A-Z])(?=.*\\d)(?=[^\\W_]*\\W[^\\W_]*$).{8,}$")) {
	        out.println("<font color='red' size='5'>Invalid Password!<br>");
	        out.println("Password must be at least 8 characters long,<br>");
	        out.println("have 1 uppercase letter, 1 digit, and exactly 1 special character.</font><br>");
	        out.println("<a href='login.jsp'>Try Again</a>");
	        return;
	    }


	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "1234");
	        PreparedStatement ps = con.prepareStatement("select uname from login where uname=? and password=?");
	        ps.setString(1, n);
	        ps.setString(2, p);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
	            rd.forward(request, response);
	        } else {
	            out.println("<font color='red' size='5'>Login Failed!!</font><br>");
	            out.println("<a href='login.jsp'>Try AGAIN!!</a>");
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

}
