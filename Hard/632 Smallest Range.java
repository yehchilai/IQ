/**
This qeustionis from https://leetcode.com/problems/smallest-range/
Difficulty: hard

You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.

*/

// T:O(NlogN), S:O(m), row of nums, 27 ms (92.56% )
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {

        if(nums == null || nums.size() == 0) return null;

        PriorityQueue<Element> pq = new PriorityQueue(new ElementCompare());
        int len = nums.size();
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < len; i++){
            int val = nums.get(i).get(0);
            pq.add(new Element(i, 0, val));
            max = Math.max(max, val);
        }

        int start = 0;
        int end = max;
        int range = Integer.MAX_VALUE;

        while(pq.size() == len){
            Element current = pq.poll();

            // check if upate the range
            if(max - current.val < range){
                range = max - current.val;
                start = current.val;
                end = max;
            }

            // insert a new element
            if(current.col+1 < nums.get(current.row).size()){
                int row = current.row;
                int col = current.col + 1;
                int val = nums.get(row).get(col);
                max = Math.max(max, val);
                pq.add(new Element(row, col, val));
            }
        }

        return new int[]{start, end};
    }
}

class Element{
    int row;
    int col;
    int val;

    public Element(int r, int c, int v){
        this.row = r;
        this.col = c;
        this.val = v;
    }
}

class ElementCompare implements Comparator<Element>{
    @Override
    public int compare(Element a, Element b){
        return a.val - b.val;
    }
}