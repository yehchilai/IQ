/**
This question is from https://leetcode.com/problems/graph-valid-tree/
Difficulty: medium

Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.

test cases:
4
[[0,1],[2,3]]
1
[]
4
[[2,3],[1,2],[1,3]]
5
[[0,1],[0,2],[0,3],[1,4]]
5
[[0,1], [1,2], [2,3], [1,3], [1,4]]
*/

// Union methond
// T:O(N), S:O(N), 1 ms (74.58%)
class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] connect = new int[n];
        Arrays.fill(connect, -1);

        // perform union find
        for(int[] edge: edges){
            int u = find(connect, edge[0]);
            int v = find(connect, edge[1]);

            // if two vertices happen to be in the same set
            // then there's a cycle
            // there is a cycle
            if(u == v) return false;

            // union
            connect[u] = v;
        }

        return edges.length == n - 1;
    }

    private int find(int[] connect, int vertex){
        if(connect[vertex] == -1) return vertex;
        return find(connect, connect[vertex]);
    }
}

// T:O(N or edges), S:O(N), 28 ms (5.18%)
class Solution {

    List<List<Integer>> graph;
    public boolean validTree(int n, int[][] edges) {

        if(edges == null) return true;

        graph = new LinkedList();

        for(int i = 0; i < n; i++){
            graph.add(new LinkedList());
        }

        for(int[] edge: edges){

            int nodeA = edge[0];
            int nodeB = edge[1];
            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }

        boolean[] visited = new boolean[n];
        if(isCycle(0, -1, visited)){
            // System.out.println("There is a cycle");
            return false;
        }

//         // check all node is visited;
        boolean isVisited = true;
        // System.out.println(Arrays.toString(visited));
        // System.out.println("Keys: ");
        for(boolean b : visited){
            // System.out.println(key);
            if(b == false) isVisited = false;
        }

        return isVisited;
    }

    private boolean isCycle(int current, int parent, boolean[] visited){

        visited[current] = true;

        List<Integer> neighbors = graph.get(current);

        for(int neighbor : neighbors){

            // if(neighbor != parent){
            //     if(visited[neighbor]) return true;
            //     return isCycle(neighbor, current, visited);
            // }
            if((visited[neighbor] && neighbor != parent) ||
               (!visited[neighbor] && isCycle(neighbor, current, visited))) return true;
        }

        return false;
    }
}
