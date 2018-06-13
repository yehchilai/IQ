/*
This question is from https://leetcode.com/problems/construct-string-from-binary-tree/description/
Difficulty: easy

You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /
  4

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())",
but you need to omit all the unnecessary empty parenthesis pairs.
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \
      4

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example,
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

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

// T:O(N), S:O(N), 15ms
class Solution {
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        preorder(t, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node,  StringBuilder sb){
        if(node == null) return;
        sb.append(String.valueOf(node.val));
        if(node.left == null && node.right != null){
            sb.append("()");
        }else if(node.left != null){
            sb.append("(");
            preorder(node.left, sb);
            sb.append(")");
        }
        if(node.right != null){
            sb.append("(");
            preorder(node.right, sb);
            sb.append(")");
        }

    }

}