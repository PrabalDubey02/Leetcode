class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        final int MOD = 1_000_000_007;
        int n = s.length();

        long[] pow10 = new long[n + 1];
        long[] x = new long[n + 1];
        int[] idx = new int[n + 1];
        int[] sum = new int[n + 1];

        pow10[0] = 1;

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';

            pow10[i + 1] = (pow10[i] * 10) % MOD;
            idx[i + 1] = idx[i] + (d != 0 ? 1 : 0);

            if (d != 0) {
                x[i + 1] = (x[i] * 10 + d) % MOD;
            } else {
                x[i + 1] = x[i];
            }

            sum[i + 1] = sum[i] + d;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int cnt = idx[r + 1] - idx[l];

            long val = (x[r + 1] - (x[l] * pow10[cnt]) % MOD + MOD) % MOD;

            long digitSum = sum[r + 1] - sum[l];

            ans[i] = (int) ((val * digitSum) % MOD);
        }

        return ans;
    }
}