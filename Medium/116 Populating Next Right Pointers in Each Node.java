/**
This question is from https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
Dififculty: medium

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Example:



Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.


Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
*/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

// recursive, T:O(N), S:O(1), 0 ms (100.00%)
class Solution {
    public Node connect(Node root) {
        if(root == null) return root;
        link(root.left, root.right);
        return root;
    }

    private void link(Node node1, Node node2){
        if(node1 == null) return;
        node1.next = node2;

        link(node1.left, node1.right);
        link(node2.left, node2.right);
        link(node1.right, node2.left);
    }
}

// BFS, T:O(N), S:O(2^logN), 2 ms (28.69% )
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