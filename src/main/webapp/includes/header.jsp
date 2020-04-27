<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fa fa-arrow-up"></i></button>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top shadow p-3 mb-5">
    <div class="container">
    	<sec:authorize access="!isAuthenticated()">
        	<a class="navbar-brand" href="/">PMS</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
        	<a class="navbar-brand" href="/admin/product/list">PMS</a>
        </sec:authorize>
        <sec:authorize access="hasRole('CUSTOMER')">
        	<a class="navbar-brand" href="/customer/order_place">PMS</a>
        </sec:authorize>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        	<ul class="navbar-nav mr-auto">
	            <sec:authorize access="hasRole('ADMIN')">
                	<li class="nav-item"><a class="nav-link" href="/admin/product/list">Product</a></li>
                	<li class="nav-item"><a class="nav-link" href="/admin/order/list">Order</a></li>
                	<li class="nav-item"><a class="nav-link" href="/admin/product/report">Product Report</a></li>
                	<li class="nav-item"><a class="nav-link" href="/admin/category/list">Category Report</a></li>
                </sec:authorize>
	            <sec:authorize access="hasRole('CUSTOMER')">
                	<li class="nav-item"><a class="nav-link" href="/customer">Place Order</a></li>
                	<li class="nav-item"><a class="nav-link" href="/customer/order/list">My Orders</a></li>
                </sec:authorize>
            </ul>
            
            <ul class="navbar-nav my-2 my-lg-0">
            	<sec:authorize access="!isAuthenticated()">
            		<li class="nav-item"><a class="nav-link" href="/login">Log In</a></li>
           		</sec:authorize>
           		<sec:authorize access="isAuthenticated()">
	                <li class="nav-item dropdown">
	                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
	                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <sec:authentication property="name"/> </a>
	                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	                        <a class="dropdown-item" href="/logout">Logout</a>
	                    </div>
	                </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
