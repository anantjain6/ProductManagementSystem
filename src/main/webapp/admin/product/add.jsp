<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="card-header text-white shadow bg-dark">
            <h2 id="productFormTitle" class="float-left">Add product</h2>
        </div>
        <div id="productFormHolder" class="card-body">
            <form id="productForm" action="#">
                <div class="form-group">
                    <label for="productName" class="control-label">Product Name</label>
                    <input type="text" id="productName" class="form-control" required minlength="3"/>
                    <span id="productNameErrorMessage" class="form-text text-muted"></span>
                </div>
                <div class="form-group">
                    <label for="productPrice" class="control-label">Product Price</label>
                    <input type="text" id="productPrice" class="form-control" required min="0.1" pattern="[0-9]*\.?[0-9]+"/>
                    <span id="productPriceErrorMessage" class="form-text text-muted"></span>
                </div>
                <div class="form-group">
                    <label for="productQuantity" class="control-label">Product Qty</label>
                    <input type="text" id="productQuantity" class="form-control" required/>
                    <span id="productQtyErrorMessage" class="form-text text-muted"></span>
                </div>
                <div class="form-group">
                    <label for="productCategory" class="control-label">Product Category</label>
                    <select id="productCategory" class="form-control"></select>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success btn-lg btn-block" value="Save">
                </div>
            </form>
        </div>
    </div>
    </main>
    
    <br><br><br>

    <%@include file="/includes/footer.jsp"%>
    <script type="application/javascript">
        const title = document.getElementById("productFormTitle");
        title.innerText = "Add product";
        // window location parse /admin/update vs. /admin/update/1
        const arr = window.location.pathname.split("/");
        if (arr.length >= 4 && Number.isInteger(arr[4])) {
            fetchProduct(arr[4]);
        }

        const submit = (e) => {
            return (e) => {
                e.preventDefault();
                const product = {
                    "name": document.getElementById("productName").value,
                    "price": document.getElementById("productPrice").value,
                    "quantity": document.getElementById("productQuantity").value,
                    "category": document.getElementById("productCategory").value,
                }
                const request = new XMLHttpRequest();
                const url = "/admin/product/add";
                request.open('POST', url, true);
                request.setRequestHeader("Content-Type", "application/json");
                request.onreadystatechange = () => {
                    if (request.readyState === 4) {
                        if (request.status === 201) {
                            displaySuccess("Product created successfully.");
                            return;
                        }
                    }
                }
                request.send(JSON.stringify(product));
            }
        }

        const form = document.getElementById("productForm");
        if (form) {
            form.addEventListener("submit", submit(event));
        }
        renderCategories(fetchCategories());
    </script>
</body>
</html>
