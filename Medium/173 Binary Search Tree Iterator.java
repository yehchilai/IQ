/**
This question is from https://leetcode.com/problems/binary-search-tree-iterator/
Difficulty: medium

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.



Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
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

// Stack
// T:O(N), S:O(h), h: the height of tree, 57ms(94.00%)
class BSTIterator {

    Stack<TreeNode> stack;
    TreeNode current;
    public BSTIterator(TreeNode root) {
        stack = new Stack();
        current = root;
        while(current != null && current.left != null){
            stack.push(current);
            current = current.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        int val = current.val;
        if(current.right == null){
            if(!stack.empty()){
                current = stack.pop();
            }else{
                current = null;
            }
        }else{
            current = current.right;
            while(current.left != null){
                stack.push(current);
                current = current.left;
            }
        }

        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(!stack.empty() || current != null) return true;
        return false;
    }
}

// flatten BST
// T:O(N), S:O(N), 117ms (5.58%);
class BSTIterator {

    List<Integer> list;
    int index;
    public BSTIterator(TreeNode root) {
        list = new LinkedList();
        inorder(root);
        index = 0;
    }

    /** @return the next smallest number */
    public int next() {

        int val = list.get(index);
        index++;
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(index < list.size()) return true;
        return false;
    }

    private void inorder(TreeNode root){
        if(root == null) return;

        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */