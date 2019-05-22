/*
This question is from https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/
Difficulty: medium

Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.

The following example may help you understand the problem better:





In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list.





The new node should insert between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.


*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/

// T:O(N), S:O(1), 14 ms
class Solution {
    public Node insert(Node head, int insertVal) {
        Node node = head;
        Node insertNode = new Node(insertVal, null);
        if(head == null) return insertNode;
        if(head.next == null){
            head.next = insertNode;
            insertNode.next = head;
            return head;
        }
        int max = node.val;
        while(true){

        	// record max value
            if(node.val < node.next.val) max = node.next.val;
            // System.out.println(node.val);

            // check if the insert value is larger or samller than all values in the list.
            if(node.val > node.next.val && (insertVal >= max || insertVal <= node.next.val)){
                insertNode.next = node.next;
                node.next = insertNode;
                break;
            }

            // check the insert position
            if(node.val <= insertVal && insertVal <= node.next.val){
                insertNode.next = node.next;
                node.next = insertNode;
                break;
            }
            node = node.next;

            // check if loop
            if(node.next == head){
                insertNode.next = node.next;
                node.next = insertNode;
                break;
            }
        }
        return head;
    }
}