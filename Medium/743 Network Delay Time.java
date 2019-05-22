/*
This question is from https://leetcode.com/problems/network-delay-time/
Difficulty: medium

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
*/
// T:O(N), S:O(N), 30 ms
class Solution {

    boolean[] visited;
    int max;

    public int networkDelayTime(int[][] times, int N, int K) {
        visited = new boolean[N+1];
        HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap();

        for(int i = 0; i< times.length; i++){
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];

            HashMap<Integer, Integer> neighbors = graph.getOrDefault(u, new HashMap<Integer, Integer>());
            neighbors.put(v, w);
            graph.put(u, neighbors);
        }

        HashSet<Integer> visited = new HashSet();
        PriorityQueue<int[]> pq = new PriorityQueue(new ArrayComparator());
        pq.add(new int[]{K, 0});
        int max = 0;
        while(pq.size() > 0){
            int[] current = pq.poll();
            int node = current[0];
            int distance = current[1];
            // skip if visited this node
            if(visited.contains(node)) continue;
            visited.add(node);
            max = distance;
            // stop if cannot go to the next node
            if(graph.get(node) == null) continue;
            for(Map.Entry<Integer, Integer> entry: graph.get(node).entrySet()){
                int neighbor = entry.getKey();
                int dis = entry.getValue();

                if(!visited.contains(neighbor)) pq.add(new int[]{neighbor, distance + dis});
            }
        }

        if(visited.size() != N) return -1;
        return max;
    }
}

class ArrayComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] a, int[] b){
        return a[1] - b[1];
    }
}