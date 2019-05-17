/*
This question is from https://leetcode.com/problems/flatten-nested-list-iterator/
Difficulty: medium

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,4,6].
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// Impressive method https://leetcode.com/problems/flatten-nested-list-iterator/discuss/80146/Real-iterator-in-Python-Java-C%2B%2B

// stack method, T:O(N), S:O(N), 5 ms
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack();
        for(int i = nestedList.size() - 1; i >= 0; i--){
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        if(hasNext()){
            return stack.pop().getInteger();
        }

        return 0;
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()){
            NestedInteger current = stack.peek();

            if(current.isInteger()){
                return true;
            }

            stack.pop();

            for(int i = current.getList().size() - 1 ; i >= 0; i--){
                stack.push(current.getList().get(i));
            }
        }

        return false;
    }
}

// straight forward, T:O(N), S:O(N), 59 ms
public class NestedIterator implements Iterator<Integer> {

    List<Integer> list;
    int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new LinkedList();

        insert(nestedList);

        index = 0;

        // for(int n : list) System.out.println(n);
        // System.out.println("=======");
    }

    private void insert(List<NestedInteger> nestedList){
        for(int i = 0; i < nestedList.size(); i++){
            if(nestedList.get(i).isInteger()){
                list.add(nestedList.get(i).getInteger());
            }else{
                insert(nestedList.get(i).getList());
            }
        }
    }

    @Override
    public Integer next() {
        if(hasNext()){
            int current = list.get(index);
            index++;
            return current;
        }else{
            return 0;
        }
    }

    @Override
    public boolean hasNext() {
        if(index < list.size()){
            return true;
        }else{
            return false;
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */