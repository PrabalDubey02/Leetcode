class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];

        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int ans = 0;
        boolean odd = false;

        for (int x : count) {
            ans += (x / 2) * 2;

            if (x % 2 == 1) {
                odd = true;
            }
        }

        if (odd) {
            ans++;
        }

        return ans;
    }
}