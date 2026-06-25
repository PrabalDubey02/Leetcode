class Solution {
    public int reverse(int x) {
        int digit = 0;

        while (x != 0) {

            int rem = x % 10;

            if (digit > Integer.MAX_VALUE / 10 ||
               (digit == Integer.MAX_VALUE / 10 && rem > 7)) {
                return 0;
            }

            if (digit < Integer.MIN_VALUE / 10 ||
               (digit == Integer.MIN_VALUE / 10 && rem < -8)) {
                return 0;
            }

            digit *= 10;
            digit += rem;

            x /= 10;
        }

        return digit;
    }
}