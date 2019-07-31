/**
This question is from https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
Difficulty: medium

Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)



Example 1:



Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation:
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.


Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.
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

// T:O(N), S:O(1), 0 ms(100%)
class Solution {
	public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    public int dfs(TreeNode root, int mn, int mx) {
        if (root == null) return mx - mn;
        mx = Math.max(mx, root.val);
        mn = Math.min(mn, root.val);
        return Math.max(dfs(root.left, mn, mx), dfs(root.right, mn, mx));
    }
}

// T:O(N), S:O(1), 0 ms(100%)
class Solution {

    int result = 0;
    public int maxAncestorDiff(TreeNode root) {
        preorder(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return result;
    }

    private void preorder(TreeNode root, int max, int min){
        if(root == null) return;

        if(max != Integer.MIN_VALUE) result = Math.max(result, Math.abs(root.val - max));
        if(min != Integer.MAX_VALUE) result = Math.max(result, Math.abs(root.val - min));

        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        preorder(root.left, max, min);
        preorder(root.right, max, min);
    }
}