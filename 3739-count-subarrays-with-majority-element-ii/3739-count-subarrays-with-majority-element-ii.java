import java.util.*;

class Solution {

    class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 1];
        }

        void update(int index, int value) {
            while (index < bit.length) {
                bit[index] += value;
                index += index & -index;
            }
        }

        int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += bit[index];
                index -= index & -index;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        int[] sorted = prefix.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;

        for (int x : sorted) {
            if (!map.containsKey(x))
                map.put(x, rank++);
        }

        Fenwick bit = new Fenwick(rank);

        long ans = 0;

        for (int p : prefix) {
            int idx = map.get(p);
            ans += bit.query(idx - 1);
            bit.update(idx, 1);
        }

        return ans;
    }
}