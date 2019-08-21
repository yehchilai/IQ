/**
This question is from https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
Difficulty: medium

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:

   2
    \
     3
    /
   2
  /
 1

Output: 2

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
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

// T:O(N), S:O(h), 1 ms (100.00%)
class Solution {

    int max = 0;

    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        findDfs(root, 0, root.val);
        return max;
    }

    private void findDfs(TreeNode root, int length, int next){
        if(root == null) return;

        if(root.val == next){
            length++;
        }else{
            length = 1;
        }

        max = Math.max(length, max);

        findDfs(root.left, length, root.val + 1);
        findDfs(root.right, length, root.val + 1);
    }
}