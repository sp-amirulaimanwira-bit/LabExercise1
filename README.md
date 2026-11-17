# Education Loan Application (Java Web MVC)

A simple Java web application built using **JSP, Servlets, and Java POJOs** following the **Model-View-Controller (MVC)** architectural pattern. This application simulates an education loan eligibility and instalment calculator for working adults pursuing further studies.

## Features

* **Loan Application Form:** Collects applicant name, monthly income (RM), employment status (Full-time, Part-time, Self-employed), existing monthly debt payments (RM), requested loan amount (RM), and loan duration (1–10 years).
* **Input Validation:** Validates form inputs server-side to prevent invalid, negative, or out-of-range values.
* **Eligibility & Calculation Engine:** Computes monthly loan instalments and evaluates loan approval status based on applicant financial criteria.
* **Itemised Results View:** Displays a clear, itemised breakdown of the loan application outcome using JSP.

## Project Structure (MVC Architecture)

* **Model (`src/main/java/.../model/`):** Java POJO class (e.g., `LoanApplication.java`) storing applicant details, calculated instalments, and eligibility status.
* **Controller (`src/main/java/.../controller/`):** Java Servlet (e.g., `LoanServlet.java`) handling HTTP POST requests, input validation, business logic calculations, and request forwarding.
* **View (`src/main/webapp/`):** JSP pages (`index.jsp` for the input form and `result.jsp` for the itemised output).

## How to Run Locally

1. **Prerequisites:**
   * Java Development Kit (JDK 8 or higher)
   * Apache Tomcat (version 9 or 10)
   * An IDE such as Eclipse, NetBeans, or IntelliJ IDEA

2. **Setup & Deployment:**
   * Open/import the project into your IDE as a Dynamic Web Project / Maven Web Application.
   * Configure Apache Tomcat as the target server.
   * Build and run the project on the server.

3. **Access the Application:**
   * Open a web browser and navigate to:
     `http://localhost:8080/EducationLoanApp/` *(Update this URL to match your exact project context path)*

## Test Cases Included (Screenshots)

The submission ZIP includes screenshots demonstrating the following three test scenarios:
1. **Approved Case:** Valid applicant details meeting all eligibility criteria.
2. **Denied Case:** Valid applicant details failing the debt-to-income or eligibility thresholds.
3. **Invalid-Input Case:** Form submission with invalid inputs (e.g., negative income, loan duration outside 1–10 years) triggering validation error messages.
