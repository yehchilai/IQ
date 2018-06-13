/*
This question is from https://leetcode.com/problems/maximum-width-of-binary-tree/description/
Difficulty: medium

Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input:

           1
         /   \
        3     2
       / \     \
      5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input:

          1
         /
        3
       / \
      5   3

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input:

          1
         / \
        3   2
       /
      5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
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
// T:O(N), S:O(N), 12ms
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        // edge case
        if(root == null) return 0;

        // node queue
        Deque<Node> q = new LinkedList();

        q.add(new Node(root, 0, 0));

        // result
        int max = 0;

        // go throwgh the q (level by level)
        while(q.size() > 0){
            int size = q.size();
            int leftMost = 0;
            int rightMost = 0;
            for(int i = 0 ; i < size; i ++){
                Node current = q.poll();
                if(i == 0) leftMost = current.position;
                if(i == (size - 1)) rightMost = current.position;
                if(current.node.left != null) q.add(new Node(current.node.left, current.depth + 1, current.position * 2));
                if(current.node.right != null) q.add(new Node(current.node.right, current.depth + 1, current.position * 2 + 1));
            }
            max = Math.max(max, (rightMost - leftMost + 1));
        }
        return max;
    }

    class Node{
        TreeNode node;
        int depth;
        int position;

        public Node(TreeNode n, int d, int p){
            this.node = n;
            this.depth = d;
            this.position = p;
        }
    }
}