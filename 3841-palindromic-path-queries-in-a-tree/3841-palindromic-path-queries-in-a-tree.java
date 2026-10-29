import java.util.*;

class Solution {
    int n, LOG, timer;
    int[] tin, tout, depth, parent, pref;
    int[][] up;
    int[] lazy;
    char[] s;
    List<Integer>[] adj;

    void dfs() {
        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int node = stack[--top];

            if (node < 0) {
                node = -node - 1;
                tout[node] = timer - 1;
                continue;
            }

            tin[node] = timer++;

            pref[node] = (node == 0 ? 0 : pref[parent[node]])
                       ^ (1 << (s[node] - 'a'));

            stack[top++] = -node - 1;

            for (int v : adj[node]) {
                if (v == parent[node]) continue;

                parent[v] = node;
                depth[v] = depth[node] + 1;

                stack[top++] = v;
            }
        }
    }

    void buildLCA() {
        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            lazy[node] ^= val;
            return;
        }

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);
    }

    int query(int node, int l, int r, int idx) {
        if (l == r) return lazy[node];

        int mid = (l + r) / 2;

        if (idx <= mid) {
            return lazy[node] ^ query(node * 2, l, mid, idx);
        }

        return lazy[node] ^ query(node * 2 + 1, mid + 1, r, idx);
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.s = s.toCharArray();

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        pref = new int[n];

        up = new int[LOG][n];

        timer = 0;

        dfs();
        buildLCA();

        lazy = new int[4 * n];

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (this.s[u] - 'a');
                int newMask = 1 << (c - 'a');

                int delta = oldMask ^ newMask;

                this.s[u] = c;

                update(1, 0, n - 1, tin[u], tout[u], delta);

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int w = lca(u, v);

                int maskU = pref[u] ^ query(1, 0, n - 1, tin[u]);
                int maskV = pref[v] ^ query(1, 0, n - 1, tin[v]);

                int mask = maskU ^ maskV
                         ^ (1 << (this.s[w] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }
}