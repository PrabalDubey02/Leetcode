class Solution {
    public int minAreaRect(int[][] points) {

        int n = points.length;
        int minArea = Integer.MAX_VALUE;

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(points[i][0] + "," + points[i][1]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1])
                    continue;
                if (set.contains(points[i][0] + "," + points[j][1]) &&
                    set.contains(points[j][0] + "," + points[i][1])) {

                    int l = Math.abs(points[i][0] - points[j][0]);
                    int b = Math.abs(points[i][1] - points[j][1]);

                    minArea = Math.min(minArea, l * b);
                }
            }
        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}