/*
This question is from https://leetcode.com/problems/binary-tree-vertical-order-traversal/
Difficulty: medium

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
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
// T:O(N), S:O(N), 2 ms
class Solution {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        int left = 0;
        int right = 0;
        HashMap<Integer, List<Integer>> map = new HashMap();
        List<List<Integer>> ans = new LinkedList();

        if(root == null) return ans;

        LinkedList<Node> q = new LinkedList();
        q.add(new Node(root, 0));

        while(q.size() > 0){
            Node current = q.poll();
            TreeNode node = current.node;
            int col = current.col;

            left = Math.min(left, col);
            right = Math.max(right, col);

            List<Integer> list = map.getOrDefault(col, new LinkedList<Integer>());
            list.add(node.val);
            map.put(col, list);
            if(node.left != null) q.add(new Node(node.left, col - 1));
            if(node.right != null) q.add(new Node(node.right, col + 1));
        }



        for(int i = left; i <= right; i++){
            if(map.containsKey(i)){
                ans.add(map.get(i));
            }
        }

        return ans;
    }
}

class Node{
    TreeNode node;
    int col;

    Node(TreeNode n, int c){
        node = n;
        col = c;
    }
}


// Order is not correct "If two nodes are in the same row and column, the order should be from left to right."
class Solution {

    int left;
    int right;
    HashMap<Integer, List<Integer>> map;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        left = 0;
        right = 0;
        map = new HashMap();

        preOrder(root, 0);

        List<List<Integer>> ans = new LinkedList();

        for(int i = left; i <= right; i++){
            if(map.containsKey(i)){
                ans.add(map.get(i));
            }
        }

        return ans;
    }

    private void preOrder(TreeNode node, int col){
        if(node == null) return;

        left = Math.min(left, col);
        right = Math.max(right, col);
        List<Integer> list = map.getOrDefault(col, new LinkedList<Integer>());
        list.add(node.val);
        map.put(col, list);

        preOrder(node.left, col - 1);
        preOrder(node.right, col + 1);


    }
}