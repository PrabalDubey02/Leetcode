class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;

        int[][] start = new int[n][2];

        for (int i = 0; i < n; i++) {
            start[i][0] = intervals[i][0];
            start[i][1] = i;
        }

        Arrays.sort(start, (a, b) -> a[0] - b[0]);

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];

            int low = 0;
            int high = n - 1;
            ans[i] = -1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (start[mid][0] >= end) {
                    ans[i] = start[mid][1];
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return ans ;
    }
}