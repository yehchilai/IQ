/**
This question is from https://leetcode.com/problems/binary-tree-maximum-path-sum/
Difficulty: hard

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

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

// T:O(N), S:O(h), 1 ms (99.98% )
class Solution {

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {

        traversal(root);
        return max;
    }

    public int traversal(TreeNode root){
        if(root == null) return 0;

        int left = Math.max(traversal(root.left), 0);
        int right = Math.max(traversal(root.right), 0);

        int current = root.val + left + right;

        max = Math.max(max, current);

        return root.val + Math.max(left, right);
    }
}