package estimatePi;

import java.util.ArrayList;
import java.util.Random;



public class PiEstimator {

	Random r = new Random();
	
	
	public PiEstimator(int runs, int numThrows) {
		
		ArrayList<Double> games = new ArrayList<Double>();
		
		double piSum = 0;
		for (int i = 0; i < runs; i++) {
			double result = simulateDartThrows(numThrows);
			piSum += result;
			games.add(result);
		}
		System.out.println("=================================================");		
		System.out.println("Runs: " + runs + " Throws: " + numThrows);
		System.out.println("Average of simulation: " + piSum/runs);
		System.out.println("Standard Deviation: " + standardDeviation(games));
		System.out.println("=================================================");
		
		
	}
	
	
	 public double standardDeviation(ArrayList<Double> list) {
		 
		 double sum = 0;
		 for (double num : list) {
			 sum += num;
		 }
		 
		 double average = sum / (double) list.size();
		 
		 double sumOfSquares = 0;
		 for (double num : list) {
			 sumOfSquares += (num - average) * (num - average);
		 }
		 
		 return Math.sqrt(sumOfSquares/ (double) list.size());
	 }
	
	
	public Tuple<Double, Double> throwDart() {
		return new Tuple<Double, Double>(this.r.nextDouble() - 0.5, this.r.nextDouble() - 0.5);
	}
	
	public boolean onTarget(Tuple<Double, Double> dartThrow) {
		return Math.sqrt(Math.pow(dartThrow.x, 2) + Math.pow(dartThrow.y, 2)) < 0.5;
	}

	public Double simulateDartThrows(int numThrows) {
		int throwsOnTarget = 0;
		for (int i = 0; i < numThrows; i++) {
			if (onTarget(this.throwDart())) {
				throwsOnTarget++;
			}
		}
		return ((double)throwsOnTarget / (double) numThrows) * 4.0;
	}
	
	public static void main(String[] args) {
		for (int runs = 10; runs < 250; runs += 10) {
			for (int numThrows = 2000; numThrows < 128000; numThrows *= 2) {
				new PiEstimator(runs, numThrows);
			}
		}
	}
	
	protected class Tuple<x, y> {
		public x x;
		public y y;
		public Tuple(x x, y y) {
			this.x = x;
			this.y = y;
		}
	}
	
}


