import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "your_password");

            PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (name, date, status) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, status);
            ps.executeUpdate();

            request.getRequestDispatcher("attendance-success.jsp").forward(request, response);
            con.close();
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
