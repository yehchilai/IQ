/*
This question is from https://leetcode.com/problems/reverse-linked-list/

Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?

Time Complexity: O()
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// iteratively
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode last = head;
        ListNode current = head.next;
		head.next = null;

		while(current != null){
		    ListNode tmp = current.next; // do not modify tmp beacase it is not a reference.
		    current.next = last;
		    last = current;
		    current = tmp;
		}
		
		return last;
    }
}

// recursively
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null) return head;
        
        ListNode next = head.next;
        head.next = null;

        if(next != null){
        	ListNode root = reverseList(next);
        	next.next = head;
        	return root;
        }

        return head;
    }
}
