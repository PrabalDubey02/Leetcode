class Solution {
    static final long MOD = 1000000007;

    int n;
    long[] sum, sq, lazy;

    void push(int node, int l, int r) {
        if (lazy[node] == 0) return;

        long v = lazy[node];
        int m = (l + r) / 2;

        apply(node * 2, l, m, v);
        apply(node * 2 + 1, m + 1, r, v);

        lazy[node] = 0;
    }

    void apply(int node, int l, int r, long v) {
        long len = r - l + 1;
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + v * v % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int m = (l + r) / 2;
        update(node * 2, l, m, ql, qr);
        update(node * 2 + 1, m + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            update(1, 0, n - 1, last[nums[i]] + 1, i);
            ans = (ans + sq[1]) % MOD;
            last[nums[i]] = i;
        }

        return (int) ans;
    }
}