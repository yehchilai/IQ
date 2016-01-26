/*
This question is from https://leetcode.com/problems/maximum-depth-of-binary-tree/
Difficulty: Easy

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
public class Solution{

	int mMaxDepth = 0;

	public int maxDepth(TreeNode root){
		
		checkNode(root, 0);

		return mMaxDepth;
	}

	public void checkNode(TreeNode node, int depth){
		if(node != null){
			checkNode(node.left, depth + 1);
			checkNode(node.right, depth + 1);
		}else{
			if(depth > mMaxDepth) mMaxDepth = depth;
		}
	}
}