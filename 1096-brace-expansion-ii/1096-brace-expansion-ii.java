import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        return new ArrayList<>(solve(expression));
    }

    private Set<String> solve(String s) {
        List<Set<String>> parts = new ArrayList<>();
        Set<String> current = new TreeSet<>();
        current.add("");

        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            if (c == '{') {
                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') count++;
                    else if (s.charAt(j) == '}') count--;
                    j++;
                }

                Set<String> inside = solve(s.substring(i + 1, j - 1));
                current = multiply(current, inside);
                i = j;
            } else if (c == ',') {
                parts.add(current);
                current = new TreeSet<>();
                current.add("");
                i++;
            } else {
                Set<String> temp = new TreeSet<>();
                for (String x : current) {
                    temp.add(x + c);
                }
                current = temp;
                i++;
            }
        }

        parts.add(current);

        Set<String> result = new TreeSet<>();
        for (Set<String> part : parts) {
            result.addAll(part);
        }

        return result;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}