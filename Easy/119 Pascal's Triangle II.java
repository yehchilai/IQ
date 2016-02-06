/*
This question is from https://leetcode.com/problems/pascals-triangle-ii/

Given an index k^th, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?



Time Complexity: O(N^2), N; the rowIndex
*/
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(1);
        if(rowIndex == 0) return result;
        result.add(1);
        int level = 1;
        while(level < rowIndex){
            int lastInt = result.get(0);
            for(int i = 1; i < result.size(); i++){
                int tmp = lastInt + result.get(i);
                lastInt = result.get(i);
                result.set(i,tmp);
            }
            result.add(result.size(), 1);
            level++;
        }
        return result;
    }
}