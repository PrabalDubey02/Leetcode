import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) arr[i] = new int[]{nums[i], i};
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[arr[i][1]] = i;

        int LOG = 18;
        int[][] jump = new int[LOG][n];

        int r = 0;
        for (int i = 0; i < n; i++) {
            if (r < i) r = i;
            while (r + 1 < n && arr[r + 1][0] - arr[i][0] <= maxDiff) r++;
            jump[0][i] = r;
        }

        for (int k = 1; k < LOG; k++)
            for (int i = 0; i < n; i++)
                jump[k][i] = jump[k - 1][jump[k - 1][i]];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = pos[queries[i][0]], v = pos[queries[i][1]];
            if (u > v) { int t = u; u = v; v = t; }

            if (u == v) { ans[i] = 0; continue; }
            if (jump[LOG - 1][u] < v) { ans[i] = -1; continue; }

            int cur = u, dist = 0;
            for (int k = LOG - 1; k >= 0; k--)
                if (jump[k][cur] < v) { cur = jump[k][cur]; dist += 1 << k; }

            ans[i] = dist + 1;
        }

        return ans;
    }
}