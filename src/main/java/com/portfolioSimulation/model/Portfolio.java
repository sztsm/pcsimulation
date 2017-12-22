package com.portfolioSimulation.model;

import java.math.BigDecimal;

public class Portfolio {
	
	private String name;
	
	private double returnValue;
	
	private double standardDeviation;
	
	private BigDecimal initialInvestment;
	
	private int investmentTimeframe;
	
	private BigDecimal median;
	
	private BigDecimal percentile90;
	
	private BigDecimal percentile10;
	
	/**
	 * 
	 * @param name - test case name
	 * @param initialInvestment - initial investment amount
	 * @param mean - 
	 * @param standardDeviation
	 * @param investmentTime
	 */
	public Portfolio(String name, BigDecimal initialInvestment, double returnValue, double standardDeviation, int investmentTime) {
		this.name=name;
		this.initialInvestment=initialInvestment;
		this.returnValue = returnValue;
		this.standardDeviation=standardDeviation;
		this.investmentTimeframe = investmentTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(double returnValue) {
		this.returnValue = returnValue;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public BigDecimal getInitialInvestment() {
		return initialInvestment;
	}

	public void setInitialInvestment(BigDecimal initialInvestment) {
		this.initialInvestment = initialInvestment;
	}

	public int getInvestmentTimeframe() {
		return investmentTimeframe;
	}

	public void setInvestmentTimeframe(int investmentTimeframe) {
		this.investmentTimeframe = investmentTimeframe;
	}

	public BigDecimal getMedian() {
		return median;
	}

	public void setMedian(BigDecimal median) {
		this.median = median;
	}

	public BigDecimal getPercentile90() {
		return percentile90;
	}

	public void setPercentile90(BigDecimal percentile90) {
		this.percentile90 = percentile90;
	}

	public BigDecimal getPercentile10() {
		return percentile10;
	}

	public void setPercentile10(BigDecimal percentile10) {
		this.percentile10 = percentile10;
	}
	
	@Override
	public String toString() {
		return "Portfolio [name=" + name + ", InitialInvestment = "
				+ initialInvestment + ", Return = " + returnValue + ", Risk = "
				+ standardDeviation + ", Median = " + median
				+ ", 10 % Best Case = " + percentile90
				+ ", 10 % Worst Case = " + percentile10 + "]";
	}
}