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
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		// TODO: create n-by-n grid, with all sites blocked
		percolation = new WeightedQuickUnionUF(n * n + 2); // plus to for the top and bottom virtual sites
		isFullUF = new WeightedQuickUnionUF(n * n + 1); // doesn't have a bottom virtual site to prevent backwash
		grid = new boolean[n][n];
		numOfOpenSites = 0;
		// union the top site with the top row (percolation)
		for (int i = 1; i <= grid.length; i++) {
			percolation.union(0, i);
		}
		// union the bottom virtual site with the bottom row (percolation)
		for (int i = 0; i < grid.length; i++) {
			percolation.union(grid.length * grid.length + 2, 1 + grid.length * grid.length - i);
		}
		// union the top site with the top row (isFullUF)
		for (int i = 1; i <= grid.length; i++) {
			isFullUF.union(0, i);
		}
	}

	private int translate2DTo1D(int row, int col) {
		// convert a 2D position to a 1D position
		return (row * grid.length) + col + 1; // if we get rid of the bottom virtual site then there's no need to add
												// one
	}

	public void open(int row, int col) {
		if (row > grid.length || row < 1 || col > grid.length || col < 1) {
			throw new IllegalArgumentException();
		}
		int gridRow = row - 1;
		int gridCol = col - 1;
		if (grid[gridRow][gridCol]) {
			return;
		}

		numOfOpenSites++;
		int currentPosition1D = translate2DTo1D(gridRow, gridCol);
		// open it in our grid(boolean grid, set to true)
		grid[gridRow][gridCol] = true;
		// open it in WeightedQuickUnion by union-ing it with the adjacent open sites

		// below
		if (gridRow + 1 < grid.length && grid[gridRow + 1][gridCol]) {
			percolation.union(currentPosition1D, translate2DTo1D(gridRow + 1, gridCol));
			isFullUF.union(currentPosition1D, translate2DTo1D(gridRow + 1, gridCol));

		}

		// above
		if (gridRow - 1 >= 0 && grid[gridRow - 1][gridCol]) {
			percolation.union(currentPosition1D, translate2DTo1D(gridRow - 1, gridCol));
			percolation.union(currentPosition1D, translate2DTo1D(gridRow - 1, gridCol));
		}

		// right
		if (gridCol + 1 < grid.length && grid[gridRow][gridCol + 1]) {
			percolation.union(currentPosition1D, translate2DTo1D(gridRow, gridCol + 1));
			percolation.union(currentPosition1D, translate2DTo1D(gridRow, gridCol + 1));
		}

		// left
		if (gridCol - 1 >= 0 && grid[gridRow][gridCol - 1]) {
			percolation.union(currentPosition1D, translate2DTo1D(gridRow, gridCol - 1));
			percolation.union(currentPosition1D, translate2DTo1D(gridRow, gridCol - 1));
		}

	}

	public boolean isOpen(int row, int col) {
		return grid[row - 1][col - 1];
	}

	public boolean isFull(int row, int col) {
		return grid[row - 1][col - 1] && isFullUF.connected(translate2DTo1D(row - 1, col - 1), 0);
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
