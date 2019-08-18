/**
This question is from https://leetcode.com/problems/random-pick-with-blacklist/
Difficulty: hard

Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.

Optimize it such that it minimizes the call to system’s Math.random().

Note:

1 <= N <= 1000000000
0 <= B.length < min(100000, N)
[0, N) does NOT include N. See interval notation.
Example 1:

Input:
["Solution","pick","pick","pick"]
[[1,[]],[],[],[]]
Output: [null,0,0,0]
Example 2:

Input:
["Solution","pick","pick","pick"]
[[2,[]],[],[],[]]
Output: [null,1,1,1]
Example 3:

Input:
["Solution","pick","pick","pick"]
[[3,[1]],[],[],[]]
Output: [null,0,0,2]
Example 4:

Input:
["Solution","pick","pick","pick"]
[[4,[2]],[],[],[]]
Output: [null,1,3,1]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, N and the blacklist B. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.


*/

// T:O(B) and T:O(1), S:O(B), 95 ms (88.02%)
class Solution {

    // remap the blacklist number to white list number
    HashMap<Integer, Integer> map;
    // white list size
    int size;
    Random rand;

    public Solution(int N, int[] blacklist) {
        map = new HashMap();
        rand = new Random();
        // left side white list range
        size = N - blacklist.length;

        // T:O(B)
        for(int black: blacklist){
            map.put(black, -1);
        }

        // right side black list range
        int index = size;

        // T:O(B)
        for(int black: blacklist){
            if(black < size){
                // find a number in the black list range but not in the black list
                for(int j = index; j < N; j++){
                    if(!map.containsKey(j)){
                        map.put(black, j);
                        index = j + 1;
                        break;
                    }
                }
            }
        }
    }

    public int pick() {
        int r = rand.nextInt(size);
        if(map.containsKey(r)){
            return map.get(r);
        }
        return r;
    }
}

// Memory Limit Exceeded
class Solution {

    List<Integer> list;
    Random rand;
    public Solution(int N, int[] blacklist) {
        list = new LinkedList();
        rand = new Random();
        int index = 0;
        for(int i = 0; i < N; i++){
            if(index < blacklist.length && i == blacklist[index]){
                index++;
                continue;
            }
            list.add(i);
        }
    }

    public int pick() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */