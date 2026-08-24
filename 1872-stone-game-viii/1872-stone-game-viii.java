class Solution {
    public int stoneGameVIII(int[] stones) {
        
        for (int i = 1; i < stones.length; i++) {
            stones[i] += stones[i - 1];
        }        
        int ans = stones[stones.length - 1];
        for (int i = stones.length - 2; i >= 1; i--) {
            ans = Math.max(ans, stones[i] - ans);
        }

        return ans;
    }
}