/**
This question is from https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
Difficulty: medium

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
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

// T:O(N), S:O(N), 2 ms (92.99%)
// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/discuss/34807/Java-iterative-solution-with-explanation
class Solution{
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //boundary case
        if(inorder.length == 0) return null;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        stack.push(root);

        int i = postorder.length-2, j = inorder.length-1;//i is index in postorder[], j is index in inorder[]

        while(i >= 0){
            TreeNode curr = stack.peek();
            if(curr.val != inorder[j]){
                //as long as we have not reach the rightmost node we can safely follow right path and attach right child
                TreeNode right = new TreeNode(postorder[i]);
                curr.right = right;
                stack.push(right);
                i--;
            }else{
                //found the node from stack where we have not visited its left subtree
                while(!stack.isEmpty() && stack.peek().val == inorder[j]){
                    curr = stack.pop();
                    j--;
                }

                TreeNode left = new TreeNode(postorder[i]);
                curr.left = left;
                stack.push(left);
                i--;
            }
        }

        return root;
    }
}

// T:O(N), S:O(N), 3 ms (56.53%)
class Solution {

    // val : index for inorder
    HashMap<Integer, Integer> map;
    int[] inorder;
    int[] postorder;
    int indexPost;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.map = new HashMap();
        this.inorder = inorder;
        this.postorder = postorder;
        this.indexPost = postorder.length - 1;

        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        return build(0, postorder.length - 1);
    }

    private TreeNode build(int left, int right){
        // System.out.println(indexPost+": "+left+", "+right);
        if(left < 0 || right >= postorder.length || left > right) return null;

        TreeNode root = new TreeNode(postorder[indexPost]);
        int index = map.get(postorder[indexPost]);
        indexPost--;
        root.right = build(index + 1, right);
        root.left = build(left, index - 1);

        return root;
    }
}