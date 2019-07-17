/*
This question is from https://leetcode.com/problems/add-two-numbers-ii/
Difficulty: medium

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// T:O(N), S:O(N), 2 ms
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = reverse(l1);
        ListNode node2 = reverse(l2);

        int carrier = 0;
        ListNode head = new ListNode(0);
        ListNode node = head;

        while(node1 != null || node2 != null){
            if(node1 != null && node2 != null){
                int sum = carrier + node1.val + node2.val;
                carrier = sum / 10;
                node.next = new ListNode(sum % 10);
                node = node.next;
                node1 = node1.next;
                node2 = node2.next;
            }else if(node1 != null){
                int sum = carrier + node1.val;
                carrier = sum / 10;
                node.next = new ListNode(sum % 10);
                node = node.next;
                node1 = node1.next;
            }else if(node2 != null){
                int sum = carrier + node2.val;
                carrier = sum / 10;
                node.next = new ListNode(sum % 10);
                node = node.next;
                node2 = node2.next;
            }
        }

        if(carrier == 1) node.next = new ListNode(1);

        ListNode ans = reverse(head.next);

        return ans;
    }

    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode node = head;

        while(node != null){
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        return prev;
    }
}

// Stack
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }

        return list.val == 0 ? list.next : list;
    }
}