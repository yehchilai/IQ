/*
This question is from https://leetcode.com/problems/same-tree/
Difficulty: easy

Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

Time Complexity: 
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	if(p == null && q == null) return true;
    	if(p == null || q == null) return false;

    	if(p.val == q.val){
			boolean left = isSameTree(p.left, q.left);
			boolean right = isSameTree(p.right, q.right);
			return left && right;
    	}  
    	return false;    
    }
}