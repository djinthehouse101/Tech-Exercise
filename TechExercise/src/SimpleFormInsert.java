
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class SimpleFormInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("name");
      String address = request.getParameter("address");
      String weeklyHours = request.getParameter("weeklyHours");
      String hourlyPay = request.getParameter("hourlyPay");
      String paycheck = request.getParameter("paycheck");

      Connection connection = null;
      String insertSql = " INSERT INTO employee (id, name, address, weeklyHours, hourlyPay, paycheck) values (default, ?, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, userName);
         preparedStmt.setString(2, address);
         preparedStmt.setString(3, weeklyHours);
         preparedStmt.setString(4, hourlyPay);
         preparedStmt.setString(5, paycheck);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Name</b>: " + userName + "\n" + //
            "  <li><b>Address</b>: " + address + "\n" + //
            "  <li><b>Weekly Hours</b>: " + weeklyHours + "\n" + //
            "  <li><b>Hourly Pay</b>: " + hourlyPay + "\n" + //
            "  <li><b>Paycheck</b>: " + paycheck + "\n" + //

            "</ul>\n");

      out.println("<a href=/TechExercise/simpleFormSearch.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
