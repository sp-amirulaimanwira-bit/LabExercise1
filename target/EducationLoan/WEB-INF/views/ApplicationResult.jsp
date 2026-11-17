<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.loan.model.LoanApplication" %>
<%
    LoanApplication app = (LoanApplication) request.getAttribute("application");
    if (app == null) {
        response.sendRedirect("apply");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Application Result</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; }
        .box { width: 420px; margin: 30px auto; background: #fff; border: 1px solid #ccc; }
        .box h2 { margin: 0; padding: 10px 12px; font-size: 16px; border-bottom: 1px solid #ccc; }
        .content { padding: 12px; }
        .status { font-size: 18px; font-weight: bold; margin: 8px 0 14px; }
        .approved { color: #1b5e20; }
        .denied { color: #b71c1c; }
        table { width: 100%; border-collapse: collapse; font-size: 13px; }
        td { border: 1px solid #ccc; padding: 6px 8px; }
        td.label { font-weight: bold; width: 50%; }
        .reason { font-weight: bold; font-size: 13px; margin: 14px 0; }
        a.btn { display: block; text-align: center; padding: 8px; border: 1px solid #333; color: #000; text-decoration: none; font-weight: bold; }
    </style>
</head>
<body>
<div class="box">
    <h2>Application Result</h2>
    <div class="content">
        <div class="status <%= app.isApproved() ? "approved" : "denied" %>"><%= app.getStatusText() %></div>

        <table>
            <tr><td class="label">Applicant:</td><td><%= app.getName().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") %></td></tr>
            <tr><td class="label">Requested amount:</td><td><%= app.getRequestedAmountText() %></td></tr>
            <tr><td class="label">Approved amount:</td><td><%= app.getApprovedAmountText() %></td></tr>
            <tr><td class="label">Annual interest rate:</td><td><%= app.getInterestRateText() %></td></tr>
            <tr><td class="label">Loan duration:</td><td><%= app.getDurationText() %></td></tr>
            <tr><td class="label">Estimated monthly instalment:</td><td><%= app.getInstalmentText() %></td></tr>
            <tr><td class="label">Debt service ratio:</td><td><%= app.getDsrText() %></td></tr>
        </table>

        <p class="reason"><%= app.getReason() %></p>

        <a class="btn" href="apply">New Application</a>
    </div>
</div>
</body>
</html>
