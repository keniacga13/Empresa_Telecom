/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pw.sap.servlets.inventarios;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pw.sap.db.Conexion;
import javax.servlet.http.HttpSession;

/**
 *
 * @author montse
 */
@WebServlet(name = "ConsultaE_Salidas", urlPatterns = {"/ConsultaE_Salidas"})
public class ConsultaE_Salidas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        Conexion c=new Conexion();
        
        
        ArrayList l=c.consultaESalidas(Integer.parseInt(request.getParameter("CoBa")),Integer.parseInt(request.getParameter("IDSalida")),Integer.parseInt(request.getParameter("IDOrdenCom")));
        request.setAttribute("id_producto", l.get(0));
        request.setAttribute("id_salida", l.get(1));
        request.setAttribute("id_ordenventa", l.get(2));
        request.setAttribute("fecha_salida", l.get(3));
        request.setAttribute("cantidad_vendida", l.get(4));
       //registro para log
        HttpSession sesion=request.getSession(true);
        System.out.println("sesion usuario:"+sesion.getAttribute("usuario"));
        System.out.println("sesion usuario:"+sesion.getAttribute("area"));
        c.insercionRegistro((int)sesion.getAttribute("usuario"), (String)sesion.getAttribute("area"), "Consulta especifica de salidas");        
        
        request.getRequestDispatcher("Inventarios/Inventario/salidas.jsp").forward(request, response);
        
        
        
        
        
        
        try (PrintWriter out = response.getWriter()) {
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Consulta_Salidas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Consulta_Salidas at " + request.getContextPath() + "</h1>");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta_Salidas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Salidas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consulta_Salidas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Salidas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
