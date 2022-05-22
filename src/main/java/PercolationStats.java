import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/******************************************************************************
 * Name: Kevin Wayne Login: wayne Precept: P01
 *
 * Partner Name: N/A Partner Login: N/A Partner Precept: N/A
 * 
 * Compilation: javac-algs4 Percolation.java Execution: java-algs4 Percolation
 * Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *
 * Description: Modeling Percolation like a boss. woot. woot.
 ******************************************************************************/
/**
 * @author CarterWarren
 *
 *         A {@link PercolationStats} performs a series of trials using the
 *         Percolation class. This program inputs the results into a array. The
 *         values in the array are used to calculate mean, standard deviation,
 *         confidence high and confidence low.
 */
public class PercolationStats {
	private double[] trialRuns; // stores the ratio of open sites to total sites from each trials index

	/**
	 * Constructs a new {@link PercolationStats} with a n-by-n grid and trials
	 * number of trials
	 * 
	 * @param n      number of rows and columns
	 * @param trials number of trials
	 */
	public PercolationStats(int n, int trials) {
		if (n < 1 || trials < 1) {
			throw new IllegalArgumentException("cannot create a 0-by-0 grid or can't have 0 trials");
		}
		trialRuns = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation perc = new Percolation(n);
			while (!perc.percolates()) {
				perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
			}
			trialRuns[i] = (double) (perc.numberOfOpenSites()) / (n * n);
		}
	}

	/**
	 * Returns the mean of the values in the trialRuns array.
	 * 
	 * @return the mean of the values in the trialRuns array
	 */
	public double mean() {
		return StdStats.mean(trialRuns);
	}

	/**
	 * Return the standard deviation of the values in the trialRuns array.
	 * 
	 * @return the standard deviation of the values in the trialRuns array
	 */
	public double stddev() {
		return StdStats.stddev(trialRuns);
	}

	/**
	 * Return the confidence low of the values in the trialRuns array.
	 * 
	 * @return the confidence low of the values in the trialRuns array
	 */
	public double confidenceLo() {
		return mean() - ((1.96 * stddev()) / (Math.sqrt((double) trialRuns.length)));
	}

	/**
	 * Return the confidence high of the values in the trialRuns array.
	 * 
	 * @return the confidence high of the values in the trialRuns array
	 */
	public double confidenceHi() {
		return mean() + ((1.96 * stddev()) / (Math.sqrt((double) trialRuns.length)));
	}

	/**
	 * Prints out the results of the program.
	 * 
	 * @param args program input
	 */
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); // converts our first argument to an integer
		int trials = Integer.parseInt(args[1]);
		PercolationStats percStats = new PercolationStats(n, trials);
		System.out.println("mean = " + percStats.mean());
		System.out.println("stddev = " + percStats.stddev());
		System.out.println(
				"95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
	}
}