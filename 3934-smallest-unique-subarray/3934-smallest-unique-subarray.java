import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int left = 1, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean check(int[] nums, int k) {
        Map<Long, Integer> map = new HashMap<>();

        long base = 100003;
        long mod1 = 1000000007;
        long mod2 = 1000000009;

        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        for (int i = 0; i < k; i++) {
            p1 = p1 * base % mod1;
            p2 = p2 * base % mod2;
        }

        for (int i = 0; i < nums.length; i++) {
            h1 = (h1 * base + nums[i]) % mod1;
            h2 = (h2 * base + nums[i]) % mod2;

            if (i >= k) {
                h1 = (h1 - nums[i - k] * p1 % mod1 + mod1) % mod1;
                h2 = (h2 - nums[i - k] * p2 % mod2 + mod2) % mod2;
            }

            if (i >= k - 1) {
                long key = (h1 << 32) | h2;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}