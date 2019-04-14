/*
This question is from https://leetcode.com/problems/serialize-and-deserialize-bst/
Difficulty: medium

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


*/

// Tree traversal, Binary search tree, T:O(N), M:O(N), 16ms
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// preoder, T:O(N), S:O(N), 17ms
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    public void preorder(TreeNode root, StringBuilder sb){
        if(root == null){
            return;
        }
        sb.append(String.valueOf(root.val)+",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArr = data.split(",");
        if(strArr.length <= 1){
            if(strArr[0].equals("")){
                return null;
            }else{
                return new TreeNode(Integer.valueOf(strArr[0]));
            }

        }
        TreeNode root = new TreeNode(Integer.valueOf(strArr[0]));
        for(int i = 1; i < strArr.length; i++){
            insert(root, Integer.valueOf(strArr[i]));
        }

        return root;
    }

    public void insert(TreeNode root, int val){
        if(root == null) return;

        if(root.val < val){
            if(root.right == null){
                root.right = new TreeNode(val);
            }else{
                insert(root.right, val);
            }

        }else{
            if(root.left == null){
                root.left = new TreeNode(val);
            }else{
                insert(root.left, val);
            }
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// BFS, T:O(N), S:O(N), 27ms
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Deque<TreeNode> q = new LinkedList();
        List<String> strList = new LinkedList();
        q.add(root);
        while(q.size() > 0){
            int size = q.size();
            for(int i = 0 ;i < size; i++){
                TreeNode node = q.poll();
                if(node != null){
                    q.add(node.left);
                    q.add(node.right);
                    strList.add(String.valueOf(node.val));
                }else{
                    strList.add("null");
                }
            }
        }
        return String.join(",", strList.toArray(new String[strList.size()]));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        if((nodes.length == 1) && (nodes[0].equals("null"))) return null;
        Deque<TreeNode> q = new LinkedList();
        TreeNode head = new TreeNode(Integer.valueOf(nodes[0]));
        q.add(head);
        int index = 1;
        while(q.size() > 0){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(nodes[index].equals("null")){
                    node.left = null;
                }else{
                    node.left = new TreeNode(Integer.valueOf(nodes[index]));
                    q.add(node.left);
                }
                index++;
                if(nodes[index].equals("null")){
                    node.right = null;
                }else{
                    node.right = new TreeNode(Integer.valueOf(nodes[index]));
                    q.add(node.right);
                }
                index++;
            }
        }
        return head;
    }
}