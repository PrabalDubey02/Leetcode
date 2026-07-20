class Solution {
    public boolean predictTheWinner(int[] nums) {
        return winner(nums,0,nums.length-1)>=0;
        
    }
       public int winner(int[] nums, int left, int right) {
        if (left == right) {
            return nums[right];
        }

        int selectLeft = nums[left] - winner(nums, left + 1, right);
        int selectRight = nums[right] - winner(nums, left, right - 1);

        return Math.max(selectLeft, selectRight);
    }
}