/*
This question is from https://leetcode.com/problems/cousins-in-binary-tree/
Difficulty: easy

In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.



Example 1:


Input: root = [1,2,3,4], x = 4, y = 3
Output: false
Example 2:


Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
Example 3:



Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false


Note:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
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

// T:(N), S:O(1), 0 ms
class Solution {

    int[] targetX;
    int[] targetY;

    public boolean isCousins(TreeNode root, int x, int y) {
        targetX = new int[]{-1,-1};
        targetY = new int[]{-1,-1};

        find(root, x, y, 0);

        if(targetX[0] == -1 || targetY[0] == -1) return false;

        if(targetX[1] == targetY[1] && targetX[0] != targetY[0]) return true;
        return false;
    }

    private void find(TreeNode node, int x, int y, int depth){
        if(node == null) return;

        if(node.left != null){

            if(node.left.val == x){
                targetX[0] = node.val;
                targetX[1] = depth + 1;
            }else if(node.left.val == y){
                targetY[0] = node.val;
                targetY[1] = depth + 1;
            }
        }

         if(node.right != null){

            if(node.right.val == x){
                targetX[0] = node.val;
                targetX[1] = depth + 1;
            }else if(node.right.val == y){
                targetY[0] = node.val;
                targetY[1] = depth + 1;
            }
        }

        find(node.left, x, y, depth + 1);
        find(node.right, x, y, depth + 1);
    }
}