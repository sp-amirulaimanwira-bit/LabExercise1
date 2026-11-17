package edu.utm.loan.model;

public class LoanCalculator {
    
    private String name;
    private double amount;
    private double monthlyIncome;
    private String employmentStatus;
    private double existingDebt;
    private double requestedAmount;
    private int durationYears;
    private double annualInterestRate;
    private int durationMonths;
    private double monthlyInstalment;
    private double debtServiceRatio;
    private boolean approved;
    private double approvedAmount;
    private String reason;

    public LoanCalculator(){
    }

    public void calculate() {
        // Annual interest rate berdasarkan employment status
        if ("Full-time".equals(employmentStatus)) {
            annualInterestRate = 4.0;
        } else if ("Part-time".equals(employmentStatus)) {
            annualInterestRate = 5.0;
        } else {
            annualInterestRate = 6.0;
        }

        double r = annualInterestRate / (12 * 100);
        durationMonths = durationYears * 12;
        monthlyInstalment = (requestedAmount * r) / (1 - Math.pow(1 + r, -durationMonths));
        debtServiceRatio = ((existingDebt + monthlyInstalment) / monthlyIncome) * 100;

        boolean approved;
        if (debtServiceRatio <= 40 && monthlyInstalment <= monthlyIncome * 0.30) {
            approved = true;
            approvedAmount = amount;
        } else {
            approved = false;
            approvedAmount = 0;
}

        if (approved) {
            reason = "Both eligibility conditions are satisfied.";
        } else if (debtServiceRatio > 40 && !((monthlyInstalment <= monthlyIncome * 0.30))) {
            reason = "Denied because DSR exceeds 40% and the monthly instalment exceeds 30% of monthly income.";
        } else if (!(debtServiceRatio <= 40)) {
            reason = "Denied because DSR exceeds 40%.";
        } else {
            reason = "Denied because the monthly instalment exceeds 30% of monthly income.";
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }
    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public double getExistingDebt() {
        return existingDebt;
    }
    public void setExistingDebt(double existingDebt) {
        this.existingDebt = existingDebt;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public int getDurationYears() {
        return durationYears;
    }
    public void setDurationYears(int durationYears) {
        this.durationYears = durationYears;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public double getMonthlyInstalment() {
        return monthlyInstalment;
    }

    public double getDebtServiceRatio() {
        return debtServiceRatio;
    }

    public boolean isApproved() {
        return approved;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public String getReason() {
        return reason;
    }

    public String getStatusText() {
        return approved ? "Approved" : "Denied";
    }

    public String getRequestedAmountText() {
        return String.format("RM%,.2f", requestedAmount);
    }

    public String getApprovedAmountText() {
        return String.format("RM%,.2f", approvedAmount);
    }

    public String getInterestRateText() {
        return String.format("%.2f%%", annualInterestRate);
    }

    public String getDurationText() {
        return durationYears + (durationYears == 1 ? " year (" : " years (")
                + durationMonths + " months)";
    }

    public String getInstalmentText() {
        return String.format("RM%,.2f", monthlyInstalment);
    }

    public String getDsrText() {
        return String.format("%.2f%%", debtServiceRatio);
    }


}
