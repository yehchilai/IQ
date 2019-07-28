/**
This question is from https://leetcode.com/problems/design-hashmap/
Difficulty: easy

Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);
hashMap.put(2, 2);
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found)

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.
*/

// T:O(N/10000), S:O(1), 61 ms (97.48% )
class MyHashMap {

    ListNode[] list;
    int SIZE = 10000;

    /** Initialize your data structure here. */
    public MyHashMap() {
        list = new ListNode[SIZE];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {

        ListNode prev = getPrevNode(key);
        if(prev.next == null){
            // System.out.println("put-- key: "+ key+", val: "+value);
            prev.next = new ListNode(key, value);
        }else{
            // System.out.println("update-- key: "+ key+", val: "+value);
            prev.next.val = value;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        ListNode prev = getPrevNode(key);
        if(prev.next == null) {
            // System.out.println("get-- key: "+ key+", prev.key: "+prev.key+", prev.next: null");
            return -1;
        }
        return prev.next.val;

    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        ListNode prev = getPrevNode(key);
        if(prev.next == null) return;
        prev.next = prev.next.next;
    }

    private int getIndex(int key){
        return key % SIZE;
    }

    private ListNode getPrevNode(int key){
        int index = getIndex(key);
        if(list[index] == null){
            ListNode head = new ListNode(-1, -1);
            list[index] = head;
            return list[index];
        }

        ListNode node = list[index];

        while(node.next != null && node.next.key != key){
            node = node.next;
        }

        return node;
    }


}

class ListNode{
    int key;
    int val;
    ListNode next;

    public ListNode(int k, int v){
        this.key = k;
        this.val = v;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */