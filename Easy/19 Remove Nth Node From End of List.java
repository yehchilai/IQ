/*
This question is from https://leetcode.com/problems/remove-nth-node-from-end-of-list/
Difficulty: easy

Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.


Time complexity: O()
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode a = result;
        ListNode b = result;
        while(a.next != null){
        	a = a.next;
        	n--;
        	if(n < 0) b = b.next;
        }
    	b.next = b.next.next;
    	return result.next;
    }
}