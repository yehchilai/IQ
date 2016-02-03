/*
This question is from https://leetcode.com/problems/pascals-triangle/
Difficulty: easy

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]


Time Complexity: O()
*/
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(numRows == 0) return result;
        int index = 0;
        List<Integer> tmp = new LinkedList<Integer>();
        tmp.add(1);
        result.add(0,tmp);
        while(index < numRows - 1){
        	tmp = new LinkedList<Integer>();
        	List<Integer> last = result.get(index);
        	tmp.add(1);
        	for(int i = 0; i < last.size() - 1; i++){
        		tmp.add(last.get(i) + last.get(i+1));
        	}
        	tmp.add(1);
        	result.add(tmp);
        	index++;
        }
        return result;
    }
}

// Answer II from https://leetcode.com/discuss/76173/1ms-java-solution-simple
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> sol = new ArrayList<>();
        if (numRows == 0) return sol;

        List<Integer> row = new ArrayList<>();
        row.add(1);
        sol.add(row);

        for (int i = 1; i < numRows; i++) {
            List<Integer> r = new ArrayList<>();
            r.add(1);
            List<Integer> p = sol.get(i-1);
            for (int j = 0; j < p.size()-1; j++) {
                r.add(p.get(j) + p.get(j+1));
            }
            r.add(1);
            sol.add(r);
        }        
        return sol;
    }
}