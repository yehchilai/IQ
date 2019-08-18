/**
This question is from https://leetcode.com/problems/random-pick-with-weight/
Difficulty: medium

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input:
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input:
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.


*/

// T:O(N + logN), S:O(1), 66 ms (64.44%)
class Solution {

    int[] w;
    int sum;
    Random random;

    public Solution(int[] w) {
        this.w = w;
        random = new Random();
        sum = w[0];
        for(int i = 1; i < w.length; i++){
            sum += w[i];
            w[i] += w[i - 1];
        }
    }

    public int pickIndex() {

        int n = random.nextInt(sum);

        int left = 0;
        int right = w.length - 1;

        while(left != right){
            int mid = (left + right) / 2;

            if(n >= w[mid]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        return left;
    }
}

// T:O(N + logN), S:O(N), 87 ms (10.46%)
class Solution {

    int sum;
    TreeMap<Integer, Integer> map;
    Random rand;

    public Solution(int[] w) {
        map = new TreeMap();
        rand = new Random();
        for(int i = 0; i < w.length; i++){
            sum += w[i];
            map.put(sum, i);
        }
    }

    public int pickIndex() {
        int key = map.higherKey(rand.nextInt(sum));
        return map.get(key);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */