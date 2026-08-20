class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int ans = 2 * n;

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << col));
            }
        }

        for (int mask : map.values()) {
            boolean left = (mask & ((1 << 2) | (1 << 3) | (1 << 4) | (1 << 5))) == 0;
            boolean right = (mask & ((1 << 6) | (1 << 7) | (1 << 8) | (1 << 9))) == 0;
            boolean middle = (mask & ((1 << 4) | (1 << 5) | (1 << 6) | (1 << 7))) == 0;

            if (left && right) {
                ans -= 0;
            } else if (left || right || middle) {
                ans -= 1;
            } else {
                ans -= 2;
            }
        }

        return ans;
    }
}