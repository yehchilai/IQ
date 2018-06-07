/*
This question is from https://leetcode.com/problems/equal-tree-partition/description/
Difficulty: medium

Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation:
    5
   /
  10

Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000
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
// T: O(N), S: O(N), 18ms
class Solution {
    public boolean checkEqualTree(TreeNode root) {
        // recore the sum of the subtree in a node
        Stack<Integer> sumOfSubtree = new Stack();
        // total
        int total = sum(root, sumOfSubtree);
        // pop the total
        sumOfSubtree.pop();

        // if total is not an even number, return false
        if(total%2 == 0){
            while(!sumOfSubtree.empty()){
                int sum = sumOfSubtree.pop();
                if( (total / 2) == sum) return true;
            }
        }

        return false;
    }

    private int sum(TreeNode root, Stack<Integer> stack){
        if(root == null) return 0;
        stack.push(root.val + sum(root.left, stack) + sum(root.right, stack));
        return stack.peek();
    }
}