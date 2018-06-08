/*
This question is from https://leetcode.com/problems/split-linked-list-in-parts/description/
Difficulty: medium

Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".

The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.

The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.

Return a List of ListNode's representing the linked list parts that are formed.

Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
Example 1:
Input:
root = [1, 2, 3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The input and each element of the output are ListNodes, not arrays.
For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but it's string representation as a ListNode is [].
Example 2:
Input:
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
Note:

The length of root will be in the range [0, 1000].
Each value of a node in the input will be an integer in the range [0, 999].
k will be an integer in the range [1, 50].
*/
//
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// T:O(N + K), S:O(K), 6ms
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        // result list
        List<ListNode> result = new LinkedList();

        // egde case
        if(root == null){
            for(int i = 0; i < k; i++){
                result.add(null);
            }
            return result.toArray(new ListNode[result.size()]);
        }

        // get the list length
        int len = 1;
        ListNode node = root;
        while(node.next != null){
            len++;
            node = node.next;
        }

        // check k
        if((len/k) > 0){
            // reset root
            node = root;
            ListNode endNode = root;
            // get the quotient and remainder
            int remainder = len % k;
            int quotient = len / k;
            for(int i = 0; i < k; i++){
                for(int j = 0; j < quotient; j++){
                    if(j == 0) result.add(node);
                    endNode = node;
                    node = node.next;
                    // System.out.println("j: "+j+", q: "+quotient+", i: "+ i+", r: "+remainder);
                    // if this is the first reaminder number of partition
                    if(j == quotient - 1 && i < remainder) {
                        // System.out.println("last one");
                        endNode = node;
                        node = node.next;
                    }
                }
                endNode.next = null;
            }
        }else{
            node = root;
            // saperate list node
            for(int i = 0; i < k ; i++){
                if(node != null){
                    result.add(node);
                    node = node.next;
                    result.get(result.size() - 1).next = null;
                }else{
                    // append null
                    result.add(null);
                }
            }
        }

        return result.toArray(new ListNode[result.size()]);
    }
}

// same idea, better implementation
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, rem = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = cur;
            for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; ++j) {
                if (cur != null) cur = cur.next;
            }
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            ans[i] = head;
        }
        return ans;
    }
}
// create new list
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, rem = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = new ListNode(0), write = head;
            for (int j = 0; j < width + (i < rem ? 1 : 0); ++j) {
                write = write.next = new ListNode(cur.val);
                if (cur != null) cur = cur.next;
            }
            ans[i] = head.next;
        }
        return ans;
    }
}