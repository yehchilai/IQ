/*
This question is from https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
Difficulty: medium

Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Example:



Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}

Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.


Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
*/

// iterator, T:O(N), S:O(1), 0 ms (100%)
class Solution {
    public Node connect(Node root) {
        if(root == null) return root;
        Node result = root;
        while(root != null){
            Node head = new Node(-1);
            Node current = head;
            while(root != null){
                if(root.left != null){
                    current.next = root.left;
                    current = current.next;
                }

                if(root.right != null){
                    current.next = root.right;
                    current = current.next;
                }

                root = root.next;
            }
            root = head.next;
        }

        return result;
    }

}

// BFS, T:O(N), S:O(2^logN), 6 ms (9.25% )
class Solution {
    public Node connect(Node root) {
        if(root == null) return root;
        Node node = root;
        Deque<Node> q = new ArrayDeque();
        q.add(node);
        while(q.size() > 0){

            List<Node> list = new LinkedList();
            while(q.size() > 0) list.add(q.poll());

            for(int i = 1; i < list.size(); i++){
                Node left = list.get(i - 1);
                left.next = list.get(i);
                if(left.left != null) q.add(left.left);
                if(left.right != null) q.add(left.right);
            }

            Node last = list.get(list.size() - 1);
            if(last.left != null) q.add(last.left);
            if(last.right != null) q.add(last.right);
        }

        return root;
    }
}
