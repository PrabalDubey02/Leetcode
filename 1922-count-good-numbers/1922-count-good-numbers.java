class Solution {
    long MOD = 1000000007;

    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = n / 2;

        return (int)((power(5, even) * power(4, odd)) % MOD);
    }

    private long power(long x, long n) {
        if (n == 0) return 1;

        if (n % 2 == 0) {
            long half = power(x, n / 2);
            return (half * half) % MOD;
        } else {
            return (x * power(x, n - 1)) % MOD;
        }
    }
}