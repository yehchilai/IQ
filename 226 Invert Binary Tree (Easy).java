/*
This question is from https://leetcode.com/problems/invert-binary-tree/
Difficulty: easy

Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1

Time Complexity: O(N) 

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
    public TreeNode invertTree(TreeNode root) {
    	if(root == null) return null;

    	TreeNode tmp = root.left;
    	root.left = root.right;
    	root.right = tmp;

    	invertTree(root.left);
    	invertTree(root.right);

    	return root;

/*  ============== Wrong Answer ======================  */
    	// if(root == null) return null;

    	// if(root.left == null && root.right != null){
    	// 	root.left = root.right;
    	// 	root.left.val = root.right.val;
    	// 	root.right = null;
    	// }

    	// if(root.left != null && root.right == null){
    	// 	root.right = root.left;
    	// 	root.right.val = root.left.val;
    	// 	root.left = null;
    	// }

    	// if(root.left != null && root.right != null){
    	// 	int invertVal = root.left.val;
     //   		root.left.val = root.right.val;
     //   		root.right.val = invertVal;
     //   		root.left = invertTree(root.left);
     //   		root.right = invertTree(root.right);
    	// }

    	// return root;
    }
}