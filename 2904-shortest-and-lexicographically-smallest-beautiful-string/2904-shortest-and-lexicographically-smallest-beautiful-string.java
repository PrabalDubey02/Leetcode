class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int count = 0;
        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                count++;
            }

            while (count > k) {
                if (s.charAt(left) == '1') {
                    count--;
                }
                left++;
            }

            while (count == k && s.charAt(left) == '0') {
                left++;
            }

            if (count == k) {
                String temp = s.substring(left, right + 1);

                if (ans.equals("") ||
                    temp.length() < ans.length() ||
                    (temp.length() == ans.length() && temp.compareTo(ans) < 0)) {
                    ans = temp;
                }
            }
        }

        return ans;
    }
}