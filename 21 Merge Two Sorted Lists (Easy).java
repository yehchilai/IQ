/*
This question is from https://leetcode.com/problems/merge-two-sorted-lists/

Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.

Time Complexity: O(N+M) , the length of two lists
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode mergeNode = l1.val < l2.val? l1:l2;
        ListNode mergeHead = mergeNode;
        ListNode currentNodeA = l1.val < l2.val? l1.next:l2.next;
        ListNode currentNodeB = l1.val < l2.val? l2:l1;

        while(currentNodeA != null || currentNodeB != null){
        	if(currentNodeA == null){
        		mergeNode.next = currentNodeB;
        		mergeNode = mergeNode.next;
        		currentNodeB = currentNodeB.next;
        		continue;
        	}

        	if(currentNodeB == null){
        		mergeNode.next = currentNodeA;
        		mergeNode = mergeNode.next;
        		currentNodeA = currentNodeA.next;
        		continue;
        	}

        	if(currentNodeA.val <= currentNodeB.val){
        		mergeNode.next = currentNodeA;
        		mergeNode = mergeNode.next;
        		currentNodeA = currentNodeA.next;
        	}else{
        		mergeNode.next = currentNodeB;
        		mergeNode = mergeNode.next;
        		currentNodeB = currentNodeB.next;
        		continue;
        	}
        }
        return mergeHead;
    }
}

// Answer II : recursive from https://leetcode.com/discuss/17230/recursive-way-to-solve-this-problem-java-easy-understanding
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        ListNode mergeHead;
        if(l1.val < l2.val){
            mergeHead = l1;
            mergeHead.next = mergeTwoLists(l1.next, l2);
        }
        else{
            mergeHead = l2;
            mergeHead.next = mergeTwoLists(l1, l2.next);
        }
        return mergeHead;
    }
}