class Solution {

    static final long LIMIT = 1000001;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        String middle = "";

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                middle = String.valueOf((char) ('a' + i));
                break;
            }
        }

        int[] half = new int[26];
        int length = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            length += half[i];
        }

        StringBuilder left = new StringBuilder();

        if (!buildKthPalindrome(half, k, left, length)) {
            return "";
        }

        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + middle + right;
    }

    private boolean buildKthPalindrome(int[] freq, long k,
                                       StringBuilder ans, int len) {

        if (len == 0) {
            return true;
        }

        for (int i = 0; i < 26; i++) {

            if (freq[i] == 0) {
                continue;
            }

            freq[i]--;

            long ways = multinomial(freq);

            if (k <= ways) {
                ans.append((char) ('a' + i));
                return buildKthPalindrome(freq, k, ans, len - 1);
            }

            k -= ways;
            freq[i]++;
        }

        return false;
    }

    private long multinomial(int[] count) {

        int total = 0;

        for (int x : count) {
            total += x;
        }

        long answer = 1;

        for (int i = 0; i < 26; i++) {

            answer *= binomial(total, count[i]);

            if (answer >= LIMIT) {
                return LIMIT;
            }

            total -= count[i];
        }

        return answer;
    }

    private long binomial(int n, int r) {

        if (r > n) {
            return 0;
        }

        r = Math.min(r, n - r);

        long answer = 1;

        for (int i = 1; i <= r; i++) {

            answer = answer * (n - i + 1) / i;

            if (answer >= LIMIT) {
                return LIMIT;
            }
        }

        return answer;
    }
}