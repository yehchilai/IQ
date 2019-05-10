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

    ArrayList<String> list = new ArrayList();

    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return list;
        preOrder(root, "");
        return list;
    }

    public void preOrder(TreeNode node, String s){
        if(node.left == null && node.right == null)
            list.add(s+Integer.toString(node.val));
        if(node.left != null)
            preOrder(node.left, s+Integer.toString(node.val)+"->");
        if(node.right != null)
            preOrder(node.right, s+Integer.toString(node.val)+"->");

    }
}

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