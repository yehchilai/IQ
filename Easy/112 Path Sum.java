/*
The question is from https://leetcode.com/problems/path-sum/
Difficulty: easy

Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

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
// The answer refers to https://leetcode.com/discuss/70843/4-line-recursive-in-java
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
  		if(root == null) return false;

  		if(root.right == null && root.left == null && root.val == sum)	return true;
  		
  		return hasPathSum(root.right, sum - root.val) || hasPathSum(root.left, sum - root.val);
    }
}