class Solution {
    public int thirdMax(int[] nums) {

        long max = Long.MIN_VALUE;
        long smax = Long.MIN_VALUE;
        long tmax = Long.MIN_VALUE;

        for (int num : nums) {

            // Ignore duplicates
            if (num == max || num == smax || num == tmax)
                continue;

            if (num > max) {
                tmax = smax;
                smax = max;
                max = num;
            }
            else if (num > smax) {
                tmax = smax;
                smax = num;
            }
            else if (num > tmax) {
                tmax = num;
            }
        }

        return tmax == Long.MIN_VALUE ? (int) max : (int) tmax;
    }
}