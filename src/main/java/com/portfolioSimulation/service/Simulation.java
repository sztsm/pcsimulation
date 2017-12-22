package com.portfolioSimulation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.portfolioSimulation.model.Portfolio;

public class Simulation {

	
	List<Portfolio> portfolioList;
	
	private static final int SIMULATIONRUNS = 10000;
	
	private static final int INVESTMENT_YEAR = 20;
	
	private static final int PERCENTILE_10_INDEX = (int) Math.abs((SIMULATIONRUNS * 0.1) -1);
	
	private static final int PERCENTILE_90_INDEX = (int) Math.abs((SIMULATIONRUNS * 0.9) -1);
	
	private static final int PERCENTILE_50_INDEX = (int) Math.abs((SIMULATIONRUNS * 0.5) -1);
	
	public Simulation (List<Portfolio> portfolioList) {
		
		this.portfolioList = portfolioList;
	}
	
	
	public static void main(String[] args) {
		Portfolio aggrPortfolio = new Portfolio("Aggressive",  
				new BigDecimal("100000.00"), 0.094324, 0.15675, INVESTMENT_YEAR);
		
		Portfolio conservPortfolio= new Portfolio("Conservative",  
				new BigDecimal("100000.00"), 0.06189, 0.063438, INVESTMENT_YEAR);
		
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList.add(aggrPortfolio);
		portfolioList.add(conservPortfolio);
		
		Simulation simulation = new Simulation(portfolioList);
	    List<Portfolio> list = simulation.simulate(portfolioList);
	    
	    for (Portfolio p : list) {
	    		System.out.println(p);
	    		
	    }
	}
	
	/**
	 * Accepts a list of portfolio, Runs Monte Carlo simulation to determine the returns at the given return 
	 * and standard deviation, for a period of 20 year.
	 * The Investment return is adjusted for a 3.5% inflation rate.
	 * @param portfolioList
	 * @return returns the median, 10% percentile and 90 % percentile value
	 */
	private List<Portfolio> simulate(List<Portfolio> portfolioList) {
		
		for (Portfolio portfolio : portfolioList) {
			
			if (!portfolio.getInitialInvestment().equals(BigDecimal.ZERO )) {
				
				BigDecimal[] investmentReturns = new BigDecimal[SIMULATIONRUNS];
				
				ExecutorService exec = Executors.newCachedThreadPool();
				ArrayList<Future<BigDecimal>> simulationResult =       new ArrayList<Future<BigDecimal>>();
				
				// get the investment amount for the given timeframe using monte carlo simulation
				for (int i = 0; i < SIMULATIONRUNS; i++) {
					simulationResult.add(exec.submit(new MonteCarloSimulation(portfolio))); 
				}
				
				int runCount = 0;
				try {
					for(Future<BigDecimal> fs : simulationResult) {
						investmentReturns[runCount] = fs.get();
						runCount++;
					}
					
					// sort the forcasted investment returns from each run in asc order
					Arrays.sort(investmentReturns);

					// set the median, and percentile
					portfolio.setPercentile10(investmentReturns[PERCENTILE_10_INDEX]);
					portfolio.setPercentile90(investmentReturns[PERCENTILE_90_INDEX]);
					portfolio.setMedian(investmentReturns[PERCENTILE_50_INDEX]);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} finally {
					exec.shutdown();
				}
			}
		}
		return this.portfolioList;
	}	

}

