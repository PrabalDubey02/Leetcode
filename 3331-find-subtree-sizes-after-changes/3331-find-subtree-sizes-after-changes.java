
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        update(0, tree, s, last, newParent);

        List<Integer>[] newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        int[] answer = new int[n];
        dfs(0, newTree, answer);

        return answer;
    }

    private void update(int node, List<Integer>[] tree,
                        String s, int[] last, int[] newParent) {

        int c = s.charAt(node) - 'a';
        int previous = last[c];

        if (previous != -1) {
            newParent[node] = previous;
        }

        last[c] = node;

        for (int child : tree[node]) {
            update(child, tree, s, last, newParent);
        }

        last[c] = previous;
    }

    private int dfs(int node, List<Integer>[] tree,
                    int[] answer) {

        int size = 1;

        for (int child : tree[node]) {
            size += dfs(child, tree, answer);
        }

        answer[node] = size;
        return size;
    }
}
