
Exercise #1

Modern Portfolio Theory says that it is not enough to look at the expected risk and return of one particular stock. By investing in more than one stock, an investor can reap the benefits of diversification- chief among them, a reduction in the riskiness of the portfolio. 
A Real Time implementation of Modern Portfolio Theory is to build an optimal asset allocation of domestic stocks & bonds, international stock and bonds, alternatives and cash. Combining the risk & return of each asset class and correlations across them – we come up with the risk and return of the overall portfolio.  Two portfolios were created, one being extremely Conservative and one being Aggressive.

Portfolio Type : Aggressive
Return (Mean) : %9.4324
Risk ( Standard Deviation) : 15.675

Portfolio Type : Very Conservative
Return (Mean) : %6.189
Risk ( Standard Deviation) : 6.3438


We would now like to compare their performance against each other. We would like to know that if a user with $100,000 invested their money in either of them, how would the returns compare over the next 20 years. We would like to test their performance by using forward-looking Monte Carlo Simulations.

Monte Carlo Simulation:
This is a statistical technique that uses pseudo-random uniform variables for a given statistical distribution based on past risk (SD) and return (mean) to predict outcomes over future time periods. Based on iterative evaluation of each random future value, we project the portfolio future value over 20 years. We would like to run 10,000 simulations of projecting 20 year value and come up with the following:

Assumptions
1.     We would like to use a random number generator to ensure Gaussian distribution of random numbers that are generated.
2.     20th Year future value should be inflation adjusted at the rate of 3.5% each year. Ie. Year 1 value of 103.5 is equivalent to 100 at Year 0.

Solution Needed
1. Portfolio Inflation Adjusted Values from Simulation
2. Portfolio Type
3. Median 20th Year
4. 10 % Best Case
5. 10 % Worst Case

Description
10% Best Case               	:   90th Percentile value among the 10,000 simulations
10% Worst Case            	:  10th Percentile value among the 10,000 simulations.

Solution :
Use Simulation.java to run Run the program

The program takes the input of : Portfolio Type, Return (Mean), Risk ( Standard Deviation) and Investment period (years)

10,0000 runs of Monte Carlo simulation is run for each portfolio.
Each run calculates the forcasted amount adjusted for inflation at the end to the investment period.
Median and 90th percentile value and 10th percentile value is printed.



