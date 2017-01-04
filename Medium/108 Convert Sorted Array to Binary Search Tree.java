/*
This question is from https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
Difficulty: medium

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/
//
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// T:O(N), M:O(N), 1ms
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        TreeNode root = insertNode(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode insertNode(int[] nums, int left, int right){
        if(left > right) return null;
        int mid = (right + left)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = insertNode(nums, left, mid - 1);
        root.right = insertNode(nums, mid + 1, right);
        return root;
    }
}
