<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if(session.getAttribute("msg") != null) {
    out.write("<div class='alert "+session.getAttribute("class")+"'>"+session.getAttribute("msg")+"</div>");
    session.removeAttribute("msg");
    session.removeAttribute("class");
}
%>