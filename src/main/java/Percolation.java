import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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
public class Percolation {
	private boolean[][] grid; // true = open & false = closed
	private int numOfOpenSites;
	private WeightedQuickUnionUF percolation;
	private WeightedQuickUnionUF isFullUF;

	public Percolation(int n) {
		// TODO: create n-by-n grid, with all sites blocked
		percolation = new WeightedQuickUnionUF(n * n + 2); // plus to for the top and bottom virtual sites
		isFullUF = new WeightedQuickUnionUF(n * n + 1); // doesn't have a bottom virtual site to prevent backwash
		grid = new boolean[n][n];
		numOfOpenSites = 0;
		// union the top site with the top row (percolation)
		for (int i = 1; i <= n; i++) {
			percolation.union(0, i);
		}
		// union the bottom virtual site with the bottom row (percolation)

		// union the top site with the top row (isFullUF)
		for (int i = 1; i <= n; i++) {
			isFullUF.union(0, i);
		}
	}
	
	private int translate2DTo1D(int row, int col) {
		// TODO: convert a 2D position to a 1D position
		return 0;
	}

	public void open(int row, int col) {
		// TODO: open site (row, col) if it is not open already
		// open it in our grid(boolean grid, set to true)
		grid[row][col] = true;
		// open it in WeightedQuickUnion by union-ing it with the adjacent open sites
		numOfOpenSites++;
	}

	public boolean isOpen(int row, int col) {
		return !isFull(row, col);
	}

	public boolean isFull(int row, int col) {
		// TODO: is site (row, col) full?
		// check to see if THIS(row, col translated to 1D array index) site is connected
		// to the top virtual site (in isFullUF)
		return false;
	}

	public int numberOfOpenSites() {
		return numOfOpenSites;
	}

	public boolean percolates() {
		return percolation.connected(0, grid.length * grid.length + 1);
	}

	public static void main(String[] args) {
		// TODO: test client (optional)
	}
}
