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
            <h2 class="float-left">Product</h2>
            <a class="anchor btn-success btn-lg float-right" href="add" style="text-decoration: none;"> <i class="fa fa-plus"></i> Add
                Product
            </a>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Qty</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    	List<Product> pl = (List<Product>) request.getAttribute("pList");
                    	for (Product p : pl)
                        {
                        %>
                        <tr>
                            <th scope="row"><%=p.getProductId()%></th>
                            <td><%=p.getProductName()%></td>
                            <td>Rs. <%= p.getProductPrice() %></td>
                            <td><%= p.getProductQty() %></td>
                            <td>
                            <a class="btn btn-success" href="update?id=<%=p.getProductId()%>" role="button">Edit</a>
                            <a onclick="return confirm('Are you sure you want to delete it?');" class="btn btn-danger" href="delete?id=<%= p.getProductId() %>" role="button">Delete</a>
                            </td>
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