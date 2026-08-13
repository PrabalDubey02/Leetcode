class Solution {
    static class Node {
        int len;
        int left, right, best;
        char lc, rc;

        Node(int len, int left, int right, int best, char lc, char rc) {
            this.len = len;
            this.left = left;
            this.right = right;
            this.best = best;
            this.lc = lc;
            this.rc = rc;
        }
    }

    Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1, s);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].best;
        }

        return ans;
    }

    void build(int node, int l, int r, String s) {
        if (l == r) {
            char c = s.charAt(l);
            tree[node] = new Node(1, 1, 1, 1, c, c);
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, s);
        build(node * 2 + 1, mid + 1, r, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {
        int len = a.len + b.len;

        int left = a.left;
        int right = b.right;

        int best = Math.max(a.best, b.best);

        if (a.rc == b.lc) {
            best = Math.max(best, a.right + b.left);

            if (a.left == a.len)
                left = a.len + b.left;

            if (b.right == b.len)
                right = b.len + a.right;
        }

        return new Node(len, left, right, best, a.lc, b.rc);
    }

    void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            tree[node] = new Node(1, 1, 1, 1, c, c);
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid)
            update(node * 2, l, mid, idx, c);
        else
            update(node * 2 + 1, mid + 1, r, idx, c);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
}