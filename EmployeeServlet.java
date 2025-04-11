import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;

public class EmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");
        int empId = idParam != null && !idParam.isEmpty() ? Integer.parseInt(idParam) : -1;

        try {
            Properties props = new Properties();
            InputStream input = getServletContext().getResourceAsStream("/WEB-INF/db-config.properties");
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            String query = empId != -1 ? "SELECT * FROM employees WHERE id = ?" : "SELECT * FROM employees";
            PreparedStatement stmt = con.prepareStatement(query);
            if (empId != -1) stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            out.println("<h2>Employee List</h2>");
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + "<br>");
                out.println("Name: " + rs.getString("name") + "<br>");
                out.println("Department: " + rs.getString("department") + "<br>");
                out.println("Email: " + rs.getString("email") + "<br><br>");
            }

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
