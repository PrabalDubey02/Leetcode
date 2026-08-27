class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] temp = count.clone();
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int ch = target.charAt(j) - 'a';
                temp[ch]--;

                if (temp[ch] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            int current = target.charAt(i) - 'a';
            int bigger = -1;

            for (int c = current + 1; c < 26; c++) {
                if (temp[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger == -1) continue;

            StringBuilder ans = new StringBuilder();
            ans.append(target.substring(0, i));
            ans.append((char) ('a' + bigger));

            temp[bigger]--;

            for (int c = 0; c < 26; c++) {
                while (temp[c] > 0) {
                    ans.append((char) ('a' + c));
                    temp[c]--;
                }
            }

            return ans.toString();
        }

        return "";
    }
}