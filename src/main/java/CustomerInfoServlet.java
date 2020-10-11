import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CustomerInfoServlet", urlPatterns = {"customerInfo"}, loadOnStartup = 1)
public class CustomerInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        StringBuilder htmlBuilder = new StringBuilder();
        final String DB_URL = "jdbc:mysql://localhost:3306/ITCS6112";
        final String USER = "root";
        final String PASS = "password";
        Connection conn = null;

        JSONArray jarr = new JSONArray();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");


            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database...");

            String query = "SELECT * FROM ITCS6112.CustomerDetails";

            // create the java statement
            Statement st = conn.createStatement();

// execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);


            htmlBuilder.append("<html><head><title>New Page</title></head><body>");
            htmlBuilder.append("<head><title>Hello World</title></head>");
            htmlBuilder.append("<body>");
            htmlBuilder.append("<table> <tr> <td>LastName</td><td>FirstName</td> <td>Address</td> <td>City</td>  <td>State</td>  <td>ZipCode</td>  <td>Country</td>  </tr>");

            htmlBuilder.append("<table> <tr> <td>" +
                    "LastName </td><td>" +
                    "FirstName</td> <td>" +
                    "Address</td> <td>" +
                    "City</td>  <td>" +
                    "State</td>  <td>" +
                    "ZipCode</td>  <td>" +
                    "Country</td>  </tr>");


            while (rs.next()) {


                htmlBuilder.append("<table> <tr> <td>" +
                        rs.getString("LastName") +" </td><td>" +
                        rs.getString("FirstName") +"</td> <td>" +
                        rs.getString("Address") +"</td> <td>" +
                        rs.getString("City") +"</td>  <td>" +
                        rs.getString("State") +"</td>  <td>" +
                        rs.getString("ZipCode") +"</td>  <td>" +
                        rs.getString("Country") +"</td>  </tr>");


            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new Error("Error", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        response.getWriter().write(htmlBuilder.toString());
    }
}