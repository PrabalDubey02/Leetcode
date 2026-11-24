class Solution {
    public int maxLength(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            long prod = 1;
            long g = 0;
            long l = 1;

            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                g = gcd(g, nums[j]);
                l = l * nums[j] / gcd(l, nums[j]);

                if (prod == g * l) {
                    ans = Math.max(ans, j - i + 1);
                }

                if (prod > 25200) {
                    break;
                }
            }
        }

        return ans;
    }

    long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}