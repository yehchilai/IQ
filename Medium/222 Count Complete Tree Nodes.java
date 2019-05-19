/*
This question is from https://leetcode.com/problems/count-complete-tree-nodes/
Difficulty: medium

Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
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

// T:O(logN), S::O(1), 0 ms
class Solution {
    int count;

    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        int leftHeight = 0;
        int rightHeight = 0;
        TreeNode left = root;
        TreeNode right = root;

        while(left != null) {
            left = left.left;
            leftHeight++;
        }

        while(right != null){
            right = right.right;
            rightHeight++;
        }

        if(leftHeight == rightHeight) return (int)Math.pow(2, leftHeight) - 1;

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

}

// T:O(N), S:O(1), 0 ms
class Solution {
    int count;

    public int countNodes(TreeNode root) {
        count = 0;
        preOrder(root);
        return count;
    }

    private void preOrder(TreeNode node){
        if(node == null) return;

        count++;
        preOrder(node.left);
        preOrder(node.right);
    }
}