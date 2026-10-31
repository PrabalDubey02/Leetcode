class Solution {
    static final int MOD = 1000000007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) {
                    continue;
                }

                if (grid[i][j] == 1) {
                    continue;
                }

                move(grid, dp, i, j, 0);
                move(grid, dp, i, j, 1);
            }
        }

        return dp[m - 1][n - 1];
    }

    private void move(int[][] grid, int[][] dp, int i, int j, int dir) {
        int m = grid.length;
        int n = grid[0].length;

        int r = i;
        int c = j;

        if (dir == 0) {
            c++;
        } else {
            r++;
        }

        while (r < m && c < n) {

            if (grid[r][c] == 0) {
                dp[r][c] = (int) (
                    (dp[r][c] + (long) dp[i][j]) % MOD
                );
                return;
            }

            if (dir == 0) {
                r++;
                dir = 1;
            } else {
                c++;
                dir = 0;
            }
        }
    }
}