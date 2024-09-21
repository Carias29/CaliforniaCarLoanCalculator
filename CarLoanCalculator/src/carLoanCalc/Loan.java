package carLoanCalc;

public class Loan {
	private double carPrice;
	private int loanTerm;
	private double annualInterest;
	private double downPayment;
	private double salesTax;
	private String frequency;
	private double tradeInValue;
	private double amountOwed;
	private double titleAndRegistration;
	private double otherFees;
	
	public Loan(String cPrice, String loanTerm, String annualInterest, String downPayment, String salesTax, String frequency, String tradeInValue, String amountOwed, String titleAndRegistration, String otherFees) {
		try {
		    carPrice = Double.parseDouble(cPrice);
		} catch (NumberFormatException e) {
		    carPrice = 0;
		}

		try {
		    this.loanTerm = Integer.parseInt(loanTerm);
		} catch (NumberFormatException e) {
		    this.loanTerm = 0;
		}

		try {
		    this.annualInterest = Double.parseDouble(annualInterest);
		} catch (NumberFormatException e) {
		    this.annualInterest = 0;
		}

		try {
		    this.downPayment = Double.parseDouble(downPayment);
		} catch (NumberFormatException e) {
		    this.downPayment = 0;
		}

		try {
		    this.salesTax = Double.parseDouble(salesTax);
		} catch (NumberFormatException e) {
		    this.salesTax = 0;
		}

		try {
		    this.tradeInValue = Double.parseDouble(tradeInValue);
		} catch (NumberFormatException e) {
		    this.tradeInValue = 0;
		}

		try {
		    this.amountOwed = Double.parseDouble(amountOwed);
		} catch (NumberFormatException e) {
		    this.amountOwed = 0;
		}

		try {
		    this.titleAndRegistration = Double.parseDouble(titleAndRegistration);
		} catch (NumberFormatException e) {
		    this.titleAndRegistration = 0;
		}  
		
		try {
		    this.otherFees = Double.parseDouble(otherFees);
		} catch (NumberFormatException e) {
		    this.otherFees = 0;
		} 
		
		this.frequency = frequency;

	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public double totalTaxCost() {
		return carPrice * (salesTax/100.0);
	}
	
	public double getDownPayment() {
		return downPayment;
	}
	
	public double LoanAmount() {
		double amountLoaned = (carPrice + totalTaxCost() + titleAndRegistration + otherFees) - (downPayment + tradeInValue) + amountOwed;
		return amountLoaned;
	}
	
	public double calculateInterestCost() {	
		double interestCost = LoanAmount() * (annualInterest/100.0) * loanTerm;
		return interestCost;
	}
	
	public double getYearlyPayment() {
		double yearlyInterestRate = (annualInterest / 100.0) / 26.0;
		int allYearlyPayments = loanTerm * 26;
		double yearlyPayment = LoanAmount() * (yearlyInterestRate * Math.pow(1 + yearlyInterestRate, allYearlyPayments)) /
                (Math.pow(1 + yearlyInterestRate, allYearlyPayments) - 1);
		return yearlyPayment;
	}
	
	public double getQuarterlyPayment() {
		double quarterlyInterestRate = (annualInterest / 100.0) / 4.0;
		int allQuarterlyPayments = loanTerm * 4;
		double quarterlyPayment = LoanAmount() * (quarterlyInterestRate * Math.pow(1 + quarterlyInterestRate, allQuarterlyPayments)) /
                (Math.pow(1 + quarterlyInterestRate, allQuarterlyPayments) - 1);
		return quarterlyPayment;
	}
	
	public double getMonthlyPayment() {
		double monthlyInterestRate = (annualInterest/100.0) /12.0;
		int allMonthlyPayments = loanTerm * 12;
		double monthlyPayment = LoanAmount() * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, allMonthlyPayments)) /
                (Math.pow(1 + monthlyInterestRate, allMonthlyPayments) - 1);
		return monthlyPayment;
	}
	
	public double getBiWeeklyPayment() {
		double biWeeklyInterestRate = (annualInterest / 100.0) / 26.0;
		int allBiWeeklyPayments = loanTerm * 26;
		double biWeeklyPayment = LoanAmount() * (biWeeklyInterestRate * Math.pow(1 + biWeeklyInterestRate, allBiWeeklyPayments)) 
				/(Math.pow(1 + biWeeklyInterestRate, allBiWeeklyPayments) - 1);
		return biWeeklyPayment;
	}
	public double getWeeklyPayment() {
		double WeeklyInterestRate = (annualInterest / 100.0) / 52.0;
		int allWeeklyPayments = loanTerm * 52;
		double biWeeklyPayment = LoanAmount() * (WeeklyInterestRate * Math.pow(1 + WeeklyInterestRate, allWeeklyPayments)) /
                (Math.pow(1 + WeeklyInterestRate, allWeeklyPayments) - 1);
		return biWeeklyPayment;
	}
	
	
}
