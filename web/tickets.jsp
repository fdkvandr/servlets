<%@ page import="com.http.service.TicketService" %>
<%@ page import="com.http.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Купленные билеты:</h1>
<ul>
    <%
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        List<TicketDto> ticketDtos = TicketService.getINSTANCE().findAllByFlightId(flightId);
        for (TicketDto ticketDto : ticketDtos) {
            out.write(String.format("<li>%s</li>", ticketDto.getSeatNo()));
        }
    %>
</ul>
</body>
</html>

<%!
    public void jspInit() {
        System.out.println("Hello world");
    }
%>
