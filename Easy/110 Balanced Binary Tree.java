/*
This question is from https://leetcode.com/problems/balanced-binary-tree/
Difficulty: easy

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of 
the two subtrees of every node never differ by more than 1.

[1,2,2,3,3,null,null,4,4]

		1
	2		2
  3   3 		
4   4
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
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(Math.abs(height(root.left) - height(root.right)) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    } 

    public int height(TreeNode node){
    	if(node == null) return 0;
    	return Math.max(height(node.left), height(node.right)) + 1;
    }
}

// 93%
// public class Solution {
//     public boolean isBalanced(TreeNode root) {
//         if(root == null) return true;
        
//         if(root.right == null && (root.left.right != null || root.left.left != null)){
//         	return false;
//         } else if(root.left == null && (root.right.right != null || root.right.left != null)){
//         	return false;
//         }

//         return isBalanced(root.left) && isBalanced(root.right);
//     } 

// }