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
    <% Order order = (Order) request.getAttribute("order"); %>
    <div class="card">
        <div class="card-header text-white shadow bg-dark">
            <h2 class="float-left">Product Report</h2>
        </div>
        <div class="card-body">
        	Order ID: <%= order.getId() %><br>
        	Order Status: <%= order.getStatus() %><br>
        	Order Date: <%= order.getCreateDateTime() %><br>
        	<hr>
        	<br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Qty</th>
                        <th scope="col">Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    	float sumAmu = 0;
                    	Set<OrderProduct> opList = order.getOrderProduct();
                    	for (OrderProduct op : opList)
                        {
                        %>
                        <tr>
                            <th scope="row"><%=op.getProduct().getProductId()%></th>
                            <td><%=op.getProduct().getProductName()%></td>
                            <td><%= op.getProduct().getProductPrice() %></td>
                            <td><%= op.getBuyqty() %></td>
                            <td><%= op.getBuyqty() * op.getProduct().getProductPrice() %></td>
                        </tr>
                        <%   
                        	sumAmu = sumAmu + op.getBuyqty() * op.getProduct().getProductPrice();
                        }
                    %>
                    <tr><td  colspan="4" class="text-center"><b>Total Amount</b></td><td>Rs. <%= sumAmu %></td></tr>
                </tbody>
            </table>
        </div>
    </div>
    <br><br><br>
    </main>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>