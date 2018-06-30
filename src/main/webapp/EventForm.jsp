
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag" %>
<html>
<head>
    <title>Java Event Application</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>Event Management</h1>
        <h2>
            <a href="/new">Add New Event</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Events</a>
        </h2>
    </div>
    <div align="center">

        <%--Decide wich form will be use--%>
        <myTag:if test="${javaEvent != null}">
            <form action="update" method="post" onsubmit="return validateOnUpdate()" name="update_form">
        </myTag:if>
        <myTag:if test="${javaEvent == null}">
            <form action="insert" method="post" onsubmit="return validateOnInsert()" name="insert_form">
        </myTag:if>

        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <myTag:if test="${javaEvent != null}">
                        Edit Event
                    </myTag:if>
                    <myTag:if test="${javaEvent == null}">
                        Add New Event
                    </myTag:if>
                </h2>
            </caption>
                <myTag:if test="${javaEvent != null}">
                    <input type="hidden" name="id" value="<myTag:out value='${javaEvent.id}'/>"/>
                </myTag:if>
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="title" size="45" value="<myTag:out value='${javaEvent.title}'/>"/>
                    <div name="title_error" class="val_error"></div>
                </td>
            </tr>
            <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="description" size="45" value="<myTag:out value='${javaEvent.description}'/>"/>
                </td>
            </tr>
            <tr>
                <th>Date of Event:</th>
                <td>
                    <input type="text" name="dateOfEvent" placeholder="2000-12-31" size="45" value="<myTag:out value='${javaEvent.dateOfEvent}'/>"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
        </form>
    </div>

    <script>
        function validateOnUpdate() {
            var titleValidation = document.forms["update_form"]["title"];
            var descriptionValidation = document.forms["update_form"]["description"];
            var titleValidationOnUpdate = document.forms["update_form"]["title"];

            if(!titleValidation.value){
                titleValidation.style.border = "2px solid red";
                // title_errorValidation.textContent = "Please enter a title of event."
                titleValidation.focus();
                return false;
            }else if(!descriptionValidation.value){
                descriptionValidation.style.border = "2px solid red";
                // title_errorValidation.textContent = "Please enter a title of event."
                descriptionValidation.focus();
                return false;
            }
        }
    </script>
    <script>
        function validateOnInsert() {
            var titleValidation = document.forms["insert_form"]["title"];
            var descriptionValidation = document.forms["insert_form"]["description"];
            // var titleValidationOnUpdate = document.forms["insert_form"]["title"];

            if(!titleValidation.value){
                titleValidation.style.border = "2px solid red";
                // title_errorValidation.textContent = "Please enter a title of event."
                titleValidation.focus();
                return false;
            }else if(!descriptionValidation.value){
                descriptionValidation.style.border = "2px solid red";
                // title_errorValidation.textContent = "Please enter a title of event."
                descriptionValidation.focus();
                return false;
            }
        }

    </script>

</body>
</html>