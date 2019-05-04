/*
This question is from https://leetcode.com/problems/bus-routes/
Difficulty: hard

We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input:
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation:
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.
*/
// T:O(N*Nlog(N)), S:O(N^2), 51ms
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T) return 0;
        int buses = routes.length;

        List<List<Integer>> graph = new LinkedList();

        // initialize graph and sort each route
        for(int i = 0; i < buses; i++){
            Arrays.sort(routes[i]);
            graph.add(new ArrayList());
        }

        // setup graph
        for(int i = 0; i < buses; i++){
            for(int j = 0; j < buses; j++){
                if(isConnected(routes[i], routes[j])){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        // System.out.println("graph: "+ graph.size());
        Set<Integer> seen = new HashSet();
        Set<Integer> target = new HashSet();
        Queue<int[]> q = new LinkedList();

        // setup seen, target, and q
        for(int i = 0; i < buses; i++){
            if(Arrays.binarySearch(routes[i], S) >= 0){
                seen.add(i);
                q.add(new int[]{i, 0});
            }

            if(Arrays.binarySearch(routes[i], T) >= 0){
                target.add(i);
            }
        }

        // System.out.println("seen: "+ seen.size());
        // System.out.println("target: "+ target.size());
        // System.out.println("q: "+ q.size());
        // BFS
        while(q.size() > 0){
           // System.out.println( q.poll()[0]);
            int[] current = q.poll();
            int bus = current[0];
            int depth = current[1];
            if(target.contains(bus)) return depth + 1;
            for(Integer neighbor: graph.get(bus)){
                if(seen.contains(neighbor)) continue;
                seen.add(neighbor);
                q.add(new int[]{neighbor, depth + 1});
            }
        }

        return -1;
    }

    // a and b are sorted
    public boolean isConnected(int[] a, int[] b){
        int i = 0;
        int j = 0;

        while( i < a.length && j < b.length){
            if(a[i] == b[j]) return true;
            if(a[i] < b[j]){
                i++;
            }else{
                j++;
            }
        }

        return false;
    }
}