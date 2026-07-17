class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {

        int M = 0;

        for (int x : nums) M = Math.max(M, x);

        int[] cnt = new int[M + 1];

        for (int x : nums) cnt[x]++;

        long[] exact = new long[M + 1];

        for (int d = M; d >= 1; d--) {

            long multiple = 0;

            for (int m = d; m <= M; m += d) {

                multiple += cnt[m];

            }

            long totalPairsDivByD = multiple * (multiple - 1) / 2;

            long subtract = 0;

            for (int m = 2 * d; m <= M; m += d) {

                subtract += exact[m];

            }

            exact[d] = totalPairsDivByD - subtract;

        }

        

        long[] prefix = new long[M + 1];

        prefix[0] = 0;

        for (int d = 1; d <= M; d++) {

            prefix[d] = prefix[d - 1] + exact[d];

        }

        int q = queries.length;

        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {

            long target = queries[i] + 1; 
            int lo = 1, hi = M;

            while (lo < hi) {

                int mid = lo + (hi - lo) / 2;

                if (prefix[mid] >= target) hi = mid;

                else lo = mid + 1;

            }

            ans[i] = lo;

        }

        return ans;

    }

}