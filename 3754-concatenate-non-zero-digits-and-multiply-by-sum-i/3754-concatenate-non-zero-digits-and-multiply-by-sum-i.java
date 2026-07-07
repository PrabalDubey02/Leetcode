class Solution {
    public long sumAndMultiply(int n) {
        String str = String.valueOf(n);

        long num = 0;
        long sum = 0;

        for (char ch : str.toCharArray()) {
            if (ch != '0') {
                int digit = ch - '0';
                num = num * 10 + digit;
                sum += digit;
            }
        }

        return num * sum;
    }
}