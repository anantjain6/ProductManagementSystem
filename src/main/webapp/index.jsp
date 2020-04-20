<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
if(session.getAttribute("email") == null) {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/includes/head.jsp"%>
<style type="text/css">
body {
      background: url("bg.jpg")no-repeat center center;
      background-size: cover;
}
</style>
</head>
<body>
    <%@include file="/includes/header.jsp"%>
    <main role="main" class="container">
    <%@include file="/includes/msg.jsp"%>
    <div class="row">

        <div class="col-md-4 col-sm-4 col-xs-12"></div>
        <div class="col-md-4 col-sm-4 col-xs-12 ">
            <form action="Login" method="post">
                <div class="animated zoomIn shadow p-4 mb-5 bg-white rounded border border-primary">
                    <div class="mx-auto">
                        <h1 class="font-weight-bold text-center">Login</h1>
                    </div>
                    <div class="form-group">
                        <label for="username">E-Mail</label> <input type="email" class="form-control" id="username" required name="email"
                            autocomplete="off" placeholder="E-Mail">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password</label> <input type="password" class="form-control" id="pwd" name="password" required
                            placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-success btn-block">Login</button>
                </div>
            </form>
        </div>
        <div class="col-md-4 col-sm-4 col-xs-12"></div>

    </div>
    </main>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>
<%
} else {
 if((int)session.getAttribute("usertype") == 0) {
     response.sendRedirect("employee/home.jsp");
 } else if((int)session.getAttribute("usertype") == 1) {
     response.sendRedirect("technician/home.jsp");
 } else {
     response.sendRedirect("admin/home.jsp");
 } 
}
%>