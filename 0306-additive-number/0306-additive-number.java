import java.math.BigInteger;
class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();

        for (int i = 1; i <= n / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) break;

            for (int j = 1; Math.max(i, j) <= n - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) break;

                if (isValid(num, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValid(String num, int start, int len1, int len2) {
        String s1 = num.substring(start, start + len1);
        String s2 = num.substring(start + len1, start + len1 + len2);

        java.math.BigInteger first = new java.math.BigInteger(s1);
        java.math.BigInteger second = new java.math.BigInteger(s2);

        int index = start + len1 + len2;

        while (index < num.length()) {
          
           BigInteger sum = first.add(second);
            String sumStr = sum.toString();

            if (!num.startsWith(sumStr, index)) {
                return false;
            }

            index += sumStr.length();
            first = second;
            second = sum;
        }

        return true;
    }
}