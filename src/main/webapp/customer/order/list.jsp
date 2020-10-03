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
            <h2 class="float-left">My Orders</h2>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Date</th>
                        <th scope="col">Item</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    	Set<Order> orderList = (Set<Order>) request.getAttribute("orderList");
                    	for (Order o : orderList)
                        {
                        %>
                        <tr>
                            <th scope="row"><a href="detail?id=<%=o.getId()%>"><%=o.getId()%></a></th>
                            <td><%=o.getCreateDateTime()%></td>
                            <%
                            float sum = 0;
                            int count = 0;
                            Set<OrderProduct> opList = o.getOrderProduct();
                            for(OrderProduct op: opList) {
                            	sum = sum + op.getProduct().getProductSellingPrice() * op.getBuyqty();
                            	count++;
                            }
                            %>
                            <td><%= count %></td>
                            <td>Rs. <%= sum %></td>
                            <td><%= o.getStatus() %></td>
                            <td><a onclick="return confirm('Are you sure that you want cancel this order?');" class="btn btn-danger" href="cancel?id=<%= o.getId() %>">Cancel</a></td>
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