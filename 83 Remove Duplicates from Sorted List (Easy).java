/*
This question is from https://leetcode.com/problems/remove-duplicates-from-sorted-list/
Difficulty: easy


Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.


Time Complexity: O(N)
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode current = head.next;
		ListNode last = head;
        while(current != null){
        	if(current.val == last.val){
        		last.next = current.next;
            	current = current.next;
        	}else{
        		last = last.next;
        		current = last.next;
        	}
        }
        return head;
    }
}