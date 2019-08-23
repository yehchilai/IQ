/**
This question is from https://leetcode.com/problems/zigzag-iterator/
Difficulty: medium

Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6]

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].
*/

// T:O(N), S:O(1), 2 ms (79.62%)
public class ZigzagIterator {

    LinkedList<Iterator> list;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList();
        if(v1 != null && v1.size() > 0) list.add(v1.iterator());
        if(v2 != null && v2.size() > 0) list.add(v2.iterator());
    }

    public int next() {
        Iterator iter = list.poll();
        int result = (int)iter.next();
        if(iter.hasNext()) list.add(iter);
        return result;
    }

    public boolean hasNext() {
        if(list.size() == 0) return false;
        return true;
    }
}

// T:O(N), S:O(1), 1 ms (100.00%)
public class ZigzagIterator {

    private Iterator<Integer> i, j, tmp;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        if (j.hasNext()) { tmp = j; j = i; i = tmp; }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */