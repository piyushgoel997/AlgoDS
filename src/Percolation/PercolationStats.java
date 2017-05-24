package Percolation;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by piyus on 20-03-2017 at 19:14.
 */
public class PercolationStats {
    private int n;
    private int trials;
    private double mean;
    private double[] x;

    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n can't be <= 0");
        } else if (trials <= 0) {
            throw new IllegalArgumentException("trials can't be <= 0");
        }
        this.n = n;
        this.trials = trials;
        x = new double[trials];
        for (int i = 0; i < trials; i++) {
            x[i] = work(new Percolation(n));
        }

    }

    private double work(Percolation percolation) {
        while (!percolation.percolates()) {
            percolation.open(StdRandom.uniform(1,n+1),StdRandom.uniform(1,n+1));
        }
        return percolation.numberOfOpenSites()/(n*n);
    }

    public double mean() {
        mean = 0;
        for (int i = 0; i < trials; i++) {
            mean += x[i];
        }
        mean = mean / trials;
        return mean;
    }

    public double stddev() {
        int sd = 0;
        for (int i = 0; i < trials; i++) {
            sd += (mean - x[i]) * (mean - x[i]);
        }
        sd = sd / (trials - 1);
        return Math.sqrt(sd);
    }

    public double confidenceLo() {
        return 0.95 * mean;
    }

    public double confidenceHi() {
        return 1.05 * mean;
    }

    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        int n = s.nextInt();
//        int t = s.nextInt();
//        for (int i = 0; i < t; i++) {
//
//        }

    }

}
