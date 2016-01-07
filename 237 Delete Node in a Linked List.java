/*
This question is from https://leetcode.com/problems/delete-node-in-a-linked-list/
Difficulty: easy

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 

the linked list should become 1 -> 2 -> 4 after calling your function.

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
    public void deleteNode(ListNode node) {
        if(node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}

/*  Wrong answer .... */
// public class Solution {
//     public void deleteNode(ListNode node) {
//         if(node == null) return;
//         if(node.next == null) return;
//         if(node.next.next == null){
//         	node.val = node.next.val;
//         	node.next= node.next.next;
//         	return;
//         }

//         ListNode lastNode = null;
//        	ListNode currentNode = node;
//     	ListNode nextNode = node.next;
//     	while(nextNode.next != null ){
//     		lastNode = currentNode;
//     		currentNode = currentNode.next;
//     		nextNode = currentNode.next;
//     	}
//     	lastNode.next = nextNode;
//     }
// }