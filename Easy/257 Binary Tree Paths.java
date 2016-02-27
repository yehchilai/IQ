/*
This question is from https://leetcode.com/problems/binary-tree-paths/
Difficulty: easy

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

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
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if(root == null) return result;
        binaryTreePath(root, "", result);
        return result;
    }

    public void binaryTreePath(TreeNode root, String str, List<String> list){
    	if(root.left == null && root.right == null) list.add(str + Integer.toString(root.val));
    	if(root.left != null) binaryTreePath(root.left, str + Integer.toString(root.val) + "->", list);
    	if(root.right != null) binaryTreePath(root.right, str + Integer.toString(root.val) + "->", list);
    }
}

// Alternative : https://leetcode.com/discuss/83639/java-simple-recursive-solution
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> paths = new ArrayList<String>();
        if(root==null) return paths;
        if(root.left==null && root.right==null) {
            paths.add("" + root.val); 
            return paths;
        }
        if (root.left!=null) for (String st:binaryTreePaths(root.left)) paths.add(root.val + "->" + st);
        if (root.right!=null) for (String st:binaryTreePaths(root.right)) paths.add(root.val + "->" + st);
        return paths;
    }
}