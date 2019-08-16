/**
This question is from https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
Difficulty: hard

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree


as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



Note:

N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

// T:O(N), S:O(N), 12 ms (39.27%)
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return "";
        List<String> list = new LinkedList();
        traversal(root, list);

        return String.join(",", list);
    }

    private void traversal(Node root, List<String> list){
        if(root == null) return;
        list.add(String.valueOf(root.val));
        int size = root.children.size();
        list.add(String.valueOf(size));
        for(int i = 0 ;i < size; i++){
            traversal(root.children.get(i), list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {

        LinkedList<String> q = new LinkedList(Arrays.asList(data.split(",")));
        if(q.size() < 2) return null;

        return insert(q);
    }

    private Node insert(LinkedList<String> q){
        int val = Integer.valueOf(q.poll());
        Node root = new Node(val);
        int size = Integer.valueOf(q.poll());
        root.children = new LinkedList<Node>();
        for(int i = 0; i < size; i++){
            root.children.add(insert(q));
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));