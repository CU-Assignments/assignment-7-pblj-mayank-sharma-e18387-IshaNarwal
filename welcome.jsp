<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Welcome</title></head>
<body>
    <h1>Welcome, <%= request.getAttribute("user") %>!</h1>
</body>
</html>
