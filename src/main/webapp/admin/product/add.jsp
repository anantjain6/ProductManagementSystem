<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
            <h2 class="float-left">Add Product</h2>
        </div>
        <div class="card-body">
            <form action="/admin/product/add" method="post">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group">
                    <label for="productName" class="control-label">Product Name</label> <input type="text" name="productName" id="productName" class="form-control"
                        required>
                </div>
                <div class="form-group">
                    <label for="productPrice" class="control-label">Product Price</label> <input type="text" name="productPrice" id="productPrice" class="form-control"
                        required>
                </div>
                <div class="form-group">
                    <label for="productQty" class="control-label">Product Qty</label> <input type="text" name="productQty" id="productQty" class="form-control"
                        required>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success btn-lg btn-block" value="Add Product">
                </div>
            </form>
        </div>
    </div>
    </main>
    
    <br><br><br>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>