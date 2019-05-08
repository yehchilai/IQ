/*
This question is from https://leetcode.com/problems/minimum-absolute-difference-in-bst/
Difficulty: easy

Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).


Note: There are at least two nodes in this BST.
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
// T:O(N), S:O(N), 2 ms
class Solution {

    ArrayList<Integer> list;

    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;

        list = new ArrayList();

        inOrder(root);

        for(int i = 0 ; i < list.size() - 1; i++){
            min = Math.min(min, list.get(i+1) - list.get(i));
        }

        return min;
    }

    public void inOrder(TreeNode node){
        if(node == null) return;

        inOrder(node.left);
        list.add(node.val);
        inOrder(node.right);

    }
}