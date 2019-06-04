/*
This question is from https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
Difficulty: medium

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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

// T:O(N), S:O(N), 1 ms
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int depth = 0;
        LinkedList<TreeNode> q = new LinkedList();
        q.add(root);
        List<List<Integer>> ans = new LinkedList();
        if(root == null) return ans;
        while(q.size() > 0){
            int len = q.size();
            // System.out.println(len);
            List<Integer> tmp = new LinkedList();

            for(int i = 0; i < len; i++){
                TreeNode node = q.poll();
                tmp.add(node.val);
                // System.out.print(node.val+",");
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
            if(depth % 2 != 0){
               Collections.reverse(tmp);
            }
            depth++;
            ans.add(tmp);
        }
        return ans;
    }
}