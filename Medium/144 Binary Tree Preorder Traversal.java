/*
This question is from https://leetcode.com/problems/binary-tree-preorder-traversal/
Difficulty: medium

Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/
// recursive, 1ms
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
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<Integer>();

        traversal(root, result);

        return result;
    }

    public void traversal(TreeNode root, LinkedList<Integer> result){
        if(root == null) return;
        result.add(root.val);
        traversal(root.left, result);
        traversal(root.right, result);
    }
}

// iterative, 2ms
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
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;

        while(current != null || !stack.empty()){
            if(current != null){
                result.add(current.val);
                stack.push(current.right);
                current = current.left;
            }else{
                current = stack.pop();
            }
        }
        return result;
    }

}
