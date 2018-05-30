/*
This question is from https://leetcode.com/problems/add-two-numbers/description/
Difficulty: medium

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/



// This cannot be used when the sum is larget than Integer.MAX_VALUE
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        // unit
        int unit = 1;
        // sum
        int sum = 0;

        // node of l1
        ListNode node1 = l1;
        // node of l2
        ListNode node2 = l2;
        // go through 2 list
        while(node1 != null || node2 != null){
            int val1 = 0;
            int val2 = 0;
            if(node1 != null) val1 = node1.val;
            if(node2 != null) val2 = node2.val;
            // update sum
            sum = sum + (val1 + val2)* unit;
            // update unit
            unit = unit * 10;
            if(node1 != null) node1 = node1.next;
            if(node2 != null) node2 = node2.next;
        }
        System.out.println(sum);
        ListNode newNode = new ListNode(0);
        newNode.next = new ListNode(sum%10);
        sum = sum/10;
        ListNode currentNode = newNode.next;
        while(sum > 0){
            currentNode.next = new ListNode(sum%10);
            sum = sum /10;
            currentNode = currentNode.next;
        }

        return newNode.next;
    }
}

// better by using carry to store infromation.
// T: O(N), S: O(N), 56ms
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        // integer carry
        int carry = 0;

        // node of l1
        ListNode node1 = l1;
        // node of l2
        ListNode node2 = l2;
        // result list
        ListNode psuedoNode = new ListNode(0);
        ListNode node = psuedoNode;

        // go through 2 list
        while(node1 != null || node2 != null){
            int val1 = 0;
            int val2 = 0;
            if(node1 != null) val1 = node1.val;
            if(node2 != null) val2 = node2.val;
            // get sum of this two digits
            int sum = val1 + val2 + carry;
            // update carry
            carry = sum /10;
            // add this digit into the result list
            node.next = new ListNode(sum%10);
            node = node.next;

            if(node1 != null) node1 = node1.next;
            if(node2 != null) node2 = node2.next;
        }

        if(carry == 1){
            node.next = new ListNode(1);
        }

        return psuedoNode.next;
    }
}