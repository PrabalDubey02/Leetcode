import java.util.*;

class Solution {

    static final int[][] FACTORS = {
        {}, {}, {2}, {3}, {2,2}, {5},
        {2,3}, {7}, {2,2,2}, {3,3}
    };

    public String smallestNumber(String num, long t) {

        int[] need = getPrimeFactors(t);

        if (need == null)
            return "-1";

        int[] factorCount = getDigitCounts(need);

        if (sum(factorCount) > num.length())
            return build(factorCount);

        int[] prefix = getPrimeCount(num);

        int zero = num.indexOf('0');

        if (zero == -1) {
            zero = num.length();

            if (isEnough(prefix, need))
                return num;
        }

        for (int i = num.length() - 1; i >= 0; i--) {

            int d = num.charAt(i) - '0';

            remove(prefix, FACTORS[d]);

            int space = num.length() - 1 - i;

            if (i > zero)
                continue;

            for (int bigger = d + 1; bigger <= 9; bigger++) {

                int[] remaining = new int[4];

                int[] biggerFactors = new int[4];
                add(biggerFactors, FACTORS[bigger]);

                for (int j = 0; j < 4; j++) {
                    remaining[j] = Math.max(
                        0,
                        need[j] - prefix[j] - biggerFactors[j]
                    );
                }

                int[] digits = getDigitCounts(remaining);

                if (sum(digits) <= space) {

                    int ones = space - sum(digits);

                    StringBuilder ans = new StringBuilder();

                    ans.append(num, 0, i);
                    ans.append(bigger);

                    for (int k = 0; k < ones; k++)
                        ans.append('1');

                    ans.append(build(digits));

                    return ans.toString();
                }
            }
        }

        int[] digits = getDigitCounts(need);

        int total = sum(digits);

        if (total > num.length() + 1)
            return "-1";

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < num.length() + 1 - total; i++)
            ans.append('1');

        ans.append(build(digits));

        return ans.toString();
    }

    int[] getPrimeFactors(long t) {

        int[] ans = new int[4];
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {

            while (t % primes[i] == 0) {
                t /= primes[i];
                ans[i]++;
            }
        }

        if (t != 1)
            return null;

        return ans;
    }

    int[] getPrimeCount(String s) {

        int[] ans = new int[4];

        for (char c : s.toCharArray())
            add(ans, FACTORS[c - '0']);

        return ans;
    }

    void add(int[] a, int[] factors) {

        for (int x : factors) {

            if (x == 2)
                a[0]++;
            else if (x == 3)
                a[1]++;
            else if (x == 5)
                a[2]++;
            else
                a[3]++;
        }
    }

    void remove(int[] a, int[] factors) {

        for (int x : factors) {

            if (x == 2)
                a[0]--;
            else if (x == 3)
                a[1]--;
            else if (x == 5)
                a[2]--;
            else
                a[3]--;
        }
    }

    boolean isEnough(int[] have, int[] need) {

        for (int i = 0; i < 4; i++) {

            if (have[i] < need[i])
                return false;
        }

        return true;
    }

    int[] getDigitCounts(int[] c) {

        int[] res = new int[8];

        int two = c[0];
        int three = c[1];

        // 2^3 = 8
        res[6] = two / 3;
        two %= 3;

        // 3^2 = 9
        res[7] = three / 2;
        three %= 2;

        // 2^2 = 4
        res[2] = two / 2;
        two %= 2;

        // 2 * 3 = 6
        int six = 0;

        if (two == 1 && three == 1) {

            two = 0;
            three = 0;
            six = 1;
        }

        // 3 * 4 = 2 * 6
        if (three == 1 && res[2] == 1) {

            two = 1;
            six = 1;

            three = 0;
            res[2] = 0;
        }

        res[0] = two;
        res[1] = three;
        res[3] = c[2];
        res[4] = six;
        res[5] = c[3];

        return res;
    }

    int sum(int[] a) {

        int ans = 0;

        for (int x : a)
            ans += x;

        return ans;
    }

    String build(int[] cnt) {

        StringBuilder s = new StringBuilder();

        for (int d = 2; d <= 9; d++) {

            int index = d - 2;

            for (int i = 0; i < cnt[index]; i++)
                s.append(d);
        }

        return s.toString();
    }
}