<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
if(request.getAttribute("msg") != null) {
    out.write("<div class='alert "+request.getAttribute("class")+"'>"+request.getAttribute("msg")+"</div>");
}
%>
<div id="successMsg" class="alert alert-success" style="display: none;"></div>
<div id="errorMsg" class="alert alert-danger" style="display: none;"></div>
