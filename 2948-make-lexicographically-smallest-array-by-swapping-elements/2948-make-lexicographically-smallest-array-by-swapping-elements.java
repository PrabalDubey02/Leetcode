import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> group = new HashMap<>();
        int groupId = 0;

        group.put(sorted[0], groupId);

        for (int i = 1; i < n; i++) {
            if (sorted[i] - sorted[i - 1] > limit) {
                groupId++;
            }
            group.put(sorted[i], groupId);
        }

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int x : nums) {
            int g = group.get(x);

            if (!map.containsKey(g)) {
                map.put(g, new PriorityQueue<>());
            }

            map.get(g).add(x);
        }

        for (int i = 0; i < n; i++) {
            int g = group.get(nums[i]);
            nums[i] = map.get(g).poll();
        }

        return nums;
    }
}