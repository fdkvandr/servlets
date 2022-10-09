<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="header.jsp" %>
    <div>
        <span>Content. Русский</span>
        <p>Size: ${requestScope.flights.size()}</p>
        <%--dont null safe--%>
        <p>Id: ${requestScope.flights.get(0).id}</p>
        <%--null safe--%>
        <p>Id: ${requestScope.flights[1].id}</p>
        <p>Map Id: ${sessionScope.flightsMap[1].id}</p>
        <p>JSSESSIONID: ${cookie["JSESSIONID"].value}, unique identifier</p>
        <p>Header: ${header["Cookie"]}</p>
        <p>Parameter id: ${param.id}</p>
        <p>Parameter test: ${param.test}</p>
        <p>Empty list: ${not empty requestScope.flights}</p>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
