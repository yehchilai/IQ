/*
This question is from https://leetcode.com/problems/remove-linked-list-elements/
Difficulty: easy

Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
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
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        ListNode current = head;
        while(current.next != null){
        	if(current.next.val == val){
        		current.next = current.next.next;
        		current = current.next.next;
        	}else{
        		current = current.next;
        	}
        }

        if(head.val == val){
        	return head.next;
        }else{
        	return head;
        }
    }
}