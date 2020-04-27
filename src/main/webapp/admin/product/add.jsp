<%@page import="me.anant.PMS.model.ProductCategory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="me.anant.PMS.model.Product"%>
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
            <h2 class="float-left"><%
            if(request.getAttribute("product") == null) {
            	out.write("Add Product");
            } else {
            	out.write("Update Product");
            }
            %></h2>
        </div>
        <div class="card-body">
            <form action="<%
            if(request.getAttribute("product") == null) {
            	out.write("/admin/product/add");
            } else {
            	out.write("/admin/product/update?id="+request.getParameter("id"));
            }
            %>" method="post">
            	<% if(request.getAttribute("product") != null) {
            		out.write("<input type=\"hidden\" value=\""+request.getParameter("id")+"\" name=\"productId\">");
	            } %>
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group">
                    <label for="productName" class="control-label">Product Name</label> <input type="text" name="productName" id="productName" class="form-control"
                        required <% if(request.getAttribute("product") != null) {
                        	Product p = (Product) request.getAttribute("product");
		            		out.write("value=\""+p.getProductName()+"\"");
			            } %>>
                </div>
                <div class="form-group">
                    <label for="productPrice" class="control-label">Product Price</label> <input type="text" name="productPrice" id="productPrice" class="form-control"
                        required <% if(request.getAttribute("product") != null) {
                        	Product p = (Product) request.getAttribute("product");
		            		out.write("value=\""+p.getProductPrice()+"\"");
			            } %>>
                </div>
                <div class="form-group">
                    <label for="productQty" class="control-label">Product Qty</label> <input type="text" name="productQty" id="productQty" class="form-control"
                        required <% if(request.getAttribute("product") != null) {
                        	Product p = (Product) request.getAttribute("product");
		            		out.write("value=\""+p.getProductQty()+"\"");
			            } %>>
                </div>
                <div class="form-group">
                    <label for="productCategory" class="control-label">Product Category</label>
					<select name="productCategory" id="productCategory" class="form-control">
					<%
					List<ProductCategory> pcList = (List<ProductCategory>) request.getAttribute("pcList");
                    for(ProductCategory pc: pcList) {
                    	out.write("<option value=\""+pc.getId()+"\" ");
                    	if(request.getAttribute("product") != null) {
                    		Product p = (Product) request.getAttribute("product");
                    		if(p.getCategory().getId() == pc.getId()) {
                    			out.write("selected");
                    		}
                    	}
                    	out.write(">"+pc.getName()+"</option>");
                    }
					%>
					</select>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success btn-lg btn-block" value="<%
		            if(request.getAttribute("product") == null) {
		            	out.write("Add Product");
		            } else {
		            	out.write("Update Product");
		            }
		            %>">
                </div>
            </form>
        </div>
    </div>
    </main>
    
    <br><br><br>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>