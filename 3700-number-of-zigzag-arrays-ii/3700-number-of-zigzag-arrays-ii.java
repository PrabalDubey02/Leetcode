class Solution {
    static final long MOD = 1000000007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] T = new long[size][size];

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < x; y++) {
                T[x][m + y] = 1;
            }

            for (int y = x + 1; y < m; y++) {
                T[m + x][y] = 1;
            }
        }

        long[] base = new long[size];

        for (int a = 0; a < m; a++) {
            for (int b = 0; b < m; b++) {
                if (a < b) {
                    base[b]++;
                } else if (a > b) {
                    base[m + b]++;
                }
            }
        }

        long[][] P = power(T, n - 2);

        long[] res = multiply(P, base);

        long ans = 0;

        for (long v : res) {
            ans = (ans + v) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i] = (res[i] + A[i][j] * v[j]) % MOD;
            }
        }

        return res;
    }

    private long[][] power(long[][] A, long exp) {
        int n = A.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, A);
            }

            A = multiply(A, A);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    res[i][j] =
                        (res[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}