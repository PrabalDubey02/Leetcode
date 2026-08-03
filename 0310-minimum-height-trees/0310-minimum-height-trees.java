class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] degree = new int[n];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);

            degree[u]++;
            degree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.add(i);
            }
        }

        int remaining = n;

        while (remaining > 2) {
            int size = queue.size();
            remaining -= size;

            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                for (int next : graph[node]) {
                    degree[next]--;

                    if (degree[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }

        return new ArrayList<>(queue);
    }
}