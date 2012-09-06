package com.yourwebapp;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class AuditUserLogin extends HttpServlet implements Filter {
    private FilterConfig filterConfig;
    String RootPath = "";
    
    
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            if (filterConfig == null) {
                return;
            }

            RootPath = filterConfig.getServletContext().getInitParameter("RootPath");
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;

            
            Cookie[] cc = httpRequest.getCookies();
            
            
			/*
            if (!sw.getCookieValue(cc, "PersonKey", RootPath).equals("null")) {
                filterChain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(RootPath);               
            }
			*/
			
			
			httpResponse.sendRedirect(RootPath); 
			//filterChain.doFilter(request, response);
        } catch(Exception e) {
        } finally {
        }
    }   // close do filter

    public void destroy() {
        this.filterConfig = null;
    }

}   // close class
























