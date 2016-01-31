/*
This question is from https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
Difficulty: easy

Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, 
where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".


Time Complexity: O(N) , N : the number of nodes
Hint: BFS
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
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if(root == null) return result;
        q.add(root); 

        while(!q.isEmpty()){
        	List<Integer> tmp = new ArrayList<Integer>();
        	int length = q.size();
        	for(int i = 0; i < length; i++){
        		TreeNode node = q.poll();
        		tmp.add(node.val);
        		if(node.left != null) q.add(node.left);
        		if(node.right != null) q.add(node.right);
        	}
        	result.add(0, tmp);
        }
        return result;
    }
}