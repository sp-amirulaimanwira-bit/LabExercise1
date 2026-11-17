package edu.utm.loan.controller;

import edu.utm.loan.model.LoanCalculator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/apply")
public class LoanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/ApplicationForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = trim(request.getParameter("name"));
        String incomeStr = trim(request.getParameter("monthlyIncome"));
        String status = trim(request.getParameter("employmentStatus"));
        String debtStr = trim(request.getParameter("existingDebt"));
        String amountStr = trim(request.getParameter("requestedAmount"));
        String durationStr = trim(request.getParameter("durationYears"));

        List<String> errors = new ArrayList<>();

        // ---------- Validasi ----------
        if (name.isEmpty()) {
            errors.add("Applicant name is required.");
        }

        double income = 0;
        try {
            income = parseNumber(incomeStr);
            if (income <= 0) {
                errors.add("Monthly income must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            errors.add("Monthly income must be a valid number.");
        }

        if (!status.equals("Full-time") && !status.equals("Part-time")
                && !status.equals("Self-employed")) {
            errors.add("Employment status must be Full-time, Part-time or Self-employed.");
        }

        double debt = 0;
        try {
            debt = parseNumber(debtStr);
            if (debt < 0) {
                errors.add("Existing debt cannot be negative.");
            }
        } catch (NumberFormatException e) {
            errors.add("Existing debt must be a valid number.");
        }

        double amount = 0;
        try {
            amount = parseNumber(amountStr);
            if (amount <= 0) {
                errors.add("Requested loan amount must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            errors.add("Requested loan amount must be a valid number.");
        }

        int years = 0;
        try {
            years = Integer.parseInt(durationStr);
            if (years <= 0) {
                errors.add("Loan duration must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            errors.add("Loan duration must be a valid integer.");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("name", escape(name));
            request.setAttribute("monthlyIncome", escape(incomeStr));
            request.setAttribute("employmentStatus", escape(status));
            request.setAttribute("existingDebt", escape(debtStr));
            request.setAttribute("requestedAmount", escape(amountStr));
            request.setAttribute("durationYears", escape(durationStr));
            request.getRequestDispatcher("WEB-INF/views/ApplicationForm.jsp").forward(request, response);
            return;
        }

        LoanCalculator calculator = new LoanCalculator();
        calculator.setName(name);
        calculator.setMonthlyIncome(income);
        calculator.setEmploymentStatus(status);
        calculator.setExistingDebt(debt);
        calculator.setRequestedAmount(amount);
        calculator.setDurationYears(years);

        request.setAttribute("calculator", calculator);
        request.getRequestDispatcher("WEB-INF/views/ApplicationResult.jsp").forward(request, response); 
    }

    private String escape(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }

    private String trim(String input) {
        return input == null ? "" : input.trim();
    }

    private double parseNumber(String input) throws NumberFormatException {
        if (input == null || input.trim().isEmpty()) {
            throw new NumberFormatException("Input is empty");
        }
        return Double.parseDouble(input.trim());
    }
}