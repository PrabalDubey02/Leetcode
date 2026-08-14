class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int[] nums, List<Integer> current,
                            List<List<Integer>> ans) {

        if (current.size() == nums.length) {
            ans.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {
            if (current.contains(num)) continue;

            current.add(num);
            backtrack(nums, current, ans);
            current.remove(current.size() - 1);
        }
    }
}