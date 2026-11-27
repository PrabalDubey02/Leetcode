class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] dp = new int[budget + 1];
        int min = Integer.MAX_VALUE;

        for (int[] item : items) {
            min = Math.min(min, item[1]);
        }

        for (int i = 0; i <= budget; i++) {
            dp[i] = i / min;
        }

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int count = 0;

            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    count++;
                }
            }

            for (int j = budget; j >= price; j--) {
                dp[j] = Math.max(dp[j], dp[j - price] + count);
            }
        }

        return dp[budget];
    }
}