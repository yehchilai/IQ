/*
This question is from https://leetcode.com/problems/palindrome-linked-list/
Difficulty: easy

Given a singly linked list, determine if it is a palindrome.

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
// 7ms
public class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<Integer>();
        ListNode node = head;
        while(node != null){
        	stack.push(node.val);
        	node = node.next;
        }
        node = head;
        while(node!=null){
        	if(node.val != stack.pop()) return false;
        	node = node.next;
        }

        return true;
    }
}
// alternative (2ms) : https://leetcode.com/discuss/78152/java-easy-to-understand
public class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
        	fast = fast.next.next;
        	slow = slow.next;
        }
        if(fast != null) slow = slow.next; // even number list

        slow = reverse(slow);
        while(slow != null && head.val == slow.val){
        	slow = slow.next;
        	head = head.next;
        }
        return head.val == slow.val;
    }
    // 1 -> 2 -> 3 -> null
    // null <- 1
    public ListNode reverse(ListNode head){
    	ListNode pre = null;
    	while(head != null){
    		ListNode next = head.next;
    		head.next = pre;
    		pre = head;
    		head = next;
    	}
    	return pre;
    }
}