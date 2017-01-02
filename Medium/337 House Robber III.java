/*
This question is from https://leetcode.com/problems/house-robber-iii/
Difficulty: medium

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem
*/

// recursive, T:O(N), T:O(N), 1146ms
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
    public int rob(TreeNode root) {
        if(root == null) return 0;

        int result = 0;
        if(root.left != null){
            result = result + rob(root.left.left) + rob(root.left.right);
        }

        if(root.right != null){
            result = result + rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(result + root.val, rob(root.right) + rob(root.left));
    }
}

// DFS, T:O(N), M:O(1), 1ms
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
    public int rob(TreeNode root) {
        int[] result = dfs(root);
        return Math.max(result[0], result[1]);
    }

    public int[] dfs(TreeNode root){
        if(root == null) return new int[2];

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] result = new int[2];

        // without root
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // with root
        result[1] = root.val + left[0] + right[0];

        return result;
    }
}
