/*
This question is from https://leetcode.com/problems/validate-binary-search-tree/description/
Difficulty: medium

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.

*/
// T: O(N), S: O(1), 1ms
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return traversal(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    public boolean traversal(TreeNode root, long max, long min){
        // leave node
        if(root == null) return true;
        if(root.left != null && (root.left.val <= min || root.left.val >= root.val)) return false;
        if(root.right != null && (root.right.val >= max || root.right.val <= root.val)) return false;

        boolean left = traversal(root.left, root.val, min);
        boolean right = traversal(root.right, max, root.val);

        return (left && right);
    }
}