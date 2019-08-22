/**
This question is from https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
DIfficulty: medium

You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.



Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL


Explanation for the above example:

Given the following multilevel doubly linked list:




We should return the following flattened doubly linked list:


*/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/

// T:O(N), S:O(1), 0 ms (100.00%)
class Solution {
    public Node flatten(Node head) {
        if(head == null) return head;

        Node node = head;

        while(node != null){
            if(node.child != null){
                Node next = node.next;

                node.next = flatten(node.child);
                // reconnect child's head to the main list
                node.next.prev = node;
                node.child = null;

                while(node.next != null){
                    node = node.next;
                }
                // reconnect child's tail to the main list
                node.next = next;
                if(next != null) next.prev = node;

            }

            node = node.next;
        }

        return head;
    }
}

// T:O(N), S:O(N), 4 ms (14.64%)
class Solution {

    List<Node> list;

    public Node flatten(Node head) {

        if(head == null) return head;

        list = new LinkedList();
        insert(head);

        for(int i = 0; i < list.size() - 1; i++){
            list.get(i).next = list.get(i + 1);
            list.get(i+1).prev = list.get(i);
            list.get(i).child = null;
        }

        if(list.size() > 0) list.get(list.size() - 1).child = null;

        return head;
    }

    private void insert(Node head){
        Node node = head;
        while(node != null){
            list.add(node);
            if(node.child != null){
                insert(node.child);
            }
            node = node.next;
        }
    }
}