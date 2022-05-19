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
public class PercolationStats {
	private double[] trialRuns;

	public PercolationStats(int n, int trials) {
		// TODO: perform trials independent experiments on an n-by-n grid
		trialRuns = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation perc = new Percolation(n);
			while (!perc.percolates()) {
				perc.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
			}
			trialRuns[i] =  perc.numberOfOpenSites() / (n*n);
		}
	}

	public double mean() {
		// TODO: calculate sample mean of percolation threshold
		return StdStats.mean(trialRuns);
	}

	public double stddev() {
		// TODO: calculate sample standard deviation of percolation threshold
		return StdStats.stddev(trialRuns);
	}

	public double confidenceLo() {
		// TODO: return low endpoint of 95% confidence interval
		return mean() - (1.96 * stddev()) / (Math.sqrt((double) trialRuns.length));
	}

	public double confidenceHi() {
		// TODO: return high endpoint of 95% confidence interval
		return mean() + ((1.96 * stddev()) / (Math.sqrt((double) trialRuns.length)));
	}

	public static void main(String[] args) {
		// test client (described at
		// http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)
		System.out.println(args[0] + " " + args[1]);
		int n = Integer.parseInt(args[0]); // converts our first argument to an integer
		int trials = Integer.parseInt(args[1]);
		PercolationStats percStats = new PercolationStats(n, trials);
		System.out.println("mean = " + percStats.mean());
		System.out.println("stddev = " + percStats.stddev());
		System.out.println("95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
	}
}