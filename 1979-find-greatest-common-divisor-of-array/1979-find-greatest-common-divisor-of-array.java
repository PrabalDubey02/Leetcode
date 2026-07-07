class Solution {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);

        int small = nums[0];
        int large = nums[nums.length - 1];

        for (int i = small; i >= 1; i--) {
            if (small % i == 0 && large % i == 0)
                return i;
        }

        return 1;
    }
}