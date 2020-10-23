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
       	<c:set var="action" value="/admin/product_category/add"/>
       	<c:set var="title" value="Add Product Category"/>
		<c:if test="${command.id > 0}">
		   <c:set var="action" value="/admin/product_category/update"/>
       		<c:set var="title" value="Update Product Category"/>
		</c:if>
        <div class="card-header text-white shadow bg-dark">
            <h2 class="float-left">${title}</h2>
        </div>
        <div class="card-body">
            <form:form action="${action}" method="post">
            	<form:input type="hidden" path="id"/>
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group">
                    <label for="name" class="control-label">Category Name</label>
					<form:input type="text" path="name" id="name" cssClass="form-control" required="required"/>
                	<small class="form-text text-muted"><font color="red"><form:errors path="name"></form:errors></font></small>
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