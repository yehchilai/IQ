/*
This question is from https://leetcode.com/problems/symmetric-tree/
Difficulty: easy

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3

Note:
Bonus points if you could solve it both recursively and iteratively.

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".


Time Complexity: O()
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
// recursively
public class Solution {
    public boolean isSymmetric(TreeNode root) {
    	return isSymmetric(root, root);
    }

    public boolean isSymmetric(TreeNode node1, TreeNode node2){
    	if(node1 == null && node2 == null) return true;
    	if(node1 == null || node2 == null) return false;
    	if(node1.val != node2.val) return false;
    	return isSymmetric(node1.left, node1.right) && isSymmetric(node2.right, node2.left);
    }
}
// iteratively
public class Solution {
    public boolean isSymmetric(TreeNode root) {
    	if(root == null) return true;
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root.left);
    	queue.add(root.right);
    	while(!queue.isEmpty()){
    		TreeNode node1 = queue.poll();
    		TreeNode node2 = queue.poll();
    		if(node1 == null && node2 == null) continue;
    		if(node1 == null || node2 == null) return false;
    		if(node1.val != node2.val) return false;

    		queue.add(node1.left);
    		queue.add(node2.right);
    		queue.add(node1.right);
    		queue.add(node2.left);
    	}
    	return true;
    }
}

