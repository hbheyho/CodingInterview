package CrackingTheCodeInterview.graph;

import java.util.*;

/**
 * @Author: HB
 * @Description: 面试题04.01 - 节点间通路
 *               描述: 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 *               Case:
 *               Input: n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 *               Output: true
 *               Limit:
 *               Remark:
 * @CreateDate: 20:51 2021/4/19
 */

public class Q24 {

    /**
     * @Author: HB
     * @Description: BFS解法
     * @Date: 20:53 2021/4/19
     * @Params: null
     * @Returns:
    */
    // 使用邻接表存储图
    int[] h, e, ne;
    int idx = 0;
    // BFS解法
    public boolean findWhetherExistsPathByBFS(int n, int[][] graph, int start, int target) {

        Deque<Integer> queue = new LinkedList<>();
        // 节点是否被访问
        boolean[] visited = new boolean[n];
        // 初始化邻接表
        h = new int[n];
        e = new int[2 * n];
        ne = new int[2 * n];
        Arrays.fill(h, -1);
        for (int i = 0; i < graph.length; i++) {
            add(graph[i][0], graph[i][1]);
        }

        visited[start] = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            // 取出一个节点, 开始行走
            for (int i = h[node]; i != -1; i = ne[i]) {
                int nextNode = e[i];
                if (visited[nextNode])
                    continue;
                if (nextNode == target)
                    return true;
                queue.offer(nextNode);
                visited[nextNode] = true;
            }
        }

        return false;
    }

    // 建立节点关联
    public void add (int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    /**
     * @Author: HB
     * @Description: BFS解法二
     * @Date: 20:53 2021/4/19
     * @Params: null
     * @Returns:
    */
    public boolean findWhetherExistsPatByBFS2(int n, int[][] graph, int start, int target) {

        Deque<Integer> queue = new LinkedList<>();
        // 节点是否被访问
        boolean[] visited = new boolean[n];
        // 使用邻接表存储图
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if (!graphMap.containsKey(graph[i][0])) {
                graphMap.put(graph[i][0], new ArrayList<>());
            }
            graphMap.get(graph[i][0]).add(graph[i][1]);
        }

        visited[start] = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            // 取出一个节点, 开始行走
            List<Integer> list = graphMap.get(node);
            if (list == null)
                continue;
            for (int nextNode : list) {
                if (visited[nextNode])
                    continue;
                if (nextNode == target)
                    return true;
                queue.offer(nextNode);
                visited[nextNode] = true;
            }
        }

        return false;
    }

    /**
     * @Author: HB
     * @Description: DFS解法
     * @Date: 20:54 2021/4/19
     * @Params: null
     * @Returns:
    */
    public boolean findWhetherExistsPathByDFS(int n, int[][] graph, int start, int target) {

        int len = graph.length;
        // 节点是否被访问
        boolean[] visited = new boolean[n];

        // 使用邻接表存储图
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int j = 0; j < len; j++) {
            // 根据graph数组，把边的头到尾存在edges里
            edges.get(graph[j][0]).add(graph[j][1]);
        }

        return dfs (edges, start, target,visited);
    }

    public boolean dfs (List<List<Integer>> edges, int start, int target, boolean[] visited) {

        List<Integer> nodeList = edges.get(start);
        if (nodeList.contains(target)) {
            return true;
        } else if (!visited[start]) {
            visited[start] = true;
            for (int nextNode : nodeList) {
                if(dfs (edges, nextNode, target, visited))
                    return true;
            }
        }
        return false;
    }

}
