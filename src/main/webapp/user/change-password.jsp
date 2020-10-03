<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
 import="java.util.*,me.anant.PMS.model.*"%>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Change Password</title>
</head>
<body>
<body style="background-color: #f8f9fa !important;">
    <%@include file="/includes/header.jsp"%>
    <main role="main" class="container">
    <%@include file="/includes/msg.jsp"%>
    <div class="card">
       	<c:set var="action" value="/user/change-password"/>
       	<c:set var="title" value="Change Password"/>
    	 <div class="card-body">
            <form:form action="${action}" method="post">
                <div class="form-group">
                    <label for="oldPassword" class="control-label">Old Password</label>
					<form:input type="password" path="oldPassword" id="oldPassword" cssClass="form-control" required="required"/>
                	<small class="form-text text-muted"><font color="red"><form:errors path="oldPassword"></form:errors></font></small>
                </div>
                <div class="form-group">
                    <label for="newPassword" class="control-label">New Password</label>
                    <small class="form-text">Password should contain minimum 8 characters, an  upper and lower case letter </small>
					<form:input type="password" path="newPassword" id="newPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" cssClass="form-control" required="required"/>
                	<small class="form-text text-muted"><font color="red"><form:errors path="newPassword"></form:errors></font></small>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="control-label">Confirm Password</label>
					<form:input type="password" path="confirmPassword" id="confirmPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" cssClass="form-control" required="required"/>
                	<small class="form-text text-muted"><font color="red"><form:errors path="confirmPassword"></form:errors></font></small>
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
<script type='text/javascript'>
	var newPAssword = document.getElementById("newPassword"), confirmPassword = document.getElementById("confirmPassword");

	function validatePassword() {
		if(newPassword.value != confirmPassword.value) {
			confirmPassword.setCustomValidity("Passwords Don't Match");
		} 
		else {
			confirmPassword.setCustomValidity('');
		}
}

newPassword.onchange = validatePassword;
confirmPassword.onkeyup = validatePassword;        	    
</script>
</html>