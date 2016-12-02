/*
This question is from https://leetcode.com/problems/binary-tree-inorder-traversal/
Difficulty: medium

Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

*/

// Recursion, T:O(N), M:O(N), 1ms

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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        traversal(root, result);
        return result;
    }

    public void traversal(TreeNode root, ArrayList<Integer> result){
        if(root.left != null) traversal(root.left, result);
        result.add(root.val);
        if(root.right != null) traversal(root.right, result);
    }
}

// Iteration, T:O(N), M:O(2N), 1ms

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
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;

        if(root == null) return result;

        while(current != null || !stack.empty()){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }


}
