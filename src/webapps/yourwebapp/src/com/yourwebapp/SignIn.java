/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 *
 * @author Donnie
 */
public class SignIn extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String DBCTX = "";
        Context ctx = null;
        DataSource ds = null;
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        String Login = "";
        String Password = "";
        
        
        try {
            String RootPath = getServletContext().getInitParameter("RootPath");
            String RootAccessPath = getServletContext().getInitParameter("RootAccessPath");
            
            
            if (request.getParameter("l") != null) {
                if (!request.getParameter("l").trim().equals("")) {
                    Login = request.getParameter("l").trim();
                }
            }
            if (request.getParameter("p") != null) {
                if (!request.getParameter("p").trim().equals("")) {
                    Password = request.getParameter("p").trim();
                }
            }            
            
            
            
            DBCTX = getServletContext().getInitParameter("DBCTX");
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(DBCTX);
            conn = ds.getConnection();
            
            cstmt = conn.prepareCall("{Call spUserLogin(?,?)}");
            cstmt.setString(1, Login);
            cstmt.setString(2, Password);
            boolean r = cstmt.execute();
            rs = cstmt.getResultSet();


            if (rs.next()) {
                Cookie[] cc = request.getCookies();
                
                for(int i = 0; i < cc.length; i++) {
                    Cookie tc = cc[i];
                    tc.setPath(RootPath);
                    tc.setMaxAge(0);
                    response.addCookie(tc);
                }
                
                
                Cookie c = null;
                c = new Cookie("PersonKey", String.valueOf(rs.getInt("PersonKey")));
                c.setMaxAge(60*60*24*7);
                c.setPath(RootPath);
                response.addCookie(c);
                c = null;
                
                
                out.print("1");
            } else {
                Cookie[] cc = request.getCookies();

                for(int i = 0; i < cc.length; i++) {
                    Cookie tc = cc[i];
                    tc.setPath(RootPath);
                    tc.setMaxAge(0);
                    response.addCookie(tc);
                }
                
                
                out.print("0");
            }
        }
        catch(Exception e) {
            out.print("-1");
            
            System.out.println(e.toString());
        }
        finally {
            try {
                out.close();
                if (rs!=null) rs.close();
                if (cstmt!=null) cstmt.close();
                if (conn!=null) conn.close();
                if (ctx!=null) ctx=null;
            } catch(Exception __ex) {}
        }
    }

    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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













































