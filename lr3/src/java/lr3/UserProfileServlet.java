package lr3;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.extern.java.Log;

@WebServlet(urlPatterns = {"/user"})
@Log
public class UserProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            var context = new InitialContext();
            var userProfile = (UserProfile)
                context.lookup("userProfileSessionBean");
            
            String[] friendNames = {
                "Nola Neal",
                "Katrina Bridges",
                "Danny Gregory",
                "Veronica Le",
            };
            
            for (var name : friendNames) {
                userProfile.addFriend(name);
            }
            
            var friends = userProfile.getFriends();
            
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                var userName = userProfile.getName();
                out.println("<title>" + (userName == null ? "anonymous"
                                                          : userName)
                                      + " user page</title>");            
                out.println("</head>");
                out.println("<body>");
                for (var name : friends) {
                    out.println("<h4>" + name + "</h4>"); 
                }
                out.println("</body>");
                out.println("</html>");
            }
            userProfile.removeFriend(3);
            log.info("friends: " + String.join(", ", userProfile.getFriends()));
        } catch (NamingException e) {
            log.throwing("UserProfileServlet", "processRequest", e);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
