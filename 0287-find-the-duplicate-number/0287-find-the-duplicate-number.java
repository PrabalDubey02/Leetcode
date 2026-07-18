class Solution {
    public int findDuplicate(int[] nums) {
        int i = 0;

        while (i < nums.length) {
            int idx = nums[i] - 1;

            if (idx == i) {
                i++;
                continue;
            }

            if (nums[i] == nums[idx]) {
                return nums[i];
            }

            swap(nums, i, idx);
        }

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}