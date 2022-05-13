package lr5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet(name = "CustomerController", urlPatterns = "/processcustomer")
public class CustomerController extends HttpServlet {
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        var firstname = request.getParameter("firstname");
        var lastname  = request.getParameter("lastname");
        var email     = request.getParameter("email");
        
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname",  lastname);
        request.setAttribute("email",     email);

        var violations = new ArrayList<String>();
        if (firstname.isBlank()) violations.add("First name required");
        if (lastname .isBlank()) violations.add("Last name required");
        if (email    .isBlank()) violations.add("Email required");
        
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."
                            + "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@" 
                            + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        if (!Pattern.compile(regexPattern)
                    .matcher(email)
                    .matches()
                    ) violations.add("Bad email format");
        
        String url;
        if (!violations.isEmpty()) { request.setAttribute( "violations"
                                                         , violations);
                                     url = "/";
        } else url = "/welcome.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
