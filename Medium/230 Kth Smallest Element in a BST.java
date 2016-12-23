/*
This question is from https://leetcode.com/problems/kth-smallest-element-in-a-bst/
Difficulty: medium

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Show Hint (see below)

*/

// recursive, T:O(N), M:O(1), 3ms
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
    int current = 0;
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        traversal(root, k);
        return result;
    }

    public void traversal(TreeNode root, int k){
        if(root == null) return;
        traversal(root.left, k);
        current++;
        if(k == current) result = root.val;
        traversal(root.right, k);
    }
}

/*
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

1. Try to utilize the property of a BST.
2. What if you could modify the BST node's structure?
3. The optimal runtime complexity is O(height of BST).

Solution:
O(h) (h = height) time complexity by modify TreeNode structure and add left subtree node count and find kth smallest element
http://www.geeksforgeeks.org/find-k-th-smallest-element-in-bst-order-statistics-in-bst/

*/
