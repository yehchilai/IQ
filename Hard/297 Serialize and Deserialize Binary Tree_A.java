/*
This question is from https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

Difficulty: hard

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example:

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: Just the same as how LeetCode OJ serializes a binary tree(https://leetcode.com/faq/#binary-tree). You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// T: O(N), S: O(N), 51ms
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<String>();

        traversal(root, list);
        //System.out.println(String.join(" ", list));
        return String.join(" ", list);
    }

    private void traversal(TreeNode root, List<String> list){
        if(root == null){
            list.add("null");
            return;
        }
        list.add(String.valueOf(root.val));
        traversal(root.left, list);
        traversal(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //System.out.println(data);
        String[] strArr = data.split("\\s");
        //System.out.println(strArr.length);
        LinkedList<String> list = new LinkedList<String>(Arrays.asList(strArr));
        String rootStr = list.poll();
        if(rootStr.equals("null")) return null;
        TreeNode root  = new TreeNode(Integer.valueOf(rootStr));
        insert(root, list);
        return root;
    }

    private void insert(TreeNode node, LinkedList<String> list){
        if(list.size() == 0) return;
        String str = list.poll();
        if(!str.equals("null")){
            TreeNode newNode = new TreeNode(Integer.valueOf(str));
            node.left = newNode;
            insert(node.left, list);
        }
        if(list.size() == 0) return;
        str = list.poll();
        if(!str.equals("null")){
            TreeNode newNode = new TreeNode(Integer.valueOf(str));
            node.right = newNode;
            insert(node.right, list);
        }
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// 19ms
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    public String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder();
        serializeHelper(root, s);
        return new String(s);
    }

    private void serializeHelper(TreeNode root, StringBuilder s) {
        if(root == null) {
            s.append("/ ");
            return;
        }
        s.append(root.val + " ");
        serializeHelper(root.left, s);
        serializeHelper(root.right, s);
    }

    public TreeNode deserialize(String data) {
        String[] arr = data.split(" ");


        return deHelper(arr, new int[1]);
    }

    public TreeNode deHelper(String[] arr, int[] i) {
        if(i[0] > arr.length) return null;
        if(arr[i[0]].equals("/")) return null;
        //System.out.println(arr[i[0]]);
        TreeNode node = new TreeNode(Integer.valueOf(arr[i[0]]));
        ++(i[0]);
        node.left = deHelper(arr, i);
        ++(i[0]);
        node.right = deHelper(arr, i);
        return node;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));