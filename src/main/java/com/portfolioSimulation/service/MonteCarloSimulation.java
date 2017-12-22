package com.portfolioSimulation.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

import com.portfolioSimulation.model.Portfolio;
import com.portfolioSimulation.utility.RandomGaussianGenerator;


public class MonteCarloSimulation implements Callable<BigDecimal> {
	
	private static final BigDecimal inflation = new BigDecimal("0.035");
	private Portfolio portfolio;
	
	public MonteCarloSimulation(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	/**
	 * This method calculates the projected portfolio future value 
	 * @param portfolio
	 * @return estimated in future investment
	 */
	
	public BigDecimal call() {

		BigDecimal startAmount = portfolio.getInitialInvestment();
		BigDecimal annualInterest;
		BigDecimal annualReturn;
		BigDecimal inflationAdjAmt;
		
		for (int year = 0 ; year < portfolio.getInvestmentTimeframe() ; year ++) {
			
			annualInterest = RandomGaussianGenerator.getGaussianValue(portfolio.getReturnValue(), portfolio.getStandardDeviation());

			//calculate annual return amount, Annual Return = year begin Investment amount * guassian interest
			annualReturn = startAmount.multiply(annualInterest).setScale(2, RoundingMode.HALF_UP);
			
			// end of the year total amount
			startAmount = startAmount.add(annualReturn).setScale(2, RoundingMode.HALF_UP);
			
			//calculate yearly inflation of 3.5 % on the cummulative amount
			inflationAdjAmt = startAmount.multiply(inflation).setScale(2, RoundingMode.HALF_UP);
			
			// inflation adjusted amount for the investment year
			startAmount = startAmount.subtract(inflationAdjAmt).setScale(2, RoundingMode.HALF_UP);
			
//			System.out.println("year :" + year + " return%: " + annualInterest + "Annual Return" + annualReturn +
//					 "inflationAdjAmt :" + inflationAdjAmt +  "adjusted year end amount" + startAmount);
		}
		
		return startAmount;
	
	}
	
}
