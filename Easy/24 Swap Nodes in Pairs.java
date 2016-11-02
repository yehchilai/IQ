/*
This question is from https://leetcode.com/problems/swap-nodes-in-pairs/
Difficulty: easy

Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.


*/
// No loop, no recursive, T:O(N), M:(1), 5ms
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode current = newHead;
        while(current.next !=null && current.next.next != null){
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            second.next = first;
            current.next = second;
            current = current.next.next;
        }

        return newHead.next;
    }
}
