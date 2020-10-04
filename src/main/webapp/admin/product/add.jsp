<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <main role="main" class="container">
    <%@include file="/includes/msg.jsp"%>
    <div class="card">
       	<c:set var="action" value="/admin/product/add"/>
       	<c:set var="title" value="Add Product"/>
		<c:if test="${command.productId > 0}">
		   <c:set var="action" value="/admin/product/update"/>
       		<c:set var="title" value="Update Product"/>
		</c:if>
        <div class="card-header text-white shadow bg-dark">
            <h2 class="float-left">${title}</h2>
        </div>
        <div class="card-body">
            <form:form action="${action}" enctype="multipart/form-data" method="post" >
            	<form:input type="hidden" path="productId"/>
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                
                <div class="form-group">
                	<div class="form-group row">
	                	<div class="col-sm-6">
	                		<a href="#" id="pop">
	                          <img src="https://placehold.it/200x200" width="200" height="200" id="preview" class="img-thumbnail">
	                        </a>
	                	</div>
	                	
	                	<div class="col-sm-6">
		                    <lable for="file" class="custom-control">Product Picture</label>
	                          <input type="file" name="imageFile" class="file" accept="image/*" />
	                          
	                          <div class="input-group">
	                            <input type="text" class="form-control" placeholder="Upload File" id="file" disabled>
	                            <div class="input-group-append">
	                              <button type="button" class="browse btn btn-primary disabled" id="capture">Browse</button>
	                            </div>
	                            <small class="form-text text-muted"><font color="red"><form:errors path="productName"></form:errors></font></small>
	                          </div>
		                    
							<%-- <form:input type="text" path="productImage" id="productImage" cssClass="form-control" required="required"/> --%>
		                	
	                	</div>
                	</div>
                </div>
                
                <div class="form-group">
                    <label for="productName" class="control-label">Product Name</label>
					<form:input type="text" path="productName" id="productName" cssClass="form-control" required="required"/>
                	<small class="form-text text-muted"><font color="red"><form:errors path="productName"></form:errors></font></small>
                </div>
                <div class="form-group">
                    <label for="productPrice" class="control-label">Product Price</label>
					<form:input type="text" path="productPrice" id="productPrice" cssClass="form-control" required="required"/>
                	<small class="form-text text-muted"><font color="red"><form:errors path="productPrice"></form:errors></font></small>
                </div>
                <div class="form-group">
                    <label for="productQty" class="control-label">Product Qty</label>
					<form:input type="text" path="productQty" id="productQty" cssClass="form-control" required="required"/>
                	<small class="form-text text-muted"><font color="red"><form:errors path="productQty"></form:errors></font></small>
                </div>
                <div class="form-group">
                    <label for="productCategory" class="control-label">Product Category</label>
					<form:select path="category.id" id="productCategory" cssClass="form-control">
						<c:forEach items="${pcList}" var="category">
							<form:option value="${category.id}">${category.name}</form:option>
						</c:forEach>
					</form:select>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success btn-lg btn-block" value="${title}">
                </div>
            </form:form>
        </div>
    </div>
    </main>
    
    <br><br><br>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>