/*
This question is from
Difficulty: hard
Tag: A

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 ); //capacity
/*
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/

//  Time Limit Exceeded
// T: O(N), S(N)
class LRUCache {

    // max capacity
    private int mCapacity;
    // record key and value
    private HashMap<Integer, Integer> mMap;
    // record order
    private LinkedList<Integer> mQ;

    public LRUCache(int capacity) {
        this.mCapacity = capacity;
        this.mMap = new HashMap();
        this.mQ = new LinkedList();
    }

    public int get(int key) {
        if(mMap.containsKey(key)){
            int val = mMap.get(key);
            updateQ(key);
            return val;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        // if contains key, update the value
        if(mMap.containsKey(key)){
            mMap.put(key, value);
            updateQ(key);
        }else{
            // if exceed the capacity, remove the oldest one.
            if (mQ.size() >= mCapacity){
                int oldest = mQ.poll();
                mMap.remove(oldest);
                mMap.put(key, value);
                mQ.add(key);
            }else{
                // otherwise, append the new one
                mMap.put(key, value);
                mQ.add(key);
            }
        }

    }

    // update the order based on the last used
    public void updateQ(int key){
        for(int i=0; i<mQ.size(); i++){
            if(mQ.get(i) == key){
                mQ.remove(i);
                mQ.add(key);
                break;
            }
        }
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Double-linkedList
// T: O(1), S: O(N), 154 ms
class LRUCache {

    // create a double linked list class
    class DoubleLinkedList{

        int key;
        int val;
        DoubleLinkedList prev;
        DoubleLinkedList next;

    }

    // max capacity
    private int mCapacity;
    // head node
    private DoubleLinkedList mHead;
    // tail node
    private DoubleLinkedList mTail;
    // hashmap to store key and value
    private HashMap<Integer, DoubleLinkedList> mCache;
    // current data points
    private int mCount;

    // constructor
    public LRUCache(int capacity) {
        this.mCapacity = capacity;
        this.mCache = new HashMap();
        this.mCount = 0;

        this.mHead = new DoubleLinkedList();
        this.mTail = new DoubleLinkedList();

        this.mHead.next = this.mTail;
        this.mHead.prev = null;
        this.mTail.next = null;
        this.mTail.prev = this.mHead;
    }

    // get the value of giving key. If not, return -1
    public int get(int key) {
        if(mCache.containsKey(key)){
            DoubleLinkedList node = mCache.get(key);
            moveToHead(node);
            // System.out.println("update: "+key);
            return node.val;
        }else{
            return -1;
        }
    }

    // put the new key and value
    public void put(int key, int value) {
        if(mCache.containsKey(key)){
            DoubleLinkedList node = mCache.get(key);
            // System.out.println("update: "+key);
            moveToHead(node);
            node.val = value;
            mCache.put(key, node);
        }else{
            if(mCount >= mCapacity){
                DoubleLinkedList last = removeTail();
                mCache.remove(last.key);
                // System.out.println("remove: "+last.key);
                mCount--;
                DoubleLinkedList newNode = new DoubleLinkedList();
                newNode.key = key;
                newNode.val = value;
                addNode(newNode);
                // System.out.println("add: "+key);
                mCache.put(key, newNode);
                mCount++;
            }else{
                DoubleLinkedList newNode = new DoubleLinkedList();
                newNode.key = key;
                newNode.val = value;
                addNode(newNode);
                // System.out.println("add: "+key);
                mCache.put(key, newNode);
                mCount++;
            }
        }
        // print();
    }

    // add node
    private void addNode(DoubleLinkedList node){
        node.prev = mHead;
        node.next = mHead.next;
        mHead.next.prev = node;
        mHead.next = node;

    }

    // remove node
    private void removeNode(DoubleLinkedList node){
        DoubleLinkedList prev = node.prev;
        DoubleLinkedList next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    // add to the head
    private void moveToHead(DoubleLinkedList node){
        removeNode(node);
        addNode(node);
    }

    // add to the tail
    private DoubleLinkedList removeTail(){
        DoubleLinkedList last = mTail.prev;
        mTail.prev = last.prev;
        last.prev.next = mTail;
        return last;
    }

    private void print(){
        DoubleLinkedList node = mHead;
        while(node.next != null){
            System.out.print(node.key+" ");
            node = node.next;
        }
        System.out.println();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */