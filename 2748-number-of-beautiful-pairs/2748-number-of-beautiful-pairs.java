class Solution {
    public int countBeautifulPairs(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int first = first(nums[i]);

            for (int j = i + 1; j < nums.length; j++) {
                int last = nums[j] % 10;

                if (gcd(first, last) == 1)
                    count++;
            }
        }

        return count;
    }

    private int first(int n) {
        while (n >= 10)
            n /= 10;
        return n;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}