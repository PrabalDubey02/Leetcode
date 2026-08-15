class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        int zeros = 0;

        for (int x : nums) {
            xor ^= x;

            if (x == 0) {
                zeros++;
            }
        }

        
        if (xor != 0) {
            return n;
        }

        if (zeros == n) {
            return 0;
        }

       
        return n - 1;
    }
}