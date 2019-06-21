/*
This question is from https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
Difficulty: medium

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


*/
// T:O(N), S:O(N), 1 ms
/*
1. n points = n islands = n trees = n roots.
2. With each edge added, check which island is e[0] or e[1] belonging to.
3. If e[0] and e[1] are in same islands, do nothing.
4. Otherwise, union two islands, and reduce islands count by 1.
5. Bonus: path compression can reduce time by 50%.
*/
class Solution{
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for(int i = 0; i < n; i++) roots[i] = i;

        for(int[] e : edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            if(root1 != root2) {
                roots[root1] = root2;  // union
                n--;
            }
        }
        return n;
    }

    public int find(int[] roots, int id) {
        while(roots[id] != id) {
            roots[id] = roots[roots[id]];  // optional: path compression
            id = roots[id];
        }
        return id;
    }
}

// BFS, T:O(N), S:O(N), 21 ms
class Solution {

    HashMap<Integer, List<Integer>> map;
    public int countComponents(int n, int[][] edges) {

        if(edges == null || edges.length == 0) return n;

        map = new HashMap();
        getGraph(edges);

        HashSet<Integer> visited = new HashSet();
        int ans = 0;

        for(int i = 0; i < n; i++){
            if(!visited.contains(i)){

                LinkedList<Integer> q = new LinkedList();
                q.add(i);

                while(q.size() > 0){
                    int current = q.poll();
                    visited.add(current);
                    List<Integer> neighbors = map.getOrDefault(current, new LinkedList<Integer>());

                    for(int j = 0; j< neighbors.size(); j++){
                        int neighbor = neighbors.get(j);
                        if(!visited.contains(neighbor)) q.add(neighbor);
                    }
                }

                ans++;
            }
        }

        return ans;
    }

    private void getGraph(int[][] edges){
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            List<Integer> listU = map.getOrDefault(u, new LinkedList<Integer>());
            listU.add(v);
            map.put(u, listU);

            List<Integer> listV =map.getOrDefault(v, new LinkedList<Integer>());
            listV.add(u);
            map.put(v, listV);
        }
    }
}