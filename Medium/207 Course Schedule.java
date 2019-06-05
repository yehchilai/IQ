/*
This question is from https://leetcode.com/problems/course-schedule/
Difficulty: medium

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

*/
// (Optional) TopographicalSort https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
// https://leetcode.com/problems/course-schedule/discuss/289732/Very-easy-to-read-thoroughly-explained-solution-(BFS-and-DFS)

// DFS, T:O(edges), S:O(edges), 164 ms
class Solution {

    HashMap<Integer, List<Integer>> graph;
    boolean[] visited;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        graph = new HashMap();

        for(int i = 0; i < prerequisites.length; i++){
            int u = prerequisites[i][1];
            int v = prerequisites[i][0];
            List<Integer> list = graph.getOrDefault(u, new LinkedList<Integer>());
            list.add(v);
            graph.put(u, list);
        }

        boolean cycle = false;
        for(int i = 0; i < numCourses; i++){
            visited = new boolean[numCourses];
            if(isCycle(i)){
                // System.out.println("cycle: "+i);
                cycle = true;
                break;
            }
        }

        return !cycle;
    }

    private boolean isCycle(int node){
        if(visited[node]) return true;

        List<Integer> neighbors = graph.getOrDefault(node, new LinkedList());

        visited[node] = true;

        for(int i = 0; i < neighbors.size(); i++){
            int next = neighbors.get(i);
            if(isCycle(next)) return true;
        }
        visited[node] = false;

        return false;
    }
}