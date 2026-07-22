import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) if (s.charAt(i) == '1') ones++;

        List<int[]> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1)[1]++;
                } else {
                    zeroGroups.add(new int[]{i, 1});
                }
            }
            zeroGroupIndex[i] = zeroGroups.size() - 1;
        }

        List<Integer> ans = new ArrayList<>();
        int m = zeroGroups.size();
        if (m == 0) {
            for (int qi = 0; qi < queries.length; qi++) ans.add(ones);
            return ans;
        }

        int[] sums = new int[Math.max(0, m - 1)];
        for (int k = 0; k < sums.length; k++)
            sums[k] = zeroGroups.get(k)[1] + zeroGroups.get(k + 1)[1];

        SparseTable st = sums.length > 0 ? new SparseTable(sums) : null;

        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0], r = queries[qi][1];
            int idxL = zeroGroupIndex[l], idxR = zeroGroupIndex[r];

            int left = (idxL == -1) ? -1 : zeroGroups.get(idxL)[1] - (l - zeroGroups.get(idxL)[0]);
            int right = (idxR == -1) ? -1 : r - zeroGroups.get(idxR)[0] + 1;

            int endGroupR = (s.charAt(r) == '1') ? idxR : idxR - 1;
            int startAdj = idxL + 1, endAdj = endGroupR - 1;

            int best = ones;

            if (s.charAt(l) == '0' && s.charAt(r) == '0' && idxL + 1 == idxR) {
                best = Math.max(best, ones + left + right);
            } else if (startAdj <= endAdj) {
                best = Math.max(best, ones + st.query(startAdj, endAdj));
            }

            if (s.charAt(l) == '0' && idxL + 1 <= endGroupR)
                best = Math.max(best, ones + left + zeroGroups.get(idxL + 1)[1]);

            if (s.charAt(r) == '0' && idxL < idxR - 1)
                best = Math.max(best, ones + right + zeroGroups.get(idxR - 1)[1]);

            ans.add(best);
        }

        return ans;
    }

    static class SparseTable {
        int[][] table;
        int[] log;

        SparseTable(int[] arr) {
            int n = arr.length;
            int LOG = 1;
            while ((1 << LOG) <= n) LOG++;
            table = new int[LOG + 1][n];
            table[0] = arr.clone();
            for (int i = 1; i <= LOG; i++)
                for (int j = 0; j + (1 << i) <= n; j++)
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j + (1 << (i - 1))]);
            log = new int[n + 1];
            for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;
        }

        int query(int l, int r) {
            int i = log[r - l + 1];
            return Math.max(table[i][l], table[i][r - (1 << i) + 1]);
        }
    }
}