/*
This Question is from https://leetcode.com/problems/minimum-depth-of-binary-tree/
Difficulty: easy

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.


Time Complexity: O()
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
public class Solution {
	
    public int minDepth(TreeNode root) {
        
        if(root == null) return 0;
        
        return Math.min(depth(root.left, 1), depth(root.right, 1));
    }

    public int depth(TreeNode node, int depth){
    	if(node == null) return depth;
    	
        return Math.min(depth(node.left, depth + 1), depth(node.right, depth + 1));
    }
}