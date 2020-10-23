<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
if(request.getAttribute("msg") != null) {
    out.write("<div class='alert "+request.getAttribute("class")+"'>"+request.getAttribute("msg")+"</div>");
}

if(Arrays.stream(request.getCookies()).anyMatch(cookie -> "msg".equals(cookie.getName()))) {
    String message = Arrays.stream(request.getCookies())
            .filter(cookie -> "msg".equals(cookie.getName()))
            .collect(Collectors.toList())
            .get(0).toString();
    String messageClass = Arrays.stream(request.getCookies())
            .filter(cookie -> "msgClass".equals(cookie.getName()))
            .collect(Collectors.toList())
            .get(0).toString();

    out.write("<div class='alert "+messageClass+"'>"+message+"</div>");
}

%>
