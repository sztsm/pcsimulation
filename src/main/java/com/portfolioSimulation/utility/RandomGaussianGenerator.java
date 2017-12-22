package com.portfolioSimulation.utility;

import java.math.BigDecimal;
import java.util.Random;

public class RandomGaussianGenerator {
	  private static Random random = new Random();

	  /**
	   * generates a  Gaussian random value with the given input mean and standard deviation
	   * @author muser
	   *
	   */
	  public static BigDecimal getGaussianValue(double mean, double variance){
		 double guassionRandom = (random.nextGaussian() * variance ) + mean;
	    return new BigDecimal(String.valueOf(guassionRandom));
	  }
}
