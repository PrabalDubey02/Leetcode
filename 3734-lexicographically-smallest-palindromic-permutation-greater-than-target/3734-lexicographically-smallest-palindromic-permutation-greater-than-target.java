class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        String calendrix = s;

        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                if (mid != 0) {
                    return "";
                }
                mid = (char) ('a' + i);
            }
            cnt[i] /= 2;
        }

        int half = n / 2;

        // Try to keep left half same as target
        int[] left = cnt.clone();
        boolean possible = true;

        for (int i = 0; i < half; i++) {
            int x = target.charAt(i) - 'a';

            if (left[x] == 0) {
                possible = false;
                break;
            }

            left[x]--;
        }

        // If left half is same, check the palindrome
        if (possible) {
            StringBuilder first = new StringBuilder();

            for (int i = 0; i < half; i++) {
                first.append(target.charAt(i));
            }

            StringBuilder ans = new StringBuilder(first);

            if (n % 2 == 1) {
                ans.append(mid);
            }

            ans.append(first.reverse());

            if (ans.toString().compareTo(target) > 0) {
                return ans.toString();
            }
        }

        // Change one character of the left half
        for (int pos = half - 1; pos >= 0; pos--) {

            int[] temp = cnt.clone();

            // Keep characters before pos same as target
            boolean ok = true;

            for (int i = 0; i < pos; i++) {
                int x = target.charAt(i) - 'a';

                if (temp[x] == 0) {
                    ok = false;
                    break;
                }

                temp[x]--;
            }

            if (!ok) {
                continue;
            }

            int current = target.charAt(pos) - 'a';

            // Put the smallest character greater than target[pos]
            for (int c = current + 1; c < 26; c++) {

                if (temp[c] == 0) {
                    continue;
                }

                StringBuilder first = new StringBuilder();

                for (int i = 0; i < pos; i++) {
                    first.append(target.charAt(i));
                }

                first.append((char) ('a' + c));
                temp[c]--;

                // Fill remaining positions with smallest characters
                for (int x = 0; x < 26; x++) {
                    while (temp[x] > 0) {
                        first.append((char) ('a' + x));
                        temp[x]--;
                    }
                }

                StringBuilder ans = new StringBuilder(first);

                if (n % 2 == 1) {
                    ans.append(mid);
                }

                ans.append(new StringBuilder(first).reverse());

                return ans.toString();
            }
        }

        return "";
    }
}