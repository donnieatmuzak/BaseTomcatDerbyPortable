<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.CallableStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Types"%>
<%@ page import="javax.sql.DataSource" %>



<%
	String AppVersion = "";
    String ImagesCommon = "";
    Context ctx = null;
    DataSource ds = null;
    Connection conn = null;
try {
    AppVersion = (getServletContext().getInitParameter("Major_Version") + "." + getServletContext().getInitParameter("Minor_Version") + "." + getServletContext().getInitParameter("Build_Version"));
    ImagesCommon = getServletContext().getInitParameter("ImagesCommon");
		
    String DBCTX = getServletContext().getInitParameter("DBCTX");    
    ctx = new InitialContext();
    ds = (DataSource) ctx.lookup(DBCTX);
    conn = ds.getConnection();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
    <title>Your Web App</title>
</head>

<body>
    

Your web app


</body>
</html>






<%

} catch (Exception ex) {
    System.out.println(ex.toString());
} finally {
    if (ctx != null) ctx = null;
    if (ds != null) ds = null;
    if (conn != null) conn.close();
    if (conn != null) conn = null;
}

%>