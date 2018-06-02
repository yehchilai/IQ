/*
This question is from: https://leetcode.com/problems/insert-delete-getrandom-o1/description/
Difficulty: medium

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/

// T: O(1), S:O(N), 119ms
class RandomizedSet {

    // map for value and its index in the array list
    Map<Integer, Integer> mMap;
    // list of values
    List<Integer> mList;
    // random number generater
    Random mRandom;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        mMap = new HashMap();
        mList = new ArrayList();
        mRandom = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(mMap.containsKey(val)){
            return false;
        }else{
            // append the new value
            mList.add(val);
            // track the index of this value
            mMap.put(val, mList.size() - 1);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(mMap.containsKey(val)){
            // get the index of the value
            int index = mMap.get(val);
            // get the last value in the list
            int last = mList.get(mList.size() - 1);
            // replace the element with the last value in the list
            mList.set(index, last);
            // remove the last element in the list
            mList.remove(mList.size() - 1);
            // update the last element in the map
            mMap.put(last, index);
            // remove the element in the map
            mMap.remove(val);

            return true;
        }else{
            return false;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = mRandom.nextInt(mList.size());
        return mList.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */