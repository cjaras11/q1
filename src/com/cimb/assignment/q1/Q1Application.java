package com.cimb.assignment.q1;

import java.util.ArrayList;
import java.util.List;

public class Q1Application {

	public static int maxProfit = 0;

	public static void main(String[] args) {

		int[] prices = {7,1,5,3,6,4};

		new Q1Application().process(prices);

	}

	private void process(int[] prices) {
		
		int midIndex = prices.length / 2;
		
		Thread thread1 = new Thread(new Q1ApplicationThread(prices, 0, midIndex));
		Thread thread2 = new Thread(new Q1ApplicationThread(prices, midIndex, prices.length));
		
		List<Thread> threadList = new ArrayList<Thread>();
		threadList.add(thread1);
		threadList.add(thread2);
		
		threadList.forEach(thread -> thread.start());
		threadList.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println("Maximum Profit is : " + Q1Application.maxProfit);
		
	}

	public static synchronized void setMaxProfit(int profit) {
		if (profit > maxProfit)
			maxProfit = profit;
	}

}

class Q1ApplicationThread implements Runnable {

	int[] prices;
	int startIndex;
	int lastIndex;

	public Q1ApplicationThread(int[] prices, int startIndex, int lastIndex) {
		super();
		this.prices = prices;
		this.startIndex = startIndex;
		this.lastIndex = lastIndex;
	}

	@Override
	public void run() {

		int profit = 0;
		
		// First loop for get buying value.
		for (int i = startIndex; i < lastIndex; i++) {
			
			// Second loop for get selling value to compare with buying value.
			for (int j = i + 1; j < prices.length; j++) {
				
				// Calculate Profit by compare between buying and selling value.
				profit = calculateProfit(prices[i], prices[j]);
				
				// Set Maximum Profit.
				Q1Application.setMaxProfit(profit);

			}
		}

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
