/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package org.health.administration;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import static jakarta.servlet.jsp.tagext.Tag.SKIP_BODY;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static jdk.internal.vm.vector.VectorSupport.insert;

/**
 *
 * @author ogrey
 */
public class All_bookings extends SimpleTagSupport {
    
   //JBDC driver name and database URL
   private final String driver = "com.mysql.jdbc.Driver";

   private final String database_type = "mysql";

   private final String database_url = "\"jdbc:mysql://localhost:3306/vaccinationtracker\"";

   private final String database_username = "root";

   private final String database_password = "";
   
   private String table;
   public void setTable(String table){
       this.table = table;
   }

    private String nin;
    private String email;
    public void setNin(String nin) {
        this.nin = nin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        Statement stmt = null;
        try{ 
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinationtracker","root","");
           Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
            st = conn.createStatement();
            String sql = "SELECT * FROM bookings";
            ResultSet rs = st.executeQuery(sql);
                
                out.println("<table class='table-auto border border-2 p-10'>");
                out.println("<thead class='border border-4 p-10'>");
                out.println("<tr class='border border-4 px-5 py-2'>");
                out.println("<th class='border border-4 px-10 py-2'>NIN</th>");
                out.println("<th class='border border-4 px-20 py-2'>EMAILS</th>");
                out.println("<th class='border border-4 px-20 py-2'>NAME</th>");
                out.println("<th class='border border-4 px-5 py-2'>PREFERRED DATE FOR VACCINATION</th>");
                out.println("<th class='border border-4 px-5 py-2'>PREFERRED HOSPITAL</th>");
                out.println("<th class='border border-4 px-5 py-2'>PREFFERED VACCINE</th>");
                out.println("</tr>");
                out.println("</thead>");
                while(rs.next()){
                out.println("<tbody class='border border-4'>");
                out.println("<tr class='border border-4'>");
                out.println("<th class='border border-4'>");
                out.println(rs.getString("nin"));
                out.println("</th>");
                out.println("<th class='border border-4'>");
                out.println(rs.getString("email"));
                out.println("</th>");
                out.println("<th class='border border-4'>");
                out.println(rs.getString("name"));
                out.println("</th>");
                out.println("<th class='border border-4'>");
                out.println(rs.getString("date"));
                out.println("</th>");
                out.println("<th class='border border-4'>");
                out.println(rs.getString("hospital"));
                out.println("</th>");
                out.println("<th class='border border-4'>");
                out.println(rs.getString("vaccine"));
                out.println("</th>");
                out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
//            stmt = conn.createStatement();
//           
//            String sql = "SELECT COUNT(*) FROM '"+table+"'";
//
//            int no_of_patients = stmt.executeUpdate(sql);
//            out.println(no_of_patients);
       
       }catch(ClassNotFoundException | SQLException e){
                out.println(e);
       }
   }
}