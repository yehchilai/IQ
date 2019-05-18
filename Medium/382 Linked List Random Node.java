/*
This question is from https://leetcode.com/problems/linked-list-random-node/
Difficulty: medium

Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();

*/

// https://www.itread01.com/content/1545432309.html
// reservoir sampling
// T:O(N), S:O(1), 60 ms
class Solution {

    ListNode mHead;

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        mHead = head;

    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = mHead;
        if(node == null) return 0;

        int i = 1;
        Random random = new Random();
        int val = node.val;
        int k = 1;
        // System.out.println(random.nextInt(1+1));
        // return 0;
        while(node.next != null){
            node = node.next;
            int r = random.nextInt(i + 1) + 1;
            if(r <= k){
                val = node.val;
            }

            i++;

        }

        return val;
    }


}


// T:O(N), M:O(1), 152ms
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    int mSize;
    ListNode mHead;

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        mSize = 0;
        mHead = head;
        if(head != null){
            mSize++;
            while(head.next != null){
                mSize++;
                head = head.next;
            }
        }

    }

    /** Returns a random node's value. */
    public int getRandom() {
        Random radom = new Random();
        int r = radom.nextInt(mSize) + 1;
        ListNode node = mHead;
        r--;
        while(r > 0){
            node = node.next;
            r--;
        }

        return node.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

/****************************************************************************************************/
//Reservoir Sampling, T:O(N), M:O(0), 126 ms

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    ListNode mHead;

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        mHead = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = mHead;
        if(node == null) return 0;
        int result = node.val;
        int i = 1;
        Random radom = new Random();

        while(node.next != null){
            node = node.next;
            i++;
            if(randInt(1, i) == i) result = node.val;
        }

        return result;
    }

    public int randInt(int min, int max){
        Random random = new Random();
        return min+random.nextInt(max - min + 1);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
