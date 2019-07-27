/**
This question is from https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
Difficulty: medium

Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

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
// T:O(N), S:O(1), 0 ms (100%)
class Solution {

    TreeNode prev;

    public void flatten(TreeNode root) {
        if(root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;

    }

}

// Memory Limit Exceeded
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;

        List<TreeNode> list = new LinkedList();
        preOrder(root, list);
        TreeNode head = root;
        head.left = null;
        for(TreeNode node: list){
            node.left = null;
            root.right = node;
            root = root.right;
        }
        root = head;
    }

    private void preOrder(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

}