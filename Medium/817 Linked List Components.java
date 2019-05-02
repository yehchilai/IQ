/*
This question is from https://leetcode.com/problems/linked-list-components/
Difficulty: medium

We are given head, the head node of a linked list containing unique integer values.

We are also given the list G, a subset of the values in the linked list.

Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

Example 1:

Input:
head: 0->1->2->3
G = [0, 1, 3]
Output: 2
Explanation:
0 and 1 are connected, so [0, 1] and [3] are the two connected components.
Example 2:

Input:
head: 0->1->2->3->4
G = [0, 3, 1, 4]
Output: 2
Explanation:
0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
Note:

If N is the length of the linked list given by head, 1 <= N <= 10000.
The value of each node in the linked list will be in the range [0, N - 1].
1 <= G.length <= 10000.
G is a subset of all values in the linked list.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// T:O(N+G.length), S:O(G), 7ms
class Solution {
    public int numComponents(ListNode head, int[] G) {
        int group = 0;
        HashSet<Integer> set = new HashSet();
        for(int n : G) set.add(n);
        ListNode node = head;

        while (node != null){
            if(set.contains(node.val) && (node.next == null || !set.contains(node.next.val))){
                group++;
            }
            node = node.next;
        }

        return group;
    }
}
// T:O(N+G.length), S:O(G), 8ms
class Solution {
    public int numComponents(ListNode head, int[] G) {
        int group = 0;
        boolean start = true;
        HashSet<Integer> set = new HashSet();
        for(int n : G) set.add(n);
        ListNode node = head;

        while (node != null){
            // System.out.println(node.val);
            if(set.contains(node.val)){
                if(start) {
                    group++;
                    start = false;
                }
                set.remove(node.val);
            }else{
                start = true;
            }
            node = node.next;
        }

        return group;
    }
}