class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {

        int m = primes.length;

        int[] dp = new int[n];
        dp[0] = 1;

        int[] index = new int[m];

        for (int i = 1; i < n; i++) {

            long next = Long.MAX_VALUE;

           
            for (int j = 0; j < m; j++) {
                next = Math.min(next, (long) primes[j] * dp[index[j]]);
            }

            dp[i] = (int) next;

            
            for (int j = 0; j < m; j++) {
                if ((long) primes[j] * dp[index[j]] == next) {
                    index[j]++;
                }
            }
        }

        return dp[n - 1];
    }
}