/*
This question is from https://leetcode.com/problems/longest-univalue-path/
Difficulty: easy

Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.



Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2



Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2



Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.

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

// T:O(N), S:O(H), H: height of tree, 4ms
class Solution {

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        postOrder(root);
        return max;
    }

    public int postOrder(TreeNode node){

        if(node == null) return 0;

        int left = postOrder(node.left);
        int right = postOrder(node.right);

        int leftBranch = 0;
        int rightBranch = 0;
        if(node.left != null && node.left.val == node.val){
            leftBranch = 1 + left;
        }

        if(node.right != null && node.right.val == node.val){
            rightBranch = 1 + right;
        }

        max = Math.max(max, leftBranch + rightBranch);
        // return leftBranch + rightBranch;
        return Math.max(leftBranch, rightBranch);
    }
}