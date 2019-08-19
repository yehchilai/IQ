/**
This question is from https://leetcode.com/problems/largest-bst-subtree/
Difficulty: medium

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

   10
   / \
  5  15
 / \   \
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
  5
 / \
1   8
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
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

// T:O(N), S:O(h), 1 ms (87.73%)
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        /*
        ans[0] : min
        ans[1] : max
        ans[2] : subtree nodes including self
        */
        long[] ans= traversal(root);

        return (int)ans[2];

    }

    private long[] traversal(TreeNode root){
        if(root == null) return new long[]{Long.MAX_VALUE, Long.MIN_VALUE, 0};

        long[] left = traversal(root.left);
        long[] right = traversal(root.right);

        long leftMin = left[0];
        long leftMax = left[1];
        long rightMin = right[0];
        long rightMax = right[1];

        if(root.val > leftMax && root.val < rightMin){
            return new long[]{Math.min(root.val, leftMin), Math.max(root.val, rightMax),
                              left[2] + right[2] + 1};
        }else{
            return new long[]{Long.MIN_VALUE, Long.MAX_VALUE,
            	Math.max(left[2], right[2])};
        }

    }
}