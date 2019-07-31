package Misc;

/**
 * Created by piyus on 30-09-2017 at 17:20.
 * Prime Sieve. All primes up to a number.
 */
public class SieveOfErastosthenes {
    private static boolean[] primeSieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 1; i <= n; i++) isPrime[i] = true;
        for (int i = 1; i <= n;) {
            if (isPrime[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}
