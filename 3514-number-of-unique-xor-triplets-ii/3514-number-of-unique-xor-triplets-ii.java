class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;

        boolean[] one = new boolean[MAX];
        boolean[] two = new boolean[MAX];
        boolean[] three = new boolean[MAX];

        for (int x : nums) {
            boolean[] nextThree = three.clone();
            boolean[] nextTwo = two.clone();

            for (int i = 0; i < MAX; i++) {
                if (two[i]) nextThree[i ^ x] = true;
                if (one[i]) nextTwo[i ^ x] = true;
            }

            nextTwo[x ^ x] = true;
            nextThree[x] = true;
            one[x] = true;

            two = nextTwo;
            three = nextThree;
        }

        int ans = 0;
        for (boolean b : three) {
            if (b) ans++;
        }

        return ans;
    }
}