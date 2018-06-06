/*
This question is from https://leetcode.com/problems/binary-tree-right-side-view/description/
Difficulty: Medium

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/
//
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// BFS, T: O(N), S: O(N), 3ms
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // record the current layer nodes
        Deque<TreeNode> q = new LinkedList();
        // result list
        List<Integer> result = new LinkedList();
        // edge case
        if(root == null) return result;
        // initialize q
        q.add(root);
        // traveral all nodes
        while(q.size() > 0){
            int size = q.size();
            // go thourgh the current layer
            for(int i = 0; i < size ; i++){
                TreeNode node = q.poll();
                if(i == 0) result.add(node.val);
                if(node.right != null) q.add(node.right);
                if(node.left != null) q.add(node.left);
            }
        }
        return result;
    }

}

// Pre-order traversal with layer index, T: O(N), S: O(N), 3ms
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // result list
        List<Integer> result = new LinkedList();
        List<List<Integer>> layerList = new LinkedList();
        traversal(root, layerList, 0);

        for(int i = 0; i < layerList.size(); i++){
            List<Integer> currentLayer = layerList.get(i);
            result.add(currentLayer.get(currentLayer.size() - 1));
        }

        return result;
    }

    private void traversal(TreeNode root, List<List<Integer>> list, int layer){
        if(root == null) return;

        // if there is no layer list in the current layer, add a new layer list
        if(list.size() <= layer) list.add(new LinkedList());

        // insert the value into this layer list.
        list.get(layer).add(root.val);

        traversal(root.left, list, layer + 1);
        traversal(root.right, list, layer + 1);
    }
}