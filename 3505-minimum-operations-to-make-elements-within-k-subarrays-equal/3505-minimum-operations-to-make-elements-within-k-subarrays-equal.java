
import java.util.*;

class Solution {
    class Group {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int x) {
            map.merge(x, 1, Integer::sum);
            size++;
            sum += x;
        }

        void remove(int x) {
            map.merge(x, -1, Integer::sum);
            if (map.get(x) == 0) map.remove(x);
            size--;
            sum -= x;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }
    }

    Group low = new Group();
    Group high = new Group();

    void balance() {
        while (low.size < high.size) {
            int v = high.first();
            high.remove(v);
            low.add(v);
        }

        while (low.size > high.size + 1) {
            int v = low.last();
            low.remove(v);
            high.add(v);
        }
    }

    void add(int x) {
        if (low.size == 0 || x <= low.last())
            low.add(x);
        else
            high.add(x);

        balance();
    }

    void remove(int x) {
        if (low.map.containsKey(x))
            low.remove(x);
        else
            high.remove(x);

        balance();
    }

    long cost() {
        long median = low.last();

        return median * low.size - low.sum
             + high.sum - median * high.size;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] costs = new long[n - x + 1];

        for (int i = 0; i < n; i++) {
            add(nums[i]);

            if (i >= x) {
                remove(nums[i - x]);
            }

            if (i >= x - 1) {
                costs[i - x + 1] = cost();
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j > 0 && i >= x &&
                    dp[i - x][j - 1] != INF) {

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + costs[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
