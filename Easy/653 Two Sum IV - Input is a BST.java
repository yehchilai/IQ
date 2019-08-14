/**
This question is from https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
Difficulty: easy

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True


Example 2:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False

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

// use BST to create a sorted array, T:O(N), S:O(N), 2ms (95.13%)
public class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List < Integer > list = new ArrayList();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k)
                return true;
            if (sum < k)
                l++;
            else
                r--;
        }
        return false;
    }
    public void inorder(TreeNode root, List < Integer > list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}

// T:O(N), S:O(N), 4 ms (70.84%)
class Solution {

    HashSet<Integer> set = new HashSet();

    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;

        if(set.contains(k - root.val)) return true;

        set.add(root.val);

        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}