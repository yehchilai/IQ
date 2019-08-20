/**
This question is from https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
Difficulty: medium

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

*/


 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
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

// T:O(N), S:O(logN), 1 ms (100.00%)
/*
Generate other possible solution
       0
     / \
   -10  5
     \   \
     -3   9

*/
class Solution {

    ListNode head;

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        this.head = head;

        return inorder(0, findSize(head) - 1);
    }

    private TreeNode inorder(int lo, int hi){
        if(lo > hi) return null;

        int mid = (lo + hi) /2;

        // go to the smallest value
        TreeNode left = inorder(lo, mid - 1);

        TreeNode node = new TreeNode(this.head.val);
        node.left = left;
        // update head
        this.head = this.head.next;

        node.right = inorder(mid + 1, hi);

        return node;
    }

    private int findSize(ListNode node){
        int count = 0;
        ListNode current = node;

        while(current != null){
            count++;
            current = current.next;
        }

        return count;
    }
}

// T:O(NlogN), S:O(logN), 1 ms (100.00%)
class Solution {
    public TreeNode sortedListToBST(ListNode head) {

        if(head == null) return null;

        ListNode mid = findMiddle(head);

        TreeNode root = new TreeNode(mid.val);

        if(mid == head) return root;

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }

    private ListNode findMiddle(ListNode node){
        ListNode prev = null;
        ListNode slow = node;
        ListNode fast = node;

        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if(prev != null){
            prev.next = null;
        }

        return slow;
    }
}