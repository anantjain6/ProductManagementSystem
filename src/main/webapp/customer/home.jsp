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
		<div class="row">
			<div class="col-8">
				<div class="card">
					<div class="card-body">
						<form action="/customer/order_place" method="post">
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col">Name</th>
										<th scope="col">Price</th>
										<th scope="col">Qty</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<Product> pl = (List<Product>) request.getAttribute("pList");
										for (Product p : pl)
										{
										%>
										<tr>
											<td><input type="checkbox" name="productId" value="<%=p.getProductId()%>"></td>
											<td><%=p.getProductName()%></td>
											<td>Rs. <%= p.getProductPrice() %></td>
											<td>
												<select class="form-control" name="<%=p.getProductId()%>">
													<%
													for(int i=1; i<=p.getProductQty(); i++) {
														out.write("<option value=\""+i+"\">"+i+"</option>");
													}
													%>
												</select>
											</td>
										</tr>
										<%   
										}
									%>
								</tbody>
							</table>
							<br>
							<input type="submit" class="btn btn-success btn-lg btn-block" value="Place Order">
						</form> 
					</div>
				</div>
			</div>
			<div class="col-4">
				<div class="card">
					<div class="card-header">
						<h3>Categories</h3>
					</div>
					<div class="card-body">
						<div class="list-group">
							<a href="/customer" class="list-group-item list-group-item-action">All ( <%= pl.size() %> )</a>
							<%
							List<ProductCategory> pcList = (List<ProductCategory>) request.getAttribute("pcList");
							for (ProductCategory pc : pcList) {
							%>
							<a href="?categoryId=<%= pc.getId() %>" class="list-group-item list-group-item-action">
								<%=pc.getName()%> ( <%=pc.getProducts().size()%> )
							</a>
							<%   
							}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>

    <br><br><br>
    </main>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>