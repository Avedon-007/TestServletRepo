
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag" %>
<html>
<head>
    <title>Java Event Calendar</title>
</head>
<body>
    <div style="text-align: center;"/>
        <h1>Java Events Management</h1>
        <h2>
            <a href="/new">Add New Event</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Events</a>
        </h2>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Events</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Date of Event</th>
                <th>Actions</th>
            </tr>
            <myTag:forEach var="javaEvent" items="${javaEventList}">
                <tr>
                    <td><myTag:out value="${javaEvent.id}"/></td>
                    <td><myTag:out value="${javaEvent.title}"/></td>
                    <td><myTag:out value="${javaEvent.description}"/></td>
                    <td><myTag:out value="${javaEvent.dateOfEvent}"/></td>
                    <td>
                        <a href="/edit?id=<myTag:out value='${javaEvent.id}'/> ">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<myTag:out value='${javaEvent.id}'/> ">Delete</a>
                    </td>
                </tr>
            </myTag:forEach>
        </table>
    </div>




</body>
</html>
