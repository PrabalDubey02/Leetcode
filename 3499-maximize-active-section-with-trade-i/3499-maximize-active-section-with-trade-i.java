class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";
        ArrayList<Integer> len = new ArrayList<>();
        ArrayList<Character> ch = new ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char c = t.charAt(i);
            int j = i;
            while (j < t.length() && t.charAt(j) == c) {
                j++;
            }
            ch.add(c);
            len.add(j - i);
            i = j;
        }

        int ans = ones;

        for (i = 1; i + 1 < len.size(); i++) {

            if (ch.get(i) == '1'
                    && ch.get(i - 1) == '0'
                    && ch.get(i + 1) == '0') {

                int gain = len.get(i - 1) + len.get(i + 1);
                ans = Math.max(ans, ones + gain);
            }
        }

        return Math.min(ans, s.length());
    }
}