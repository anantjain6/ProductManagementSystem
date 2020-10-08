<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Show Error Page</title>
</head>

<body>
<h1>Opps...</h1>
<p>Sorry, an error occurred..</p>
<pre><%=exception.getMessage() %></pre>
<p>Please Contact support..</p>
</body>
</html>