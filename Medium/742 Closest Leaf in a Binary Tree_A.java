/*
This question is from https://leetcode.com/problems/closest-leaf-in-a-binary-tree/description/
Difficulty: medium

Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// DFS and BFS, T:O(N), S:O(N), 41ms
class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        // DFS to generate the node graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap();
        dfs(graph, root, null);

        // queue to traverse the possible nodes
        Deque<TreeNode> q = new LinkedList();
        // visited nodes
        Set<TreeNode> visited = new HashSet();

        // find the list of node k
        for(TreeNode node: graph.keySet()){
            if(node != null && node.val == k){
                q.add(node);
                visited.add(node);
            }
        }

        // go through all nodes in the queue
        while(q.size() > 0){
            TreeNode node = q.poll();
            if(node != null){
                if(graph.get(node).size() <= 1){
                    return node.val;
                }
                // append the neighborhood nodes if they have not been visited.
                for(TreeNode n: graph.get(node)){
                    if(!visited.contains(n)){
                        q.add(n);
                        visited.add(n);
                    }
                }
            }
        }
        return 0;

    }

    private void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent){
        if(node != null){
            if(!graph.containsKey(node)) graph.put(node, new LinkedList());
            if(!graph.containsKey(parent)) graph.put(parent, new LinkedList());

            graph.get(node).add(parent);
            graph.get(parent).add(node);

            dfs(graph, node.left, node);
            dfs(graph, node.right, node);
        }
    }
}