/*
This question is from https://leetcode.com/problems/lfu-cache/description/
Difficulty: hard

Design and implement a data structure for Least Frequently Used (LFU, https://en.wikipedia.org/wiki/Least_frequently_used) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 capacity );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/
// T: O(1), S: O(N), 167 ms
class LFUCache {

    // max capacity
    int mCapacity;
    // current amount
    int mSize = 0;
    // value map
    HashMap<Integer, Node> mValueMap;
    // count map
    HashMap<Integer, DoubleLinkedList> mCountMap;
    // minmum used frequency;
    int mMin = 0;

    public LFUCache(int capacity) {
        this.mCapacity = capacity;
        this.mValueMap = new HashMap();
        this.mCountMap = new HashMap();
    }

    public int get(int key) {
        if(mValueMap.containsKey(key)){
            // get the value of the key
            Node node = mValueMap.get(key);
            // update count map
            update(node);
            return node.value;
        }else{
            return -1;
        }

    }

    public void put(int key, int value) {
        // check if the capacity is avalible
        if(mCapacity == 0) return;

        if(mValueMap.containsKey(key)){
            Node updateNode = mValueMap.get(key);
            // update value
            updateNode.value = value;
            // update count map
            update(updateNode);
        }else{
            Node newNode = new Node(key, value);
            // remove least frequent element
            if(mSize >= mCapacity){
                DoubleLinkedList list = mCountMap.get(mMin);
                Node node = list.removeLast();
                mValueMap.remove(node.key);
                mSize--;
            }
            // update counters
            mMin = 1;
            mSize++;
            // add the new node into the list
            DoubleLinkedList list = mCountMap.getOrDefault(mMin, new DoubleLinkedList());
            list.addNode(newNode);
            // put the new node and the list into maps
            mCountMap.put(mMin, list);
            mValueMap.put(key, newNode);
        }


    }

    // update the existing node
    private void update(Node node){
        // get the list of the current count
        DoubleLinkedList list = mCountMap.get(node.count);
        list.removeNode(node);
        if(node.count == mMin && list.size == 0) mMin++;
        // update node count
        node.count++;
        // update count map
        DoubleLinkedList updateList = mCountMap.getOrDefault(node.count, new DoubleLinkedList());
        updateList.addNode(node);
        mCountMap.put(node.count, updateList);
        // update value map
        mValueMap.put(node.key, node);
    }


    /* Double linked list*/
    class Node{
         // key, value, count
        int key, value, count;
        // previous node
        Node prev;
        // next node
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1;
            this.prev = null;
            this.next = null;
        }
    }


    class DoubleLinkedList{
        // psuedo head
        Node head;
        // psudo tail
        Node tail;
        // list size
        int size;

        public DoubleLinkedList(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        public void addNode(Node node){
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        public void removeNode(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node removeLast(){
            if(size > 0){
                Node node = tail.prev;
                removeNode(node);
                return node;
            }else{
                return null;
            }

        }
    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */