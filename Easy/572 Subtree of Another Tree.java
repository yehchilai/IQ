/*
This question is from https://leetcode.com/problems/subtree-of-another-tree/
Difficulty: easy

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
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
// T:O(N^2), S:O(N), 7 ms
class Solution {

    LinkedList<TreeNode> list;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        list = new LinkedList();
        findRoot(s, t.val);

        for(TreeNode node: list){
            if(compare(node, t, true)) return true;
        }
        return false;
    }

    private void findRoot(TreeNode node, int target){
        if(node == null) return;

        if(node.val == target) list.add(node);

        findRoot(node.left, target);
        findRoot(node.right, target);

    }

    private boolean compare(TreeNode a, TreeNode b, boolean same){
        if((a == null && b != null) || (b == null && a != null)) return false;

        if(a == null && b == null) return true;
        if(a.val != b.val) return false;

        boolean left = compare(a.left, b.left, same);
        boolean right = compare(a.right, b.right, same);

        if(left == false || right == false) same = false;
        return same;
    }
}