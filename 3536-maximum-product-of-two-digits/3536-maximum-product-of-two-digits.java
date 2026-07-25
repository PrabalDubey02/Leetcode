class Solution {
    public int maxProduct(int n) {
       List<Integer> ans = new ArrayList<>();

        while (n > 0) {
            ans.add(n % 10);
            n /= 10;
        }

        Collections.sort(ans);

        int m = ans.size();
        return ans.get(m - 1) * ans.get(m - 2);
    }
}