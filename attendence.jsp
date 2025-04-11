<html>
<head><title>Attendance Form</title></head>
<body>
    <h2>Enter Attendance Details</h2>
    <form action="AttendanceServlet" method="post">
        Student Name: <input type="text" name="name"><br>
        Date: <input type="date" name="date"><br>
        Status: 
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
