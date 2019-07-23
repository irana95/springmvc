<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="theme/style.css">

</head>
<body>

<div >
    <%--@elvariable id="user" type="com.spring.Model.UserEntity"--%>
    <form:form method="post" modelAttribute="user" action="addUser">
        <label for="name">Name:</label>
        <input  type="text" path="name" id="name" name="name"/>
        <form:errors path="name" cssClass="error"></form:errors>

        <label for="surname"> Surname: </label>
        <input type="text" path="surname" id="surname" name="surname"/>
        <form:errors path="surname" cssClass="error"></form:errors>

        <label for="mail"> Mail: </label>
        <input type="text" path="mail" id="mail" name="mail"/>
        <form:errors path="mail" cssClass="error"></form:errors>

        <label for="phone"> Phone: </label>
        <input type="text" path="phone" id="phone" name="phone" placeholder="(XXX) XXX-XX-XX" pattern="\(\d{3}\) \d{3}\-\d{2}-\d{2}" class="masked" title="10-digit number"/>
        <span class="text-muted"> example. (890) 890-89-98 </span>
        <form:errors path="phone" cssClass="error"></form:errors>

        <input type="submit" value="Send">
    </form:form>
</div>
<script src="theme/js/main.js"></script>
</body>
</html>
