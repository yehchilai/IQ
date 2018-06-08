/*
This question is from https://leetcode.com/problems/gray-code/description/
Difficulty: medium

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

// T:O(2^N), S:O(2^N), 1ms
class Solution {
    public List<Integer> grayCode(int n) {
        // result list
        List<Integer> result = new LinkedList();

        // edge case
        result.add(0);

        // total sequences
        int len = (int)Math.pow(2, n);

        // The functnion is f(i) = i^(i/2), ^: exclusive or (XOR)
        for(int i = 1; i < len; i++){
            result.add(i^(i>>1));

        }
        //System.out.println("===========");
        return result;
    }
}