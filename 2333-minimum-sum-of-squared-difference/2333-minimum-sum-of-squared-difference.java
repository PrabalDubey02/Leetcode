class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        long[] diff = new long[n];
        long sum = 0;
        long max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
            max = Math.max(max, diff[i]);
        }

        if (k >= sum) {
            return 0;
        }

        long left = 0, right = max;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long need = 0;

            for (long d : diff) {
                if (d > mid) {
                    need += d - mid;
                }
            }

            if (need <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (diff[i] > left) {
                k -= diff[i] - left;
                diff[i] = left;
            }
            ans += diff[i] * diff[i];
        }

        for (int i = 0; i < n && k > 0; i++) {
            if (diff[i] == left && diff[i] > 0) {
                ans -= diff[i] * diff[i];
                diff[i]--;
                ans += diff[i] * diff[i];
                k--;
            }
        }

        return ans;
    }
}