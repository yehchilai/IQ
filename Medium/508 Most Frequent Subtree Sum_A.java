/*
This question is from https://leetcode.com/problems/most-frequent-subtree-sum/description/
Difficulty: medium

Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
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
// Faster Map iterator, Post-order traversal, T: O(N), S: O(N), 21ms
class Solution {

    int mMaxFrequent = 0;
    // Integer: sum, Integer: frequent
    Map<Integer, Integer> mMap = new HashMap();

    public int[] findFrequentTreeSum(TreeNode root) {

        // result list
        LinkedList<Integer> list = new LinkedList();

        postOrder(root);

        // for(Map.Entry<Integer, Integer> pair : mMap.entrySet()){
        //     System.out.println(pair.getKey() + ", " + pair.getValue());
        //     if(pair.getValue() == mMaxFrequent){
        //         list.add(pair.getKey());
        //     }
        // }
        // Faster
        for(int key: mMap.keySet()){
            if(mMap.get(key) == mMaxFrequent) list.add(key);
        }

        int[] result = new int[list.size()];
        for(int i =0; i < list.size(); i++){
            result[i] = list.get(i);
        }

        return result;
    }

    private int postOrder(TreeNode root){
        if(root == null) return 0;

        int left = 0;
        // go to the left node
        left = postOrder(root.left);
        int right = 0;
        // go to the right node
        right = postOrder(root.right);

        // current sum of the sub tree
        int sum = left + right + root.val;
        // insert the sum into the mMap to calculate the frequent of this sum
        int frequent = mMap.getOrDefault(sum, 0);
        mMap.put(sum, frequent + 1);

        // update the max frequent
        mMaxFrequent = Math.max(mMaxFrequent, mMap.get(sum));

        return sum;
    }
}


// Post-order traversal, T: O(N), S: O(N), 100ms
class Solution {

    int mMaxFrequent = 0;

    public int[] findFrequentTreeSum(TreeNode root) {

        // result list
        LinkedList<Integer> list = new LinkedList();

        // Integer: sum, Integer: frequent
        Map<Integer, Integer> map = new HashMap();

        int sum = postOrder(root, map, 0);

        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            System.out.println(pair.getKey() + ", " + pair.getValue());
            if(pair.getValue() == mMaxFrequent){
                list.add(pair.getKey());
            }
        }

        int[] result = new int[list.size()];
        for(int i =0; i < list.size(); i++){
            result[i] = list.get(i);
        }

        return result;
    }

    private int postOrder(TreeNode root, Map<Integer, Integer> map, int sum){
        if(root == null) return 0;

        int left = 0;
        // go to the left node
        if(root.left != null) left = postOrder(root.left, map, sum);
        int right = 0;
        // go to the right node
        if(root.right != null) right = postOrder(root.right, map, sum);

        // current sum of the sub tree
        sum += left + right + root.val;
        // insert the sum into the map to calculate the frequent of this sum
        int frequent = map.getOrDefault(sum, 0);
        map.put(sum, frequent + 1);

        // update the max frequent
        mMaxFrequent = Math.max(mMaxFrequent, map.get(sum));

        return sum;
    }
}