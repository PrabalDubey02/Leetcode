import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;

            while (lo < hi) {
                int mid = (lo + hi) / 2;

                if (a[mid][0] > a[i][1])
                    hi = mid;
                else
                    lo = mid + 1;
            }

            next[i] = lo;
        }

        long[][] dp = new long[n + 1][5];
        int[][][] path = new int[n + 1][5][];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = dp[i + 1][k];
                path[i][k] = path[i + 1][k];

                if (k < 4) {
                    long value = a[i][2] + dp[next[i]][k + 1];

                    int[] p = path[next[i]][k + 1];

                    if (p == null)
                        p = new int[0];

                    int[] candidate = new int[p.length + 1];
                    candidate[0] = a[i][3];

                    for (int j = 0; j < p.length; j++)
                        candidate[j + 1] = p[j];

                    Arrays.sort(candidate);

                    if (value > dp[i][k] ||
                        (value == dp[i][k] &&
                         smaller(candidate, path[i][k]))) {
                        dp[i][k] = value;
                        path[i][k] = candidate;
                    }
                }
            }
        }

        return path[0][0] == null ? new int[0] : path[0][0];
    }

    private boolean smaller(int[] a, int[] b) {
        if (b == null)
            return true;

        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] != b[i])
                return a[i] < b[i];
        }

        return a.length < b.length;
    }
}