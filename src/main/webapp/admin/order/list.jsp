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
            <h2 class="float-left">Orders</h2>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Date</th>
                        <th scope="col">User</th>
                        <th scope="col">Item</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    	List<Order> oList = (List<Order>) request.getAttribute("oList");
                    	for (Order o : oList)
                        {
                        %>
                        <tr>
                            <th scope="row"><a href="detail?id=<%=o.getId()%>"><%=o.getId()%></a></th>
                            <td><%=o.getCreateDateTime()%></td>
                            <td><%=o.getUser().getEmail()%></td>
                            <%
                            float sum = 0;
                            int count = 0;
                            Set<OrderProduct> opList = o.getOrderProduct();
                            for(OrderProduct op: opList) {
                            	sum = sum + op.getProduct().getProductPrice() * op.getBuyqty();
                            	count++;
                            }
                            %>
                            <td><%= count %></td>
                            <td>Rs. <%= sum %></td>
                            <td><%= o.getStatus() %></td>
                            <td>
                            	<form action="status" method="POST">
                            		<input type="hidden" name="id" value="<%=o.getId()%>">
                            		<select name="status"  class="control-label">
                            			<option value="CONFIRM">CONFIRM</option>
                            			<option value="REJECT">REJECT</option>
                            		</select>
                            		<input type="submit" class="btn btn-success" value="Update Status">
                            	</form>
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