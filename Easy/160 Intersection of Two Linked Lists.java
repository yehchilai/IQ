/*
This question is from https://leetcode.com/problems/intersection-of-two-linked-lists/
Difficulty: easy

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.


Time Complexity: O()
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        while(a != b ){
        	if(a == null){
        		a = headB;
        	}else{
        		a = a.next;
        	}

        	if(b == null){
        		b = headA;
        	}else{
        		b = b.next;
        	}
        }

        return a;
    }
}

// Another solution from https://leetcode.com/discuss/70708/java-time-space-solution-by-using-assume-there-are-no-cycles
public class Solution{
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    // find last node of list A (c3)
    ListNode endA = headA;
    while (endA.next != null) {
        endA = endA.next;
    }
    // join c3 to b1 making a c1...c3-b1...b3-c1 loop (if b3 indeed points to c1)
    endA.next = headB;

    ListNode start = null; // if there's no cycle this will stay null
    // Floyd's cycle finder
    ListNode slow = headA, fast = headA;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) { // found a cycle
            // reset to beginning to find cycle start point (c1)
            start = headA;
            while (slow != start) {
                slow = slow.next;
                start = start.next;
            }
            break;
        }
    }
    // unjoin c3-b1
    endA.next = null;
    return start;
	}
}