/*
This question is from https://leetcode.com/problems/reverse-nodes-in-k-group/
Difficulty: hard

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// T:O(N), S:O(1), 0 ms (100%)
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) return head;

        ListNode newHead = new ListNode(-1);
        ListNode start = newHead;
        newHead.next = head;
        int count = 0;

        while(head != null){
            count++;
            if(count % k == 0){
                // System.out.println("run reverse: "+start.val+", "+head.next.val);
                start = reverse(start, head.next);
                head = start.next;
            }else{
                head = head.next;
            }
        }

        return newHead.next;
    }

    private ListNode reverse(ListNode start, ListNode end){

        ListNode prev = start;
        ListNode next;
        ListNode node = start.next;
        ListNode first = start.next;
        while(node != end){
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        start.next = prev;
        first.next = node;
        // ListNode test = start;
        // while(test != null){
        //     System.out.print(test.val+", ");
        //     test = test.next;
        // }
        // System.out.println();
        return first;
    }
}
// T:O(N), S:O(1), Memory Limit Exceeded
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = k;
        ListNode start = head;
        ListNode node = head;
        ListNode newHead = null;

        // get the new head
        while(count > 0 && node != null){
            if(count == 1){
                newHead = node;
            }
            count--;
            node = node.next;
            System.out.println("find new Head: " + node.val);
        }

        count = k;
        node = head;

        if(newHead == null) return head;

        while(node != null){
            if(count > 0){
                count--;
            }else{
                ListNode tail = reverse(start, k);
                System.out.println("reverse tail: " + tail.val);
                tail.next = node;
                start = node;
                count = k;
            }
            node = node.next;
        }

        return newHead;
    }

    private ListNode reverse(ListNode head, int k){
        ListNode node = head;
        ListNode prev = null;
        ListNode next = node.next;

        while(k > 0){
            node.next = prev;
            node = next;
            prev = node;
            k--;
        }

        return head;
    }
}