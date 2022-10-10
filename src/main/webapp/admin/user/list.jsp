<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
         import="java.util.*,me.anant.PMS.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/includes/head.jsp"%>
</head>
<body style="background-color: #f8f9fa !important;">
<%@include file="/includes/header.jsp"%>
<main role="main" class="container"> <%@include file="/includes/msg.jsp"%>
    <div class="card">
        <div class="card-header text-white shadow bg-dark">
            <h2 class="float-left">User</h2>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Role</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<User> ul = (List<User>) request.getAttribute("uList");
                    for (User u : ul)
                    {
                %>
                <tr>
                    <th scope="row"><%=u.getId()%></th>
                    <td><%=u.getName()%></td>
                    <td><%=u.getEmail()%></td>
                    <td><%=u.getRole()%></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <br><br><br>
</main>
<%@include file="/includes/footer.jsp"%>
</body>
</html>