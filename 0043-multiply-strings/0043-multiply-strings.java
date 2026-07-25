class Solution {
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int n = num1.length();
        int m = num2.length();

        int[] ans = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {

            for (int j = m - 1; j >= 0; j--) {

                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                int sum = mul + ans[i + j + 1];

                ans[i + j + 1] = sum % 10;
                ans[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int num : ans) {
            if (sb.length() == 0 && num == 0) {
                continue;
            }
            sb.append(num);
        }

        return sb.toString();
    }
}