/*
This question is from https://leetcode.com/problems/linked-list-cycle/
Difficulty: easy

Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

*/

// two pointer, T:O(N), M:O(1), 2ms
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }

        return false;
    }
}
