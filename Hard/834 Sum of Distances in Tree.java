/*
This question is from https://leetcode.com/problems/sum-of-distances-in-tree/
Difficulty: hard

An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation:
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000

*/

// T:O(N), S:O(N), 108ms
class Solution {

    ArrayList<HashSet<Integer>> treeList;
    int[] distances;
    int[] subtreeCount;
    int totalNodes;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {

        totalNodes = N;

        treeList = new ArrayList();
        distances = new int[N];
        subtreeCount = new int[N];

        Arrays.fill(subtreeCount, 1);

        for(int i = 0; i < N; i++){
            treeList.add(new HashSet<Integer>());
        }

        for(int[] edge: edges){
            treeList.get(edge[0]).add(edge[1]);
            treeList.get(edge[1]).add(edge[0]);
        }

        postOrder(0, -1);
        for(int n: subtreeCount) System.out.print(n+ ", ");
        preOrder(0, -1);

        return distances;
    }

    public void postOrder(int node, int parent){
        for(int child: treeList.get(node)){
            if(child != parent){
                postOrder(child, node);
                subtreeCount[node] += subtreeCount[child];
                distances[node] += distances[child] + subtreeCount[child];
            }
        }
    }

    public void preOrder(int node, int parent){
        for(int child: treeList.get(node)){
            if(child != parent){
                distances[child] = distances[node] - subtreeCount[child] + (totalNodes - subtreeCount[child]);
                preOrder(child, node);
            }
        }
    }

}