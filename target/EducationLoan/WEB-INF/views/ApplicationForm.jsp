<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Education Loan Application</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; }
        .box { width: 380px; margin: 30px auto; background: #fff; border: 1px solid #ccc; }
        .box h2 { margin: 0; padding: 10px 12px; font-size: 16px; border-bottom: 1px solid #ccc; }
        form { padding: 12px; }
        label { display: block; font-size: 12px; font-weight: bold; margin-top: 10px; }
        input, select { width: 100%; padding: 6px; margin-top: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 8px; margin-top: 16px; background: #fff; border: 1px solid #333; font-weight: bold; cursor: pointer; }
        .error { background: #fdecea; color: #b71c1c; border: 1px solid #f5c6cb; padding: 8px 12px; margin: 12px; font-size: 13px; }
        .error ul { margin: 0; padding-left: 18px; }
    </style>
</head>
<body>
<div class="box">
    <h2>Application Form</h2>

    <%
        java.util.List<String> errors = (java.util.List<String>) request.getAttribute("errors");
        if (errors != null && !errors.isEmpty()) {
    %>
    <div class="error">
        <strong>Please correct the following:</strong>
        <ul>
            <% for (String e : errors) { %>
            <li><%= e %></li>
            <% } %>
        </ul>
    </div>
    <% } %>

    <form action="apply" method="post" novalidate>
        <label for="name">Applicant name</label>
        <input type="text" id="name" name="name" value="${name}">

        <label for="monthlyIncome">Monthly income (RM)</label>
        <input type="number" id="monthlyIncome" name="monthlyIncome" step="any" value="${monthlyIncome}">

        <label for="employmentStatus">Employment status</label>
        <select id="employmentStatus" name="employmentStatus">
            <option value="Full-time"     ${employmentStatus == 'Full-time' ? 'selected' : ''}>Full-time</option>
            <option value="Part-time"     ${employmentStatus == 'Part-time' ? 'selected' : ''}>Part-time</option>
            <option value="Self-employed" ${employmentStatus == 'Self-employed' ? 'selected' : ''}>Self-employed</option>
        </select>

        <label for="existingDebt">Existing monthly debt (RM)</label>
        <input type="number" id="existingDebt" name="existingDebt" step="any" value="${existingDebt}">

        <label for="requestedAmount">Requested loan amount (RM)</label>
        <input type="number" id="requestedAmount" name="requestedAmount" step="any" value="${requestedAmount}">

        <label for="durationYears">Loan duration (years)</label>
        <input type="number" id="durationYears" name="durationYears" step="any" value="${durationYears}">

        <button type="submit">Check Eligibility</button>
    </form>
</div>
</body>
</html>
