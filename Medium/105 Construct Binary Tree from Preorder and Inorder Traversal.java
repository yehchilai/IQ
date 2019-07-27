/*
This question is from https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
Difficulty: medium

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
// T:O(N), S:O(N), 2 ms (96.74%)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0) return null;

        Stack<TreeNode> stack = new Stack();

        TreeNode root = new TreeNode(preorder[0]);
        TreeNode node = root;
        int j = 0;
        for(int i = 1; i < preorder.length; i++){
            if(node.val != inorder[j]){
                node.left = new TreeNode(preorder[i]);
                stack.push(node);
                node = node.left;
            }else{
                j++;
                while(!stack.empty() && stack.peek().val == inorder[j]){
                    node = stack.pop();
                    j++;
                }

                node.right = new TreeNode(preorder[i]);
                node = node.right;
            }
        }

        return root;
    }
}

 // wrong answer when preorder=[1,2], inorder=[1,2]
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder == null || preorder.length == 0) return null;

        TreeNode node = new TreeNode(preorder[0]);
        HashMap<Integer, TreeNode> map = new HashMap();
        map.put(preorder[0], node);
        int index = 0;
        for(int i = 1; i < preorder.length; i++){
            if(preorder[i] != inorder[index]){
                if(node.left == null){
                    node.left = new TreeNode(preorder[i]);
                    node = node.left;
                }else{
                    node.right = new TreeNode(preorder[i]);
                    node = node.right;
                }
                map.put(preorder[i], node);

            }else{
                if(node.left == null){
                    node.left = new TreeNode(preorder[i]);
                    node = node.left;
                }else{
                    node.right = new TreeNode(preorder[i]);
                    node = node.right;
                }

                // System.out.println("   insert: "+preorder[i]+ " on the left of "+node.val);
                map.put(preorder[i], node);

                for(int j = index; j < inorder.length; j++){
                    if(!map.containsKey(inorder[j])) break;
                    index = j;
                    // System.out.print(index+"("+inorder[j]+")"+", ");
                    node = map.get(inorder[j]);
                }
                // System.out.println();
                // System.out.println("  "+index+": "+node.val);
                index++;
            }
            // System.out.println("current: "+node.val);
        }

        return map.get(preorder[0]);
    }

}