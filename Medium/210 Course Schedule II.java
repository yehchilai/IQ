/*
This question is from https://leetcode.com/problems/course-schedule-ii/
Difficulty: medium

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/
// T:O(N), S:O(N), 8 ms
class Solution {

    boolean cycle;

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        cycle = false;
        HashMap<Integer, List<Integer>> graph = new HashMap();

        for(int[] p : prerequisites){
            List<Integer> list = graph.getOrDefault(p[1], new LinkedList<Integer>());
            list.add(p[0]);
            graph.put(p[1], list);
        }

        LinkedList<Integer> ans = new LinkedList();
        int[] visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            dfs(i, graph, visited, ans);
        }

        if(cycle) return new int[0];

        int[] result = new int[ans.size()];
        for(int i=0; i < ans.size(); i++) result[i] = ans.get(ans.size() - 1 - i);
        return result;
    }

    private void dfs(int n, HashMap<Integer, List<Integer>> graph, int[] visited, LinkedList<Integer> ans){

        if(cycle) return;

        if(visited[n] == 2) return;

        visited[n] = 1;

        if(graph.containsKey(n)){
            List<Integer> list = graph.get(n);

            for(int i = 0; i < list.size(); i++){
                int neighbor = list.get(i);
                if(visited[neighbor] == 1){
                    cycle = true;
                    return;
                }
                dfs(neighbor, graph, visited, ans);
            }

        }
        visited[n] = 2;
        ans.add(n);
    }
}