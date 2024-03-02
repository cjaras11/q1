package com.cimb.assignment.q1;

public class Q1Application {

	public static void main(String[] args) {

		int[] prices = { 7, 1, 5, 3, 6, 4 };

		new Q1Application().process(prices);

	}

	private void process(int[] prices) {
		
		int maxProfit = 0;
		
		int profit = 0;
		
		// First loop for get buying value.
		for (int i = 0; i < prices.length - 1; i++) {

			// Second loop for get selling value to compare with buying value.
			for (int j = i + 1; j < prices.length; j++) {
				
				// Calculate Profit by compare between buying and selling value.
				profit = calculateProfit(prices[i], prices[j]);
				
				// Set Maximum Profit.
				if (profit > maxProfit)
					maxProfit = profit;

			}
		}
		System.out.println("Maximum Profit : " + maxProfit);
	}
	
	/**
	 * Calculate Profit
	 * 
	 * @param price1
	 * @param price2
	 * @return 0 if Profit <= 0, otherwise Profit
	 */
	private int calculateProfit(int price1, int price2) {
		int profit = price2 - price1;
		if (profit <= 0)
			return 0;
		return profit;
	}

}
