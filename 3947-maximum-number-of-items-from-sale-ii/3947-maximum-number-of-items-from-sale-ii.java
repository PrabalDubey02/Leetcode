class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int[] gain = new int[n + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                gain[i] += freq[j];
            }
            gain[i]--;
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        int minPrice = items[0][1];
        int ans = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (price >= 2L * minPrice) {
                break;
            }

            int count = Math.min(gain[factor], budget / price);

            ans += 2 * count;
            budget -= count * price;
        }

        ans += budget / minPrice;

        return ans;
    }
}