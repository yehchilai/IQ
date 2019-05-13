/*
This question is from https://leetcode.com/problems/find-duplicate-subtrees/
Difficulty: medium

Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.
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
// T:O(N), S:O(N), 13 ms
class Solution {

    HashMap<String, Integer> map;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap();
        ans = new ArrayList();
        preOrder(root);
        return ans;
    }

    private String preOrder(TreeNode node){
        if(node == null) return "#";

        String serial = node.val + "," + preOrder(node.left) + "," + preOrder(node.right);
        map.put(serial, map.getOrDefault(serial, 0) + 1);

        if(map.get(serial) == 2) ans.add(node);

        return serial;
    }
}