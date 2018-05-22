/*
This question is from https://leetcode.com/problems/merge-k-sorted-lists/description/
Difficulty: hard

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// T: O(N), S: O(N), 22ms
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 0 ) return null;

        // min heap
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(11, new ListCompare());
        // insert all nodes into the heap
        for(int i = 0; i < lists.length; i++){
            ListNode node = lists[i];
            while(node!= null){
                heap.add(node);
                node = node.next;
            }
        }

        // check if the heap is empty
        if(heap.size() < 1) return null;

        ListNode psuedoHead = new ListNode(0);
        psuedoHead.next = new ListNode(heap.poll().val);
        ListNode node = psuedoHead.next;
        while(heap.size() > 0 ){
            node.next = new ListNode(heap.poll().val);
            node = node.next;
        }
        return psuedoHead.next;
    }

    class ListCompare implements Comparator<ListNode>{

        public int compare(ListNode a, ListNode b){
            if(a.val < b.val) return -1;
            if(a.val > b.val) return 1;
            return 0;
        }
    }
}