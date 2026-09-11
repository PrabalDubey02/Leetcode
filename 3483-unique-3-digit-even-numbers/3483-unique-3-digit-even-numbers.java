class Solution {
    public int totalNumbers(int[] digits) {
        int count = 0;

        for (int num = 100; num <= 998; num++) {
            // Number must be even
            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            int[] freq = new int[10];

            // Count available digits
            for (int d : digits) {
                freq[d]++;
            }

            // Use each copy at most once
            if (freq[a] > 0) {
                freq[a]--;
            } else {
                continue;
            }

            if (freq[b] > 0) {
                freq[b]--;
            } else {
                continue;
            }

            if (freq[c] > 0) {
                freq[c]--;
            } else {
                continue;
            }

            count++;
        }

        return count;
    }
}