package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by piyus on 20-03-2017.
 */
public class Percolation {

    private static final int OPEN = 1;
    private static final int CLOSED = 0;

    private int[][] grid;

    private WeightedQuickUnionUF uf;
    private int n;
    private int numberOfOpenSites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new int[n][n];
        this.n = n;
        numberOfOpenSites = 0;
    }

    public void open(int row, int col) {
        row--;
        col--;
        if (row >= n || col >= n || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row + 1, col + 1)) {
            return;
        }
        if (row == 0) {
            uf.union(col, n * n);
        }
        if (row == n - 1) {
            uf.union(row * n + col, n * n + 1);
        }
        grid[row][col] = OPEN;
        if (row != (n - 1))
            if (grid[row + 1][col] == OPEN) {

                uf.union((row + 1) * n + col, row * n + col);
            }
        if (col != (n - 1))
            if (grid[row][col + 1] == OPEN) {
                uf.union(row * n + (col + 1), row * n + col);
            }
        if (row != 0)
            if (grid[row - 1][col] == OPEN) {
                uf.union((row - 1) * n + col, row * n + col);
            }
        if (col != 0)
            if (grid[row][col - 1] == OPEN) {
                uf.union(row * n + (col - 1), row * n + col);
            }
        numberOfOpenSites += 1;
    }

    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (row >= n || col >= n || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col] == OPEN) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        row--;
        col--;
        if (row >= n || col >= n || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return uf.connected(row * n + col, n * n);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return uf.connected(n * n, n * n + 1);
    }

    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        int n = s.nextInt();
//        int i = s.nextInt();
//        int j = s.nextInt();
//        Percolation p = new Percolation(n);
//        while (i != 0 && j !=0) {
//            p.open(i, j);
//            i = s.nextInt();
//            j = s.nextInt();
//        }
//        System.out.print(p.percolates());
//        System.out.print(p.isOpen(18,1));
//        System.out.print(p.isFull(18,1));
    }

}
