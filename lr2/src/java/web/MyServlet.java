/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import jakarta.annotation.Resource;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.logging.Level;

import lombok.extern.java.Log;

/**
 *
 * @author dazzlemon
 */
@Log
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {
    @Resource(mappedName="myConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName="myDestinationResource")
    private Queue destinationQueue;

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
        var numberStr = request.getParameter("number");
        Double number;
        try {
            number = Double.parseDouble(numberStr);
        } catch (NumberFormatException e) {
            response.setContentType("text/html;charset=UTF-8");
            try (var out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>BAD STRING</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + numberStr + " is not a number!</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }
        var squared = number * number;
        
        try (
            var connection = connectionFactory.createConnection();
            var session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);
            var messageProducer = session.createProducer(
                destinationQueue)
        ) {
            var textMessage = session.createTextMessage();
            textMessage.setText(numberStr);
            messageProducer.send(textMessage);
            log.log(Level.INFO, "Message sent: \"{0}\"", numberStr);
        } catch (JMSException e) {
            log.throwing("MyServlet", "processRequest", e);
        }
                
        response.setContentType("text/html;charset=UTF-8");
        try (var out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + numberStr + "^2=" + squared + "</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + numberStr + " squared is " + squared + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
